package com.medhead.patientsmicroservice.Entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "care")
public class Care {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long careId ;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate careDateStart ;

    private LocalDate careDateEnd ;

    @Column(nullable = false, updatable = false)
    private double careLatitude ;

    @Column(nullable = false, updatable = false)
    private double careLongitude ;

    private Long assignmentHospitalId ;

    private Long assignmentSpecialityId ;

    private Boolean openCare ;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;

    public Care() {
        super();
    }

    public Care(Long careId, LocalDate careDateStart, LocalDate careDateEnd, double careLatitude, double careLongitude, Long assignmentHospitalId, Long assignmentSpecialityId, Patient patient, Boolean openCare) {
        this.careId = careId;
        this.careDateStart = careDateStart;
        this.careDateEnd = careDateEnd;
        this.careLatitude = careLatitude;
        this.careLongitude = careLongitude;
        this.assignmentHospitalId = assignmentHospitalId;
        this.assignmentSpecialityId = assignmentSpecialityId;
        this.patient = patient;
        this.openCare = openCare;
    }

    public Long getCareId() {
        return careId;
    }

    public void setCareId(Long careId) {
        this.careId = careId;
    }

    public LocalDate getCareDateStart() {
        return careDateStart;
    }

    public void setCareDateStart(LocalDate careDateStart) {
        this.careDateStart = careDateStart;
    }

    public LocalDate getCareDateEnd() {
        return careDateEnd;
    }

    public void setCareDateEnd(LocalDate careDateEnd) {
        this.careDateEnd = careDateEnd;
    }

    public double getCareLatitude() {
        return careLatitude;
    }

    public void setCareLatitude(double careLatitude) {
        this.careLatitude = careLatitude;
    }

    public double getCareLongitude() {
        return careLongitude;
    }

    public void setCareLongitude(double careLongitude) {
        this.careLongitude = careLongitude;
    }

    public Long getAssignmentHospitalId() {
        return assignmentHospitalId;
    }

    public void setAssignmentHospitalId(Long assignmentHospitalId) {
        this.assignmentHospitalId = assignmentHospitalId;
    }

    public Long getAssignmentSpecialityId() {
        return assignmentSpecialityId;
    }

    public void setAssignmentSpecialityId(Long assignmentSpecialityId) {
        this.assignmentSpecialityId = assignmentSpecialityId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Boolean getOpenCare() {
        return openCare;
    }

    public void setOpenCare(Boolean openCare) {
        this.openCare = openCare;
    }

    @Override
    public String toString() {
        return "Care{" +
                "careId=" + careId +
                ", careDateStart=" + careDateStart +
                ", careDateEnd=" + careDateEnd +
                ", careLatitude=" + careLatitude +
                ", careLongitude=" + careLongitude +
                ", assignmentHospitalId=" + assignmentHospitalId +
                ", assignmentSpecialityId=" + assignmentSpecialityId +
                ", patient=" + patient +
                ", openCare=" + openCare +
                '}';
    }
}
