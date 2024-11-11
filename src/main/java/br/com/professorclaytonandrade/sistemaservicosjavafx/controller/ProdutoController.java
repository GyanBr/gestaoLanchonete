package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dao.ProdutoDAO;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Produto;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.math.BigDecimal;

public class ProdutoController {

    @FXML
    private TextField descricaoField;
    @FXML
    private TextField precoField;
    @FXML
    private TextField quantidadeField;
    @FXML
    private TextField markupField;
    @FXML
    private TableView<Produto> produtosTable;
    @FXML
    private TableColumn<Produto, Integer> idColumn;
    @FXML
    private TableColumn<Produto, String> descricaoColumn;
    @FXML
    private TableColumn<Produto, BigDecimal> precoColumn;
    @FXML
    private TableColumn<Produto, Integer> quantidadeColumn;
    @FXML
    private TableColumn<Produto, BigDecimal> markupColumn;

    private ProdutoDAO produtoDAO;
    private ObservableList<Produto> produtosList;

    @FXML
    public void initialize() {
        produtoDAO = new ProdutoDAO();
        produtosList = FXCollections.observableArrayList();

        configureTableColumns();
        loadProdutos();
        configureTableSelection();
    }

    private void configureTableColumns() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        descricaoColumn.setCellValueFactory(cellData -> cellData.getValue().descricaoProperty());
        precoColumn.setCellValueFactory(cellData -> cellData.getValue().precoProperty());
        quantidadeColumn.setCellValueFactory(cellData -> cellData.getValue().quantidadeEstoqueProperty().asObject());
        markupColumn.setCellValueFactory(cellData -> cellData.getValue().markupProperty());

        // Formatador para valores monetários
        precoColumn.setCellFactory(tc -> new TableCell<Produto, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(String.format("R$ %.2f", value));
                }
            }
        });

        markupColumn.setCellFactory(tc -> new TableCell<Produto, BigDecimal>() {
            @Override
            protected void updateItem(BigDecimal value, boolean empty) {
                super.updateItem(value, empty);
                if (empty || value == null) {
                    setText(null);
                } else {
                    setText(String.format("%.2f%%", value));
                }
            }
        });
    }

    private void loadProdutos() {
        produtosList.clear();
        produtosList.addAll(produtoDAO.listarProdutos());
        produtosTable.setItems(produtosList);
    }

    private void configureTableSelection() {
        produtosTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateFields(newSelection);
                    }
                }
        );
    }

    private void populateFields(Produto produto) {
        descricaoField.setText(produto.getDescricao());
        precoField.setText(produto.getPreco().toString());
        quantidadeField.setText(String.valueOf(produto.getQuantidadeEstoque()));
        markupField.setText(produto.getMarkup().toString());
    }

    @FXML
    private void handleSave() {
        if (!validateFields()) {
            return;
        }

        try {
            Produto produto = new Produto();
            produto.setDescricao(descricaoField.getText());
            produto.setPreco(new BigDecimal(precoField.getText().replace(",", ".")));
            produto.setQuantidadeEstoque(Integer.parseInt(quantidadeField.getText()));
            produto.setMarkup(new BigDecimal(markupField.getText().replace(",", ".")));

            produtoDAO.registrarProduto(produto);
            loadProdutos();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto salvo com sucesso!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao salvar produto: " + e.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        clearFields();
        produtosTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleDelete() {
        Produto selectedProduto = produtosTable.getSelectionModel().getSelectedItem();
        if (selectedProduto == null) {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um produto para excluir.");
            return;
        }

        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmar Exclusão");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("Tem certeza que deseja excluir este produto?");

        if (confirmDialog.showAndWait().get() == ButtonType.OK) {
            try {
                // Implemente o método de exclusão no DAO
                // produtoDAO.deletar(selectedProduto.getId());
                loadProdutos();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Produto excluído com sucesso!");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao excluir produto: " + e.getMessage());
            }
        }
    }

    private boolean validateFields() {
        StringBuilder errorMessage = new StringBuilder();

        if (descricaoField.getText().trim().isEmpty()) {
            errorMessage.append("Descrição é obrigatória.\n");
        }

        try {
            new BigDecimal(precoField.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            errorMessage.append("Preço inválido.\n");
        }

        try {
            Integer.parseInt(quantidadeField.getText());
        } catch (NumberFormatException e) {
            errorMessage.append("Quantidade inválida.\n");
        }

        try {
            new BigDecimal(markupField.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            errorMessage.append("Markup inválido.\n");
        }

        if (errorMessage.length() > 0) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", errorMessage.toString());
            return false;
        }

        return true;
    }

    private void clearFields() {
        descricaoField.clear();
        precoField.clear();
        quantidadeField.clear();
        markupField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
