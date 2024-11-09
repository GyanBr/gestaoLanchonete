package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DespesaController {

    @FXML
    private TableColumn<?, ?> dataColumn;

    @FXML
    private DatePicker dataPicker;

    @FXML
    private TableColumn<?, ?> descricaoColumn;

    @FXML
    private TextArea descricaoTextArea;

    @FXML
    private TableView<?> despesasTableView;

    @FXML
    private Button limparButton;

    @FXML
    private Button salvarButton;

    @FXML
    private TableColumn<?, ?> valorColumn;

    @FXML
    private TextField valorTextField;

    @FXML
    void handleLimpar(ActionEvent event) {

    }

    @FXML
    void handleSalvar(ActionEvent event) {

    }

}
