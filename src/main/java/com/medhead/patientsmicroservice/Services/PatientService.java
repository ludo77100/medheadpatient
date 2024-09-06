package com.medhead.patientsmicroservice.Services;

import com.medhead.patientsmicroservice.Entities.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    List<Patient> findAll();

    Optional<Patient> findById(Long patientId);

    Optional<Patient> findByName(String firstName, String lastName);

    Optional<Patient> findByIdCardNumber(String idCardNumber);

    Optional<Patient> save(Patient patient);

    Optional<Patient> update(Patient patient);

    boolean deleteById(Long id);
}
