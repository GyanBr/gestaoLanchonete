package br.com.professorclaytonandrade.sistemaservicosjavafx.model;

import javafx.beans.property.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Despesa extends BaseEntity {
    private final StringProperty descricao = new SimpleStringProperty();
    private final ObjectProperty<BigDecimal> valor = new SimpleObjectProperty<>();
    private final ObjectProperty<LocalDate> data = new SimpleObjectProperty<>();

    public Despesa() {
        data.set(LocalDate.now());
    }

    // Properties
    public StringProperty descricaoProperty() { return descricao; }
    public ObjectProperty<BigDecimal> valorProperty() { return valor; }
    public ObjectProperty<LocalDate> dataProperty() { return data; }

    // Getters e Setters
    public String getDescricao() { return descricao.get(); }
    public void setDescricao(String value) {
        Objects.requireNonNull(value, "Descrição não pode ser nula");
        if (value.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode estar vazia");
        }
        descricao.set(value);
    }

    public BigDecimal getValor() { return valor.get(); }
    public void setValor(BigDecimal value) {
        Objects.requireNonNull(value, "Valor não pode ser nulo");
        if (value.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo");
        }
        valor.set(value);
    }

    public LocalDate getData() { return data.get(); }
    public void setData(LocalDate value) {
        Objects.requireNonNull(value, "Data não pode ser nula");
        data.set(value);
    }
}