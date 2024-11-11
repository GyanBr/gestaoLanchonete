package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Modality;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class StartViewController {

    @FXML
    private Button produtosButton;

    @FXML
    private Button ingredientesButton;

    @FXML
    private Button vendasButton;

    @FXML
    private Button despesasButton;

    /**
     * Método para abrir uma nova janela
     * @param fxmlPath caminho do arquivo FXML
     * @param title título da janela
     */
    private void abrirJanela(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/caminho/do/arquivo.fxml"));

            Stage stage = new Stage();
            stage.setTitle(title);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(produtosButton.getScene().getWindow());

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            mostrarErro("Erro ao abrir janela",
                    "Não foi possível abrir a tela solicitada.\nErro: " + e.getMessage());
        }
    }

    /**
     * Exibe uma mensagem de erro
     */
    private void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    @FXML
    private void handleProdutos() {
        abrirJanela("/fxml/produto-form.fxml", "Gerenciamento de Produtos");
    }

    @FXML
    private void handleIngredientes() {
        abrirJanela("/fxml/ingrediente-view.fxml", "Cadastro de Ingredientes");
    }

    @FXML
    private void handleVendas() {
        abrirJanela("/fxml/venda-form.fxml", "Registro de Venda");
    }

    @FXML
    private void handleDespesas() {
        abrirJanela("/fxml/despesa-form.fxml", "Registro de Despesa");
    }

    @FXML
    private void initialize() {
        // Adiciona efeitos hover nos botões
        configurarHoverEfeito(produtosButton, "#2980b9", "#3498db");
        configurarHoverEfeito(ingredientesButton, "#27ae60", "#2ecc71");
        configurarHoverEfeito(vendasButton, "#c0392b", "#e74c3c");
        configurarHoverEfeito(despesasButton, "#8e44ad", "#9b59b6");
    }

    /**
     * Configura efeito hover para os botões
     */
    private void configurarHoverEfeito(Button button, String corHover, String corNormal) {
        button.setOnMouseEntered(e ->
                button.setStyle(button.getStyle().replace(corNormal, corHover))
        );

        button.setOnMouseExited(e ->
                button.setStyle(button.getStyle().replace(corHover, corNormal))
        );
    }
}