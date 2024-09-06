package com.medhead.patientsmicroservice.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "postal_address")
public class PostalAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postalAddressId ;

    private String streetAddress ;

    private String complementaryAddressLine ;

    private String city ;

    private String state ;

    private String postalCode ;

    private String country ;

    public PostalAddress() {
        super();
    }

    public PostalAddress(Long postalAddressId, String streetAddress, String complementaryAddressLine, String city, String state, String postalCode, String country) {
        this.postalAddressId = postalAddressId;
        this.streetAddress = streetAddress;
        this.complementaryAddressLine = complementaryAddressLine;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Long getPostalAddressId() {
        return postalAddressId;
    }

    public void setPostalAddressId(Long postalAddressId) {
        this.postalAddressId = postalAddressId;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getComplementaryAddressLine() {
        return complementaryAddressLine;
    }

    public void setComplementaryAddressLine(String complementaryAddressLine) {
        this.complementaryAddressLine = complementaryAddressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "PostalAddress{" +
                "postalAddressId=" + postalAddressId +
                ", streetAddress='" + streetAddress + '\'' +
                ", complementaryAddressLine='" + complementaryAddressLine + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
