package br.com.professorclaytonandrade.sistemaservicosjavafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {

    public void adicionarIngrediente(Ingrediente ingrediente) {
        String sql = "INSERT INTO ingredientes (nome, preco, unidade_medida) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, ingrediente.getNome());
            stmt.setBigDecimal(2, ingrediente.getPreco());
            stmt.setString(3, ingrediente.getUnidadeMedida());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ingrediente> listarIngredientes() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        String sql = "SELECT * FROM ingredientes";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Ingrediente ingrediente = new Ingrediente(
                        rs.getString("nome"),
                        rs.getBigDecimal("preco"),
                        rs.getString("unidade_medida")
                );
                ingrediente.setId(rs.getInt("id"));
                ingredientes.add(ingrediente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredientes;
    }
}
