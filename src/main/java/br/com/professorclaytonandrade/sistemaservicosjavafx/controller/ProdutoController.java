package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class ProdutoController {

    @FXML
    private TextField nomeProdutoField;

    @FXML
    private TextField precoField;

    @FXML
    private Button cadastrarButton;

    @FXML
    public void initialize() {
        cadastrarButton.setOnAction(event -> cadastrarProduto());
    }

    private void cadastrarProduto() {
        if (validarCampos()) {
            String nomeProduto = nomeProdutoField.getText();
            double preco = Double.parseDouble(precoField.getText());

            // Lógica para cadastrar o produto

            exibirMensagem("Cadastro de Produto", "Produto cadastrado com sucesso!");
            limparCampos();
        }
    }

    private boolean validarCampos() {
        if (nomeProdutoField.getText().isEmpty() || precoField.getText().isEmpty()) {
            exibirMensagemErro("Todos os campos devem ser preenchidos.");
            return false;
        }
        try {
            Double.parseDouble(precoField.getText());
        } catch (NumberFormatException e) {
            exibirMensagemErro("O campo preço deve ser numérico.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        nomeProdutoField.clear();
        precoField.clear();
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
