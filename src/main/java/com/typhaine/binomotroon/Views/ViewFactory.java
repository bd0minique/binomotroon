package com.typhaine.binomotroon.Views;

import com.typhaine.binomotroon.Models.Model;
import com.typhaine.binomotroon.Models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewFactory {

    private Stage stage = null;
    private User user = null;

    public ViewFactory(){};

    public void selectUserWindow(ResultSet resultSet) throws SQLException {
        try {
            this.user = new User(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        switch (this.user.getRoleId()) {
            case 1:
                Model.getInstance().getViewFactory().showAdminWindow();
                break;
            case 2:
                Model.getInstance().getViewFactory().showProfessorWindow();
                break;
            case 3:
                Model.getInstance().getViewFactory().showStudentWindow();
                break;
        }
    }
    public void showLoginWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource( "/Fxml/Login.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception error) {
            error.printStackTrace();
        }
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Binomotron");
        stage.show();
    }

    public void showStudentWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource( "/Fxml/Student/StudentMenu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception error) {
            error.printStackTrace();
        }
        stage.close();
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Binomotron");
        stage.show();
    }
    public void showProfessorWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource( "/Fxml/Professor/ProfessorMenu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception error) {
            error.printStackTrace();
        }
        stage.close();
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Binomotron");
        stage.show();
    }
    public void showAdminWindow() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource( "/Fxml/Admin/AdminMenu.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (Exception error) {
            error.printStackTrace();
        }
        stage.close();
        stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Binomotron");
        stage.show();
    }
    public User getUser() {
        return this.user;
    }
    public Stage getStage() {
        return stage;
    }
}
