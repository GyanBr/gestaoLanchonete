package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import javafx.beans.property.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Venda extends BaseEntity {
    private final ObjectProperty<LocalDate> data = new SimpleObjectProperty<>();
    private final ObjectProperty<Produto> produto = new SimpleObjectProperty<>();
    private final IntegerProperty quantidade = new SimpleIntegerProperty();
    private final ObjectProperty<BigDecimal> precoTotal = new SimpleObjectProperty<>();

    public Venda() {
        data.set(LocalDate.now());
        // Listener para atualizar preço total quando produto ou quantidade mudar
        quantidade.addListener((obs, oldVal, newVal) -> calcularPrecoTotal());
        produto.addListener((obs, oldVal, newVal) -> calcularPrecoTotal());
    }

    // Properties
    public ObjectProperty<LocalDate> dataProperty() { return data; }
    public ObjectProperty<Produto> produtoProperty() { return produto; }
    public IntegerProperty quantidadeProperty() { return quantidade; }
    public ObjectProperty<BigDecimal> precoTotalProperty() { return precoTotal; }

    // Getters e Setters
    public LocalDate getData() { return data.get(); }
    public void setData(LocalDate value) {
        Objects.requireNonNull(value, "Data não pode ser nula");
        data.set(value);
    }

    public Produto getProduto() { return produto.get(); }
    public void setProduto(Produto value) {
        Objects.requireNonNull(value, "Produto não pode ser nulo");
        produto.set(value);
    }

    public int getQuantidade() { return quantidade.get(); }
    public void setQuantidade(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");
        }
        quantidade.set(value);
    }

    public BigDecimal getPrecoTotal() { return precoTotal.get(); }

    private void calcularPrecoTotal() {
        Produto p = getProduto();
        if (p != null) {
            precoTotal.set(p.getPrecoVenda().multiply(new BigDecimal(getQuantidade())));
        }
    }

    public void efetivarVenda() {
        if (getProduto() == null) {
            throw new IllegalStateException("Produto não selecionado");
        }
        getProduto().baixarEstoque(getQuantidade());
    }

    public void setPrecoTotal(BigDecimal bigDecimal) {
    }
}