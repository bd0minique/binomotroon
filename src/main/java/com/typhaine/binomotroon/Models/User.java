package com.typhaine.binomotroon.Models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    public String firstname;
    public String lastname;
    public String password;
    public Integer userId;
    public Integer roleId;
    public Integer promotionId;
    public User (ResultSet resultSet) throws SQLException {
        try {
            this.firstname = resultSet.getString("prenom_utilisateur");
            this.lastname = resultSet.getString("nom_utilisateur");
            this.password = resultSet.getString("mdp_utilisateur");
            this.userId = resultSet.getInt("id_utilisateur");
            this.promotionId = resultSet.getInt("id_promotion");
            this.roleId = resultSet.getInt("id_role");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String getFirstname() {
        return this.firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    public Integer getUserId() {
        return this.userId;
    }
    public Integer getRoleId() {
        return this.roleId;
    }
    public Integer getPromotionId() {
        return this.promotionId;
    }

}
