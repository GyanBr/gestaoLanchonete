package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Venda {
    private int id;
    private LocalDate data;
    private Produto produto;
    private int quantidade;
    private BigDecimal precoTotal;

    // Construtor
    public Venda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoTotal = produto.getPrecoVenda().multiply(new BigDecimal(quantidade));
        this.data = LocalDate.now();
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public LocalDate getData() { return data; }
    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public BigDecimal getPrecoTotal() { return precoTotal; }

}
