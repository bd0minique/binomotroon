package com.typhaine.binomotroon.Controllers;

import com.typhaine.binomotroon.Models.DatabaseConnection;
import com.typhaine.binomotroon.Models.Model;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {
    public TextField userEmail;
    public TextField userPwd;
    public Button loginButton;
    public Label loginErrorLabel;

    public void openView(ActionEvent actionEvent) {

        try {
            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM utilisateurs WHERE mail_utilisateur = ? AND mdp_utilisateur = ?");
            statement.setString(1, userEmail.getText());
            statement.setString(2, userPwd.getText());

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Model.getInstance().getViewFactory().selectUserWindow(resultSet);
            } else loginErrorLabel.setText("Login failed.");
        }
        catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
