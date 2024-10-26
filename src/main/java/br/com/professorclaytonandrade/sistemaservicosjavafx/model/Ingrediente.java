package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import java.math.BigDecimal;

public class Ingrediente {
    private int id;
    private String nome;
    private BigDecimal preco;
    private String unidadeMedida;

    // Construtor
    public Ingrediente(String nome, BigDecimal preco, String unidadeMedida) {
        this.nome = nome;
        this.preco = preco;
        this.unidadeMedida = unidadeMedida;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public BigDecimal getPreco() { return preco; }
    public void setPreco(BigDecimal preco) { this.preco = preco; }
    public String getUnidadeMedida() { return unidadeMedida; }
    public void setUnidadeMedida(String unidadeMedida) { this.unidadeMedida = unidadeMedida; }
}
