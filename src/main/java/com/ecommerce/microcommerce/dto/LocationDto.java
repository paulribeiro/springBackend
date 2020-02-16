package com.ecommerce.microcommerce.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RES_LOCATION")
public class Location {

    @Id
    @GeneratedValue
    private Integer locationId;

    private String number;

    private String street;

    private String city;

    private String zipCode;

    private String country;

    public Location() {
    }

    public Location(String number, String street, String city, String zipCode, String country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public Location(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + locationId +
                ", number='" + number +
                ", street=" + street +
                ", city=" + city +
                ", zipCode=" + zipCode +
                ", country=" + country +'}';
    }

}
