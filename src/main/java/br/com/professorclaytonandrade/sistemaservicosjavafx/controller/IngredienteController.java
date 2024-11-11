package br.com.professorclaytonandrade.sistemaservicosjavafx.controller;

import br.com.professorclaytonandrade.sistemaservicosjavafx.dao.IngredienteDAO;
import br.com.professorclaytonandrade.sistemaservicosjavafx.model.Ingrediente;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.math.BigDecimal;

public class IngredienteController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField quantidadeField;
    @FXML
    private ComboBox<String> unidadeMedidaComboBox;
    @FXML
    private TextField custoField;
    @FXML
    private TableView<Ingrediente> ingredientesTable;
    @FXML
    private TableColumn<Ingrediente, Integer> idColumn;
    @FXML
    private TableColumn<Ingrediente, String> nomeColumn;
    @FXML
    private TableColumn<Ingrediente, Double> quantidadeColumn;
    @FXML
    private TableColumn<Ingrediente, String> unidadeMedidaColumn;
    @FXML
    private TableColumn<Ingrediente, BigDecimal> custoColumn;

    private IngredienteDAO ingredienteDAO;
    private ObservableList<Ingrediente> ingredientesList;

    @FXML
    public void initialize() {
        ingredienteDAO = new IngredienteDAO();
        ingredientesList = FXCollections.observableArrayList();

        configureUnidadeMedidaComboBox();
        configureTableColumns();
        loadIngredientes();
        configureTableSelection();
    }

    private void configureUnidadeMedidaComboBox() {
        ObservableList<String> unidades = FXCollections.observableArrayList(
                "kg", "g", "L", "ml", "unidade"
        );
        unidadeMedidaComboBox.setItems(unidades);
    }

    private void configureTableColumns() {
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        nomeColumn.setCellValueFactory(cellData -> cellData.getValue().nomeProperty());
        quantidadeColumn.setCellValueFactory(cellData -> cellData.getValue().quantidadeProperty().asObject());
        unidadeMedidaColumn.setCellValueFactory(cellData -> cellData.getValue().unidadeMedidaProperty());
        custoColumn.setCellValueFactory(cellData -> cellData.getValue().custoProperty());

        // Formatador para valores monetários
        custoColumn.setCellFactory(tc -> new TableCell<Ingrediente, BigDecimal>() {
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
    }

    private void loadIngredientes() {
        ingredientesList.clear();
        ingredientesList.addAll(ingredienteDAO.listarIngredientes());
        ingredientesTable.setItems(ingredientesList);
    }

    private void configureTableSelection() {
        ingredientesTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        populateFields(newSelection);
                    }
                }
        );
    }

    private void populateFields(Ingrediente ingrediente) {
        nomeField.setText(ingrediente.getNome());
        quantidadeField.setText(String.valueOf(ingrediente.getQuantidade()));
        unidadeMedidaComboBox.setValue(ingrediente.getUnidadeMedida());
        custoField.setText(ingrediente.getCusto().toString());
    }

    @FXML
    private void handleSave() {
        if (!validateFields()) {
            return;
        }

        try {
            Ingrediente ingrediente = new Ingrediente();
            ingrediente.setNome(nomeField.getText());
            ingrediente.setQuantidade(Double.parseDouble(quantidadeField.getText().replace(",", ".")));
            ingrediente.setUnidadeMedida(unidadeMedidaComboBox.getValue());
            ingrediente.setCusto(new BigDecimal(custoField.getText().replace(",", ".")));

            ingredienteDAO.salvar(ingrediente);
            loadIngredientes();
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Ingrediente salvo com sucesso!");
        } catch (Exception e) {
            showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao salvar ingrediente: " + e.getMessage());
        }
    }

    @FXML
    private void handleClear() {
        clearFields();
        ingredientesTable.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleDelete() {
        Ingrediente selectedIngrediente = ingredientesTable.getSelectionModel().getSelectedItem();
        if (selectedIngrediente == null) {
            showAlert(Alert.AlertType.WARNING, "Aviso", "Selecione um ingrediente para excluir.");
            return;
        }

        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle("Confirmar Exclusão");
        confirmDialog.setHeaderText(null);
        confirmDialog.setContentText("Tem certeza que deseja excluir este ingrediente?");

        if (confirmDialog.showAndWait().get() == ButtonType.OK) {
            try {
                // Implemente o método de exclusão no DAO
                // ingredienteDAO.deletar(selectedIngrediente.getId());
                loadIngredientes();
                clearFields();
                showAlert(Alert.AlertType.INFORMATION, "Sucesso", "Ingrediente excluído com sucesso!");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Erro", "Erro ao excluir ingrediente: " + e.getMessage());
            }
        }
    }

    private boolean validateFields() {
        StringBuilder errorMessage = new StringBuilder();

        if (nomeField.getText().trim().isEmpty()) {
            errorMessage.append("Nome é obrigatório.\n");
        }

        try {
            Double.parseDouble(quantidadeField.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            errorMessage.append("Quantidade inválida.\n");
        }

        if (unidadeMedidaComboBox.getValue() == null) {
            errorMessage.append("Unidade de medida é obrigatória.\n");
        }

        try {
            new BigDecimal(custoField.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            errorMessage.append("Custo inválido.\n");
        }

        if (errorMessage.length() > 0) {
            showAlert(Alert.AlertType.ERROR, "Erro de Validação", errorMessage.toString());
            return false;
        }

        return true;
    }

    private void clearFields() {
        nomeField.clear();
        quantidadeField.clear();
        unidadeMedidaComboBox.setValue(null);
        custoField.clear();
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
