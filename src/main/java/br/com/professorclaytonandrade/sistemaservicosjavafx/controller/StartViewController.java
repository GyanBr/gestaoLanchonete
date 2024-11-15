package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

public class StartViewController {

    @FXML
    private Text tituloText;

    @FXML
    private Button produtosButton;

    @FXML
    private Button ingredientesButton;

    @FXML
    private Button vendasButton;

    @FXML
    private Button despesasButton;

    @FXML
    private Label rodapeLabel;

    @FXML
    public void initialize() {
        // Código de inicialização se necessário
    }

    @FXML
    private void handleProdutos(ActionEvent event) {
        // Lógica para abrir a tela de gerenciamento de produtos
        System.out.println("Abrindo a tela de Gerenciamento de Produtos...");
    }

    @FXML
    private void handleIngredientes(ActionEvent event) {
        // Lógica para abrir a tela de gerenciamento de ingredientes
        System.out.println("Abrindo a tela de Gerenciamento de Ingredientes...");
    }

    @FXML
    private void handleVendas(ActionEvent event) {
        // Lógica para abrir a tela de registro de vendas
        System.out.println("Abrindo a tela de Registro de Vendas...");
    }

    @FXML
    private void handleDespesas(ActionEvent event) {
        // Lógica para abrir a tela de controle de despesas
        System.out.println("Abrindo a tela de Controle de Despesas...");
    }
}
