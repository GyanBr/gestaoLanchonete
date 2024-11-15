package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

public class ProdutoController {

    @FXML
    private Label tituloLabel;

    @FXML
    private Label descricaoLabel;

    @FXML
    private Label precoLabel;

    @FXML
    private Label quantidadeLabel;

    @FXML
    private Label markupLabel;

    @FXML
    private TextField descricaoField;

    @FXML
    private TextField precoField;

    @FXML
    private TextField quantidadeField;

    @FXML
    private TextField markupField;

    @FXML
    private Button salvarButton;

    @FXML
    private Button limparButton;

    @FXML
    private Button excluirButton;

    @FXML
    private TableView<?> produtosTable;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> descricaoColumn;

    @FXML
    private TableColumn<?, ?> precoColumn;

    @FXML
    private TableColumn<?, ?> quantidadeColumn;

    @FXML
    private TableColumn<?, ?> markupColumn;

    @FXML
    public void initialize() {
        // Código de inicialização, como vinculação de colunas da tabela
    }

    @FXML
    private void handleSalvar(ActionEvent event) {
        // Lógica para salvar o produto
        // Exemplo: validar entrada, criar um novo objeto Produto, salvar no banco ou lista
    }

    @FXML
    private void handleLimpar(ActionEvent event) {
        // Lógica para limpar os campos de entrada
        descricaoField.clear();
        precoField.clear();
        quantidadeField.clear();
        markupField.clear();
    }

    @FXML
    private void handleExcluir(ActionEvent event) {
        // Lógica para excluir o produto selecionado na tabela
        // Exemplo: verificar seleção e remover da lista ou banco de dados
    }
}
