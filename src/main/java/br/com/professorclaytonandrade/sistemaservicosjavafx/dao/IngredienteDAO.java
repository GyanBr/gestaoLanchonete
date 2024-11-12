package br.com.professorclaytonandrade.sistemaservicosjavafx.dao;

import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Ingrediente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredienteDAO {

    private Connection connection;

    public IngredienteDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserir(Ingrediente ingrediente) throws SQLException {
        String sql = "INSERT INTO ingrediente (nome, quantidade) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ingrediente.getNome());
            stmt.setDouble(2, ingrediente.getQuantidade());
            stmt.executeUpdate();
        }
    }

    public List<Ingrediente> listarTodos() throws SQLException {
        List<Ingrediente> ingredientes = new ArrayList<>();
        String sql = "SELECT * FROM ingrediente";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ingrediente ingrediente = new Ingrediente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("quantidade")
                );
                ingredientes.add(ingrediente);
            }
        }
        return ingredientes;
    }

    public void atualizar(Ingrediente ingrediente) throws SQLException {
        String sql = "UPDATE ingrediente SET nome = ?, quantidade = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ingrediente.getNome());
            stmt.setDouble(2, ingrediente.getQuantidade());
            stmt.setInt(3, ingrediente.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM ingrediente WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
