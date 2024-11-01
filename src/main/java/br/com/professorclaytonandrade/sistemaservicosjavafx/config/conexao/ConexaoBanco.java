package br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexaoBanco {

    private static HikariDataSource dataSource;
    private static final Logger logger = LoggerFactory.getLogger(ConexaoBanco.class);

    // Connection pool configuration
    static {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/lanchonete");
        config.setUsername("root");
        config.setPassword("root");
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(1800000);

        dataSource = new HikariDataSource(config);
    }

    // Method to obtain a connection
    public static Connection obterConexao() throws SQLException {
        logger.info("Obtendo conexão do pool...");
        return dataSource.getConnection();
    }

    // Method to close the connection pool
    public static void fecharDataSource() {
        if (dataSource != null) {
            logger.info("Fechando o pool de conexões...");
            dataSource.close();
        }
    }

    public static void main(String[] args) {
        try (Connection conexao = ConexaoBanco.obterConexao()) {
            logger.info("Conexão obtida com sucesso!");
        } catch (SQLException e) {
            logger.error("Erro ao obter a conexão: {}", e.getMessage());
        }
    }
}
