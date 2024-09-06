package com.medhead.patientsmicroservice.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId ;

    /**
     * Une liste est construite pour les numéros de carte d'identité afin de
     * pouvoir tracer les changements de cartes pour une personne
     */
    private List<String> idCardNumberList ;

    private String firstName ;

    private String lastName ;

    private LocalDate birthDate ;

    private String nationality ;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private PostalAddress address;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Care> cares = new HashSet<>();

    public Patient() {
        super();
    }

    public Patient(Long patientId, List<String> idCardNumberList, String firstName, String lastName, LocalDate birthDate, String nationality, PostalAddress address, Set<Care> cares) {
        this.patientId = patientId;
        this.idCardNumberList = idCardNumberList;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.nationality = nationality;
        this.address = address;
        this.cares = cares;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public List<String> getIdCardNumberList() {
        return idCardNumberList;
    }

    public void setIdCardNumberList(List<String> idCardNumberList) {
        this.idCardNumberList = idCardNumberList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public PostalAddress getAddress() {
        return address;
    }

    public void setAddress(PostalAddress address) {
        this.address = address;
    }

    public Set<Care> getCares() {
        return cares;
    }

    public void setCares(Set<Care> cares) {
        this.cares = cares;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "patientId=" + patientId +
                ", idCardNumberList=" + idCardNumberList +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", address=" + address +
                ", cares=" + cares +
                '}';
    }
}
