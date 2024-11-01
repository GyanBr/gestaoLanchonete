package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Produto {
    private int id;
    private String descricao;
    private BigDecimal preco; // Custo do produto
    private BigDecimal markup; // Percentual de markup
    private int quantidadeEstoque;

    // Constructor
    public Produto(int id, String descricao, BigDecimal preco, BigDecimal markup, int quantidadeEstoque) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.markup = markup; // Agora o markup é inicializado
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigDecimal getMarkup() {
        return markup; // Getter para o markup
    }

    public void setMarkup(BigDecimal markup) {
        this.markup = markup; // Setter para o markup
    }

    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    // Método para calcular o preço de venda
    public BigDecimal getPrecoVenda() {
        // Calcula o preço de venda baseado no percentual de markup
        BigDecimal markupAmount = preco.multiply(markup).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        return preco.add(markupAmount);
    }
}
