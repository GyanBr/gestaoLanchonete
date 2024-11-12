package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.time.LocalDate;

public class VendaController {

    @FXML
    private TextField produtoField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField valorTotalField;

    @FXML
    private DatePicker dataVendaPicker;

    @FXML
    private Button registrarButton;

    @FXML
    public void initialize() {
        registrarButton.setOnAction(event -> registrarVenda());
    }

    private void registrarVenda() {
        if (validarCampos()) {
            String produto = produtoField.getText();
            int quantidade = Integer.parseInt(quantidadeField.getText());
            double valorTotal = Double.parseDouble(valorTotalField.getText());
            LocalDate dataVenda = dataVendaPicker.getValue();

            // Adicione a lógica de registro da venda no banco de dados ou em outra estrutura de dados

            exibirMensagem("Registro de Venda", "Venda registrada com sucesso!");
            limparCampos();
        }
    }

    private boolean validarCampos() {
        if (produtoField.getText().isEmpty() || quantidadeField.getText().isEmpty() || valorTotalField.getText().isEmpty() || dataVendaPicker.getValue() == null) {
            exibirMensagemErro("Todos os campos devem ser preenchidos.");
            return false;
        }
        try {
            Integer.parseInt(quantidadeField.getText());
            Double.parseDouble(valorTotalField.getText());
        } catch (NumberFormatException e) {
            exibirMensagemErro("Quantidade e valor total devem ser numéricos.");
            return false;
        }
        return true;
    }

    private void limparCampos() {
        produtoField.clear();
        quantidadeField.clear();
        valorTotalField.clear();
        dataVendaPicker.setValue(null);
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
