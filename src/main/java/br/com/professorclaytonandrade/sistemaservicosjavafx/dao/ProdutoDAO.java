package br.com.professorclaytonandrade.sistemaservicosjavafx.dao;

import br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao.ConexaoBanco;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;

public class ProdutoDAO {

    // Method to register a product
    public void registrarProduto(Produto produto) {
        String sql = "INSERT INTO produtos (descricao, preco, quantidade_estoque, markup) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, produto.getDescricao());
            stmt.setBigDecimal(2, produto.getPreco());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setBigDecimal(4, produto.getMarkup()); // Ensure this is set as well
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to retrieve all products
    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produtos"; // Ensure the markup column exists in the database
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                BigDecimal preco = rs.getBigDecimal("preco");
                BigDecimal markup = rs.getBigDecimal("markup"); // Retrieve the markup from the database
                int quantidadeEstoque = rs.getInt("quantidade_estoque");
                produtos.add(new Produto(id, descricao, preco, markup, quantidadeEstoque));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    // Additional methods for updating and deleting products can be added here
}
