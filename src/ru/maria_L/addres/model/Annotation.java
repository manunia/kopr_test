package ru.maria_L.addres.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Annotation {

    private final StringProperty descriptoin;
    private final Place place;

    public Annotation(String descriptoin, Place place) {
        this.descriptoin = new SimpleStringProperty(descriptoin);
        this.place = place;
    }

    public String getDescriptoin() {
        return descriptoin.get();
    }

    public StringProperty descriptoinProperty() {
        return descriptoin;
    }

    public void setDescriptoin(String descriptoin) {
        this.descriptoin.set(descriptoin);
    }

    public Place getPlace() {
        return place;
    }
}
