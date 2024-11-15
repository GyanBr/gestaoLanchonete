package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class IngredienteController {

    @FXML
    private Label tituloLabel;

    @FXML
    private Label nomeLabel;

    @FXML
    private Label precoLabel;

    @FXML
    private Label quantidadeLabel;

    @FXML
    private Label unidadeLabel;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TextField precoTextField;

    @FXML
    private TextField quantidadeTextField;

    @FXML
    private ComboBox<String> unidadeMedidaComboBox;

    @FXML
    private Button limparButton;

    @FXML
    private Button salvarButton;

    @FXML
    private Button excluirButton;

    @FXML
    private TableView<?> ingredientesTableView;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nomeColumn;

    @FXML
    private TableColumn<?, ?> quantidadeColumn;

    @FXML
    private TableColumn<?, ?> precoColumn;

    @FXML
    private TableColumn<?, ?> unidadeColumn;

    @FXML
    public void initialize() {
        // Código de inicialização, como configurar as opções do ComboBox ou as colunas da tabela
        unidadeMedidaComboBox.getItems().addAll("Kg", "g", "L", "ml", "unidade");
    }

    @FXML
    private void handleSalvar(ActionEvent event) {
        // Lógica para salvar o ingrediente
        // Exemplo: validar entrada, criar um novo objeto Ingrediente e salvar em um banco de dados ou lista
    }

    @FXML
    private void handleLimpar(ActionEvent event) {
        // Lógica para limpar os campos de entrada
        nomeTextField.clear();
        precoTextField.clear();
        quantidadeTextField.clear();
        unidadeMedidaComboBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleExcluir(ActionEvent event) {
        // Lógica para excluir o ingrediente selecionado na tabela
        // Exemplo: verificar seleção e remover da lista ou banco de dados
    }
}
