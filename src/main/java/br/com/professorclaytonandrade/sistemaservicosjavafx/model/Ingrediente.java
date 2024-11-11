package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import javafx.beans.property.*;
import java.math.BigDecimal;
import java.util.Objects;

public class Ingrediente extends BaseEntity {
    private final StringProperty nome = new SimpleStringProperty();
    private final DoubleProperty quantidade = new SimpleDoubleProperty(); // Propriedade quantidade
    private final ObjectProperty<BigDecimal> custo = new SimpleObjectProperty<>(); // Propriedade custo
    private final StringProperty unidadeMedida = new SimpleStringProperty();

    public Ingrediente() {
        // Construtor padrão
    }

    public Ingrediente(String nome, double quantidade, BigDecimal custo, String unidadeMedida) {
        setNome(nome);
        setQuantidade(quantidade);
        setCusto(custo);
        setUnidadeMedida(unidadeMedida);
    }

    public Ingrediente(String nome, BigDecimal preco, String unidadeMedida) {
        super();
    }

    // Properties
    public StringProperty nomeProperty() { return nome; }
    public DoubleProperty quantidadeProperty() { return quantidade; } // Método para quantidadeProperty
    public ObjectProperty<BigDecimal> custoProperty() { return custo; } // Método para custoProperty
    public StringProperty unidadeMedidaProperty() { return unidadeMedida; }

    // Getters e Setters
    public String getNome() { return nome.get(); }
    public void setNome(String value) {
        Objects.requireNonNull(value, "Nome não pode ser nulo");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode estar vazio");
        }
        nome.set(value);
    }

    public double getQuantidade() { return quantidade.get(); }
    public void setQuantidade(double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa");
        }
        quantidade.set(value);
    }

    public BigDecimal getCusto() { return custo.get(); }
    public void setCusto(BigDecimal value) {
        Objects.requireNonNull(value, "Custo não pode ser nulo");
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Custo não pode ser negativo");
        }
        custo.set(value);
    }

    public String getUnidadeMedida() { return unidadeMedida.get(); }
    public void setUnidadeMedida(String value) {
        Objects.requireNonNull(value, "Unidade de medida não pode ser nula");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Unidade de medida não pode estar vazia");
        }
        unidadeMedida.set(value);
    }

    public BigDecimal getPreco() {
        return null;
    }
}
