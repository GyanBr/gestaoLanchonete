package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class VendaController {

    @FXML
    private DatePicker dataPicker;

    @FXML
    private Button limparButton;

    @FXML
    private TextField precoTotalTextField;

    @FXML
    private ComboBox<?> produtoComboBox;

    @FXML
    private TextField quantidadeTextField;

    @FXML
    private Button salvarButton;

    @FXML
    void handleLimpar(ActionEvent event) {

    }

    @FXML
    void handleSalvar(ActionEvent event) {

    }

}
