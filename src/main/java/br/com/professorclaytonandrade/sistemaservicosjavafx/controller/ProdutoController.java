package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ProdutoController {

    @FXML
    private TableColumn<?, ?> descricaoColumn;

    @FXML
    private TextField descricaoField;

    @FXML
    private Button excluirButton;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private Button limparButton;

    @FXML
    private TableColumn<?, ?> markupColumn;

    @FXML
    private TextField markupField;

    @FXML
    private TableColumn<?, ?> precoColumn;

    @FXML
    private TextField precoField;

    @FXML
    private TableView<?> produtosTable;

    @FXML
    private TableColumn<?, ?> quantidadeColumn;

    @FXML
    private TextField quantidadeField;

    @FXML
    private Button salvarButton;

}
