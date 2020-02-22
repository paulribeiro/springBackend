package com.ecommerce.microcommerce.dco;

public class LocationDco {

    private Integer locationId;

    private String number;

    private String street;

    private String city;

    private String zipCode;

    private String country;

    public LocationDco(Integer locationId, String number, String street, String city, String zipCode, String country) {
        this.locationId = locationId;
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public LocationDco(String number, String street, String city, String zipCode, String country) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
        this.country = country;
    }

    public LocationDco() {
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) { this.locationId = locationId; }

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
}
