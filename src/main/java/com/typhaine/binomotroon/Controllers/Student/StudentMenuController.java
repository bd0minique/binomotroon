package com.typhaine.binomotroon.Controllers.Student;

import com.typhaine.binomotroon.Models.DatabaseConnection;
import com.typhaine.binomotroon.Models.Model;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentMenuController implements Initializable {
    public MenuButton mProjectStudentGroup;
    public TextArea historyArea;
    public TextArea groupArea;
    public Button historyButton;

    @Override public void initialize(URL location, ResourceBundle resources) {
        addProjectMenuItem();
    }
    public void getHistory(ActionEvent actionEvent) {

        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(
                    "SELECT DISTINCT nom_projet FROM projets\n" +
                            "JOIN groupes ON projets.id_projet = groupes.id_projet\n" +
                            "JOIN utilisateurs_groupes ON groupes.id_groupe = utilisateurs_groupes.id_groupe\n" +
                            "JOIN utilisateurs ON utilisateurs_groupes.id_utilisateur = utilisateurs.id_utilisateur\n" +
                            "WHERE utilisateurs.id_utilisateur = ?");
            statement.setInt(1, Model.getInstance().getViewFactory().getUser().getUserId());

            ResultSet resultSet = statement.executeQuery();

            historyArea.clear();

            while (resultSet.next()) {
                historyArea.appendText(resultSet.getString("nom_projet") + "\n");
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void addProjectMenuItem() {
        ArrayList<MenuItem> listProjectItem = new ArrayList<>();

        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM projets" );

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String projectName = resultSet.getString("nom_projet");

                MenuItem menuItem = new MenuItem(projectName);
                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent e) {
                        getProjectStudentGroups(projectName);
                    }
                });

                listProjectItem.add(menuItem);
            }

            mProjectStudentGroup.getItems().addAll(listProjectItem);

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void getProjectStudentGroups (String projectName) {
        try {
            StringBuilder groups = new StringBuilder("Project " + projectName);

            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM `groupes`\n" +
                            "join projets on groupes.id_projet = projets.id_projet\n" +
                            "where projets.nom_projet = ?");
            statement.setString(1, projectName);
            ResultSet resultSetGroup = statement.executeQuery();

            while (resultSetGroup.next()) {
                int groupId = resultSetGroup.getInt("id_groupe");
                groups.append("\nGroup " + groupId);

                statement = DatabaseConnection.getInstance().getConnection().prepareStatement(
                        "SELECT * FROM `utilisateurs`\n" +
                                "join utilisateurs_groupes on utilisateurs.id_utilisateur = utilisateurs_groupes.id_utilisateur\n" +
                                "where utilisateurs_groupes.id_groupe = ?");

                statement.setInt(1,groupId);
                ResultSet resultSetUser = statement.executeQuery();

                while (resultSetUser.next()) {
                    String firstName = resultSetUser.getString("prenom_utilisateur");
                    String lastName = resultSetUser.getString("nom_utilisateur");
                    groups.append("\n\t" + firstName + " " + lastName);
                }
            }

            groupArea.setText(groups.toString());

        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
