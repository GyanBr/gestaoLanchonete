package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dao.VendaDAO;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Venda;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.math.BigDecimal;

public class VendaController {
    @FXML private DatePicker dataPicker;
    @FXML private ComboBox<Produto> produtoComboBox;
    @FXML private TextField quantidadeTextField;
    @FXML private TextField precoTotalTextField;
    @FXML private Button salvarButton;
    @FXML private Button limparButton;

    private VendaDAO vendaDAO = new VendaDAO();
    private ObservableList<Produto> produtos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        dataPicker.setValue(LocalDate.now());
        carregarProdutos();
        configurarEventos();
    }

    private void carregarProdutos() {
        // Aqui você deve carregar os produtos do banco de dados
        // produtos.addAll(produtoDAO.listarProdutos());
        produtoComboBox.setItems(produtos);
    }

    private void configurarEventos() {
        quantidadeTextField.textProperty().addListener((obs, oldValue, newValue) -> {
            calcularPrecoTotal();
        });

        produtoComboBox.valueProperty().addListener((obs, oldValue, newValue) -> {
            calcularPrecoTotal();
        });
    }

    private void calcularPrecoTotal() {
        try {
            Produto produto = produtoComboBox.getValue();
            if (produto != null && !quantidadeTextField.getText().isEmpty()) {
                int quantidade = Integer.parseInt(quantidadeTextField.getText());
                BigDecimal precoTotal = produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
                precoTotalTextField.setText(precoTotal.toString());
            }
        } catch (NumberFormatException e) {
            // Tratar erro de formato
            precoTotalTextField.setText("0.00");
        }
    }

    @FXML
    private void handleSalvar() {
        try {
            Venda venda = new Venda();
            venda.setData(dataPicker.getValue());
            venda.setProduto(produtoComboBox.getValue());
            venda.setQuantidade(Integer.parseInt(quantidadeTextField.getText()));
            venda.setPrecoTotal(new BigDecimal(precoTotalTextField.getText()));

            vendaDAO.registrarVenda(venda);

            limparCampos();
            mostrarAlerta("Sucesso", "Venda registrada com sucesso!");
        } catch (Exception e) {
            mostrarErro("Erro ao registrar venda", e.getMessage());
        }
    }

    @FXML
    private void handleLimpar() {
        limparCampos();
    }

    private void limparCampos() {
        dataPicker.setValue(LocalDate.now());
        produtoComboBox.setValue(null);
        quantidadeTextField.clear();
        precoTotalTextField.clear();
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
