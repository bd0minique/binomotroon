package com.typhaine.binomotroon.Controllers.Student;

import com.typhaine.binomotroon.Models.DatabaseConnection;
import com.typhaine.binomotroon.Models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student extends User {

    public Student(ResultSet resultSet) throws SQLException {
        super(resultSet);
    }

    public ArrayList<String> getHistory () {
        ArrayList<String> listProjects= new ArrayList<>();

        try {

            PreparedStatement statement = DatabaseConnection.getInstance().getConnection().prepareStatement(
                    "SELECT DISTINCT nom_projet FROM projets\n" +
                            "JOIN groupes ON projets.id_projet = groupes.id_projet\n" +
                            "JOIN utilisateurs_groupes ON groupes.id_groupe = utilisateurs_groupes.id_groupe\n" +
                            "JOIN utilisateurs ON utilisateurs_groupes.id_utilisateur = utilisateurs.id_utilisateur\n" +
                            "WHERE utilisateurs.id_utilisateur = ?");
            statement.setInt(1, this.getUserId());

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                listProjects.add(resultSet.getString("nom_projet"));
            }

        } catch (SQLException error) {
            error.printStackTrace();
        }
        return listProjects;
    }
}
