package br.com.professorclaytonandrade.sistemaservicosjavafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Despesa; // Adjust the package path as needed
import br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao.ConexaoBanco;


public class DespesaDAO {

    public void registrarDespesa(Despesa despesa) {
        String sql = "INSERT INTO despesas (descricao, valor, data) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, despesa.getDescricao());
            stmt.setBigDecimal(2, despesa.getValor());
            stmt.setDate(3, java.sql.Date.valueOf(despesa.getData()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
