package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dao.DespesaDAO;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Despesa;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.math.BigDecimal;

public class DespesaController {
    @FXML private TextArea descricaoTextArea;
    @FXML private TextField valorTextField;
    @FXML private DatePicker dataPicker;
    @FXML private TableView<Despesa> despesasTableView;
    @FXML private TableColumn<Despesa, LocalDate> dataColumn;
    @FXML private TableColumn<Despesa, String> descricaoColumn;
    @FXML private TableColumn<Despesa, BigDecimal> valorColumn;

    private DespesaDAO despesaDAO = new DespesaDAO();
    private ObservableList<Despesa> despesas = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dataPicker.setValue(LocalDate.now());
        configurarTabela();
        carregarDespesas();
    }

    private void configurarTabela() {
        dataColumn.setCellValueFactory(cellData -> cellData.getValue().dataProperty());
        descricaoColumn.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
        valorColumn.setCellValueFactory(cellData -> cellData.getValue().valorProperty());

        // Formatador para valores monetários
        valorColumn.setCellFactory(tc -> new TableCell<>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(String.format("R$ %.2f", value));
                }
            }
        });
    }

    private void carregarDespesas() {
        // Aqui você deve carregar as despesas do banco de dados
        // despesas.addAll(despesaDAO.listarDespesas());
        despesasTableView.setItems(despesas);
    }

    @FXML
    private void handleSalvar() {
        try {
            if (!validarCampos()) {
                return;
            }

            Despesa despesa = new Despesa();
            despesa.setDescricao(descricaoTextArea.getText());
            despesa.setValor(new BigDecimal(valorTextField.getText().replace(",", ".")));
            despesa.setData(dataPicker.getValue());

            despesaDAO.registrarDespesa(despesa);

            despesas.add(despesa);
            limparCampos();
            mostrarAlerta("Sucesso", "Despesa registrada com sucesso!");
        } catch (Exception e) {
            mostrarErro("Erro ao registrar despesa", e.getMessage());
        }
    }

    private boolean validarCampos() {
        if (descricaoTextArea.getText().trim().isEmpty()) {
            mostrarErro("Erro de validação", "A descrição é obrigatória.");
            return false;
        }
        try {
            new BigDecimal(valorTextField.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            mostrarErro("Erro de validação", "Valor inválido.");
            return false;
        }
        if (dataPicker.getValue() == null) {
            mostrarErro("Erro de validação", "A data é obrigatória.");
            return false;
        }
        return true;
    }

    @FXML
    private void handleLimpar() {
        limparCampos();
    }

    private void limparCampos() {
        descricaoTextArea.clear();
        valorTextField.clear();
        dataPicker.setValue(LocalDate.now());
    }

    private void mostrarAlerta(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
