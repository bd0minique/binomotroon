package com.typhaine.binomotroon.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Promotion {
    public SimpleStringProperty promotionName;
    public SimpleIntegerProperty idPromotion;
    public LocalDate startDate;
    public Promotion(String promotionName, int idPromotion, LocalDate startDate) {
        this.promotionName = new SimpleStringProperty(promotionName);
        this.idPromotion = new SimpleIntegerProperty(idPromotion);
        this.startDate = startDate;
    }

    public String getPromotionName() {
        return promotionName.get();
    }

    public SimpleStringProperty promotionNameProperty() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName.set(promotionName);
    }

    public int getIdPromotion() {
        return idPromotion.get();
    }

    public SimpleIntegerProperty idPromotionProperty() {
        return idPromotion;
    }

    public void setIdPromotion(int idPromotion) {
        this.idPromotion.set(idPromotion);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
}
