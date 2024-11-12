package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class IngredienteController {

    @FXML
    private TextField nomeIngredienteField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private Button cadastrarButton;

    @FXML
    public void initialize() {
        cadastrarButton.setOnAction(event -> cadastrarIngrediente());
    }

    private void cadastrarIngrediente() {
        if (validarCampos()) {
            String nomeIngrediente = nomeIngredienteField.getText();
            double quantidade = Double.parseDouble(quantidadeField.getText());

            // Lógica para cadastrar o ingrediente

            exibirMensagem("Cadastro de Ingrediente", "Ingrediente cadastrado com sucesso!");
            limparCampos();
        }
    }

    private boolean validarCampos() {
        if (nomeIngredienteField.getText().isEmpty() || quantidadeField.getText().isEmpty()) {
            exibirMensagemErro("Todos os campos devem ser preenchidos.");
            return false;
        }
        try {
            Double.parseDouble(quantidadeField.getText());
        } catch (NumberFormatException e) {
            exibirMensagemErro("Quantidade deve ser numérica.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        nomeIngredienteField.clear();
        quantidadeField.clear();
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
