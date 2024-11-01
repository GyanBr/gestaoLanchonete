package br.com.professorclaytonandrade.sistemaservicosjavafx.config.versoesbasedados;

import br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao.ConexaoBanco;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class InicializadorBancoDados {
    private final Connection conexao; // Connection to the database
    private static final String CAMINHO_MIGRACOES = "sql/"; // Migration path

    public InicializadorBancoDados() throws SQLException {
        this.conexao = ConexaoBanco.obterConexao(); // Get database connection
    }

    public void inicializar() {
        try {
            criarSchemaVersaoTabela(); // Ensure version control table exists
            List<Path> arquivosMigracao = listarArquivosDeMigracao(); // Get migration files

            for (Path caminho : arquivosMigracao) {
                int idVersao = extrairVersaoDoArquivo(caminho.getFileName().toString()); // Extract version ID
                if (!isMigracaoAplicada(idVersao)) {
                    String sql = lerArquivoSql(caminho); // Read SQL from file
                    aplicarMigracao(idVersao, caminho.getFileName().toString(), sql); // Apply migration
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace(); // Better logging could be implemented here
        }
    }

    private List<Path> listarArquivosDeMigracao() throws IOException {
        try {
            URI uriMigracoes = getClass().getClassLoader().getResource(CAMINHO_MIGRACOES).toURI();
            Path diretorioMigracoes = Paths.get(uriMigracoes);

            return Files.list(diretorioMigracoes)
                    .filter(Files::isRegularFile)
                    .sorted()
                    .collect(Collectors.toList());
        } catch (URISyntaxException e) {
            throw new IOException("Erro ao acessar a pasta de migrações", e);
        } catch (NullPointerException e) {
            throw new IOException("Pasta de migrações não encontrada: " + CAMINHO_MIGRACOES);
        }
    }

    private String lerArquivoSql(Path caminhoArquivo) throws IOException, SQLException {
        StringBuilder sqlBuilder = new StringBuilder();
        try (BufferedReader leitor = Files.newBufferedReader(caminhoArquivo)) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                sqlBuilder.append(linha).append("\n");
            }
        }
        String sql = sqlBuilder.toString().trim();
        if (sql.isEmpty()) {
            throw new SQLException("A string SQL não pode estar vazia: " + caminhoArquivo.toString());
        }
        return sql;
    }

    private void aplicarMigracao(int idVersao, String descricao, String sql) throws SQLException {
        try (PreparedStatement declaracao = conexao.prepareStatement(sql)) {
            declaracao.executeUpdate();
            registrarMigracao(idVersao, descricao); // Register applied migration
        }
    }

    private void registrarMigracao(int idVersao, String descricao) throws SQLException {
        String sql = "INSERT INTO schema_version (version_id, description) VALUES (?, ?)";
        try (PreparedStatement declaracao = conexao.prepareStatement(sql)) {
            declaracao.setInt(1, idVersao);
            declaracao.setString(2, descricao);
            declaracao.executeUpdate();
        }
    }

    private boolean isMigracaoAplicada(int idVersao) throws SQLException {
        String sql = "SELECT COUNT(*) FROM schema_version WHERE version_id = ?";
        try (PreparedStatement declaracao = conexao.prepareStatement(sql)) {
            declaracao.setInt(1, idVersao);
            var resultSet = declaracao.executeQuery();
            resultSet.next();
            return resultSet.getInt(1) > 0; // Check if migration was applied
        }
    }

    private void criarSchemaVersaoTabela() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS schema_version (" +
                "version_id INT PRIMARY KEY, " +
                "description VARCHAR(255), " +
                "applied_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        try (PreparedStatement declaracao = conexao.prepareStatement(sql)) {
            declaracao.executeUpdate();
        }
    }

    private int extrairVersaoDoArquivo(String arquivo) {
        String parteVersao = arquivo.split("__")[0].substring(1); // Extract version number
        return Integer.parseInt(parteVersao);
    }
}
