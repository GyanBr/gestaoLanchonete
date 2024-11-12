package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class StartViewController {

    @FXML
    private Button iniciarRegistroButton;

    @FXML
    private Button cadastrarProdutoButton;

    @FXML
    private Button cadastrarIngredienteButton;

    @FXML
    public void initialize() {
        iniciarRegistroButton.setOnAction(event -> iniciarRegistro());
        cadastrarProdutoButton.setOnAction(event -> handleProdutos());
        cadastrarIngredienteButton.setOnAction(event -> handleIngredientes());
    }

    @FXML
    private void iniciarRegistro() {
        // Lógica para iniciar o registro
    }

    @FXML
    private void handleProdutos() {
        // Lógica para o evento handleProdutos
    }

    @FXML
    private void handleIngredientes() {
        // Lógica para o evento handleIngredientes
    }
}
