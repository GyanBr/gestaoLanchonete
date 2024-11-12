package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;

public class DespesaController {

    @FXML
    private TextField descricaoField;

    @FXML
    private TextField valorField;

    @FXML
    private DatePicker dataDespesaPicker;

    @FXML
    private Button registrarButton;

    @FXML
    public void initialize() {
        registrarButton.setOnAction(event -> registrarDespesa());
    }

    private void registrarDespesa() {
        if (validarCampos()) {
            String descricao = descricaoField.getText();
            double valor = Double.parseDouble(valorField.getText());
            LocalDate data = dataDespesaPicker.getValue();

            // Aqui, pode-se adicionar a lógica de salvar no banco de dados ou registrar em uma lista

            exibirMensagem("Registro de Despesa", "Despesa registrada com sucesso!");
            limparCampos();
        }
    }

    private boolean validarCampos() {
        if (descricaoField.getText().isEmpty() || valorField.getText().isEmpty() || dataDespesaPicker.getValue() == null) {
            exibirMensagemErro("Todos os campos devem ser preenchidos.");
            return false;
        }
        try {
            Double.parseDouble(valorField.getText());
        } catch (NumberFormatException e) {
            exibirMensagemErro("O campo valor deve ser numérico.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        descricaoField.clear();
        valorField.clear();
        dataDespesaPicker.setValue(null);
    }

    private void exibirMensagem(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    private void exibirMensagemErro(String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erro");
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
