package br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBanco {

    private static final String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/lanchonete";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final Logger logger = LoggerFactory.getLogger(ConexaoBanco.class);

    // Method to obtain a connection
    public static Connection obterConexao() throws SQLException {
        logger.info("Obtendo conexão...");
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conexao = ConexaoBanco.obterConexao()) {
            logger.info("Conexão obtida com sucesso!");
            // You can perform your database operations here
        } catch (SQLException e) {
            logger.error("Erro ao obter a conexão: {}", e.getMessage());
        }
    }
}
