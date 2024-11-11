package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import javafx.beans.property.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class Produto extends BaseEntity {
    private final StringProperty descricao = new SimpleStringProperty();
    private final ObjectProperty<BigDecimal> preco = new SimpleObjectProperty<>();
    private final ObjectProperty<BigDecimal> markup = new SimpleObjectProperty<>();
    private final IntegerProperty quantidadeEstoque = new SimpleIntegerProperty();

    public Produto() {
        this.markup.set(BigDecimal.ZERO);
        this.quantidadeEstoque.set(0);
    }

    // Properties
    public StringProperty descricaoProperty() { return descricao; }
    public ObjectProperty<BigDecimal> precoProperty() { return preco; }
    public ObjectProperty<BigDecimal> markupProperty() { return markup; }
    public IntegerProperty quantidadeEstoqueProperty() { return quantidadeEstoque; }

    // Getters e Setters
    public String getDescricao() { return descricao.get(); }
    public void setDescricao(String value) {
        Objects.requireNonNull(value, "Descrição não pode ser nula");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode estar vazia");
        }
        descricao.set(value);
    }

    public BigDecimal getPreco() { return preco.get(); }
    public void setPreco(BigDecimal value) {
        Objects.requireNonNull(value, "Preço não pode ser nulo");
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        preco.set(value);
    }

    public BigDecimal getMarkup() { return markup.get(); }
    public void setMarkup(BigDecimal value) {
        Objects.requireNonNull(value, "Markup não pode ser nulo");
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Markup não pode ser negativo");
        }
        markup.set(value);
    }

    public int getQuantidadeEstoque() { return quantidadeEstoque.get(); }
    public void setQuantidadeEstoque(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Quantidade em estoque não pode ser negativa");
        }
        quantidadeEstoque.set(value);
    }

    public BigDecimal getPrecoVenda() {
        BigDecimal precoBase = getPreco();
        if (precoBase == null || getMarkup() == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal markupAmount = precoBase.multiply(getMarkup())
                .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        return precoBase.add(markupAmount);
    }

    public void baixarEstoque(int quantidade) {
        if (quantidade > getQuantidadeEstoque()) {
            throw new IllegalStateException("Quantidade insuficiente em estoque");
        }
        setQuantidadeEstoque(getQuantidadeEstoque() - quantidade);
    }
}
