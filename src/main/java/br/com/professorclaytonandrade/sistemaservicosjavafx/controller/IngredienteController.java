package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class IngredienteController {

    @FXML
    private Button excluirButton;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableView<?> ingredientesTableView;

    @FXML
    private Button limparButton;

    @FXML
    private TableColumn<?, ?> nomeColumn;

    @FXML
    private TextField nomeTextField;

    @FXML
    private TableColumn<?, ?> precoColumn;

    @FXML
    private TextField precoTextField;

    @FXML
    private TableColumn<?, ?> quantidadeColumn;

    @FXML
    private TextField quantidadeTextField;

    @FXML
    private Button salvarButton;

    @FXML
    private TableColumn<?, ?> unidadeColumn;

    @FXML
    private ComboBox<?> unidadeMedidaComboBox;

}
