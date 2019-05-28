package ru.maria_L.addres.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Place {

    private final StringProperty country;
    private final StringProperty region;
    private final StringProperty city;
    private final StringProperty street;
    private final StringProperty house;
    //private final StringProperty descriptoin;
    private final Annotation descriptoin;

    public Place() {
        this(null,null);
    }

    public Place(String country, String region) {
        this.country = new SimpleStringProperty(country);
        this.region = new SimpleStringProperty(region);

        // Какие-то фиктивные начальные данные для удобства тестирования.
        this.city =new SimpleStringProperty("some place");
        this.street = new SimpleStringProperty("some street");
        this.house = new SimpleStringProperty("number house");
        //this.descriptoin = new SimpleStringProperty("this is my favorite place.");
        this.descriptoin = new Annotation("this is my favorite place.",this);

    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getRegion() {
        return region.get();
    }

    public StringProperty regionProperty() {
        return region;
    }

    public void setRegion(String region) {
        this.region.set(region);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public String getStreet() {
        return street.get();
    }

    public StringProperty streetProperty() {
        return street;
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public String getHouse() {
        return house.get();
    }

    public StringProperty houseProperty() {
        return house;
    }

    public void setHouse(String house) {
        this.house.set(house);
    }

    public String getDescriptoin() {
        return descriptoin.getDescriptoin();
    }

    public void setDescriptoin(String text) {
        this.descriptoin.setDescriptoin(text);
    }

    //    public String getDescriptoin() {
//        return descriptoin.get();
//    }
//
//    public StringProperty descriptoinProperty() {
//        return descriptoin;
//    }
//
//    public void setDescriptoin(String descriptoin) {
//        this.descriptoin.set(descriptoin);
//    }
}
