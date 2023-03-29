package com.typhaine.binomotroon.Controllers.Professor;

import com.typhaine.binomotroon.Models.DatabaseConnection;
import com.typhaine.binomotroon.Models.Project;
import com.typhaine.binomotroon.Models.Promotion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ProfessorMenuController implements Initializable {
    @FXML public TableView<Project> projectTable;
    @FXML public TableView<Promotion> promotionTable;
    @FXML public TableColumn<Project, String> projectName;
    @FXML public TableColumn<Project, Integer> projectId;
    @FXML public TableColumn<Project, String> projectCreator;
    @FXML public TableColumn<Promotion, String> promoName;
    @FXML public TableColumn<Promotion, Integer> promoId;
    @FXML public TableColumn<Promotion, LocalDate> promoCreationDate;
    public TextField selectedPromo;
    public TextField groupSize;
    public TextField projecToProcess;
    public AnchorPane professorAnchorPane;
    public ObservableList<Project> projects = FXCollections.observableArrayList();
    public ObservableList<Promotion> promotions = FXCollections.observableArrayList();

    @Override public void initialize (URL location, ResourceBundle resources) {

        projectName.setCellValueFactory(new PropertyValueFactory<Project, String>("projectName"));
        projectCreator.setCellValueFactory(new PropertyValueFactory<Project , String>("creatorName"));
        projectId.setCellValueFactory(new PropertyValueFactory<Project, Integer>("idProject"));

        promoCreationDate.setCellValueFactory(new PropertyValueFactory<Promotion, LocalDate>("startDate"));
        promoName.setCellValueFactory(new PropertyValueFactory<Promotion, String>("promotionName"));
        promoId.setCellValueFactory(new PropertyValueFactory<Promotion, Integer>("idPromotion"));

        getProjects();
        getPromotion();
        projectTable.setItems(this.projects);
        promotionTable.setItems(this.promotions);
    }

    public void getProjects() {

        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM projets");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                this.projects.add(new Project(resultSet.getString("nom_projet"), resultSet.getString("nom_createur"),
                         resultSet.getInt("id_projet"), resultSet.getInt("id_utilisateur")));

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void getPromotion () {

        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM promotions");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                this.promotions.add(new Promotion(resultSet.getString("nom_promotion"),
                        resultSet.getInt("id_promotion"), resultSet.getDate("date_entree_promotion").toLocalDate()));

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handelButtonAdd(ActionEvent actionEvent) {
        Stage stage = (Stage) professorAnchorPane.getScene().getWindow();

        Alert.AlertType type = Alert.AlertType.CONFIRMATION;
        Alert alert = new Alert(type,"");

        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

        alert.getDialogPane().setContentText("Voulez-vous ajouter un projet?");
        alert.getDialogPane().setHeaderText("test");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            System.out.println("OK");
        } else if (result.get() == ButtonType.CANCEL) {
            System.out.println("Cancel");
        }
    }

    public void checkSize(ActionEvent actionEvent) {
        System.out.println(groupSize.getText());
    }

    public void checkPromo(ActionEvent actionEvent) {
        System.out.println(selectedPromo.getText());
    }
}
