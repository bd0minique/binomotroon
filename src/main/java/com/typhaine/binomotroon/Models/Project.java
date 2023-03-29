package com.typhaine.binomotroon.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Project {
    public SimpleStringProperty projectName;
    public SimpleStringProperty creatorName;
    public SimpleIntegerProperty idProject;
    public int idCreator;

    public Project(String projectName, String creatorName, int idProject, int idCreator) {
        this.projectName = new SimpleStringProperty(projectName);
        this.creatorName = new SimpleStringProperty(creatorName);
        this.idProject = new SimpleIntegerProperty(idProject);
        this.idCreator = idCreator;
    }

    public String getProjectName() {
        return projectName.get();
    }

    public SimpleStringProperty projectNameProperty() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName.set(projectName);
    }

    public String getCreatorName() {
        return creatorName.get();
    }

    public SimpleStringProperty creatorNameProperty() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName.set(creatorName);
    }

    public int getIdProject() {
        return idProject.get();
    }

    public SimpleIntegerProperty idProjectProperty() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject.set(idProject);
    }

    public int getIdCreator() {
        return idCreator;
    }

    public void setIdCreator(int idCreator) {
        this.idCreator = idCreator;
    }
}
