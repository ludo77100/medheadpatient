package com.medhead.patientsmicroservice.Repositories;

import com.medhead.patientsmicroservice.Entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByFirstNameAndLastNameIgnoreCase(String firstName, String lastName);

    @Query("SELECT p FROM Patient p JOIN p.idCardNumberList id WHERE id = :idCardNumber")
    Optional<Patient> findPatientByIdCardNumber(String idCardNumber);

}


