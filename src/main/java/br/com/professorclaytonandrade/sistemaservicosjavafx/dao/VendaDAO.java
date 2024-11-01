package br.com.professorclaytonandrade.sistemaservicosjavafx.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Venda; // Adjust the package path as needed
import br.com.professorclaytonandrade.sistemaservicosjavafx.config.conexao.ConexaoBanco;

public class VendaDAO {

    public void registrarVenda(Venda venda) {
        String sql = "INSERT INTO vendas (data, produto_id, quantidade, preco_total) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoBanco.obterConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(venda.getData()));
            stmt.setInt(2, venda.getProduto().getId());
            stmt.setInt(3, venda.getQuantidade());
            stmt.setBigDecimal(4, venda.getPrecoTotal());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

