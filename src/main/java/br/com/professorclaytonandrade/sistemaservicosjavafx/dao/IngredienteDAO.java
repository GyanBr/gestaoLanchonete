package br.com.professorclaytonandrade.sistemaservicosjavafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao.FabricaDeConexao;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Ingrediente; // Adjust the package path as needed
import br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao.FabricaDeConexao;

public class IngredienteDAO {

    public void adicionarIngrediente(Ingrediente ingrediente) {

    }

    public List<Ingrediente> listarIngredientes() {
        List<Ingrediente> ingredientes = new ArrayList<>();
        String sql = "SELECT * FROM ingredientes";
        try (Connection conn = FabricaDeConexao.obterConexao();
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

    public void salvar(Ingrediente ingrediente) {
    }
}
