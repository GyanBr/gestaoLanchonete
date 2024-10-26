package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Despesa {
    private int id;
    private String descricao;
    private BigDecimal valor;
    private LocalDate data;

    // Construtor
    public Despesa(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = LocalDate.now();
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public LocalDate getData() { return data; }
}
