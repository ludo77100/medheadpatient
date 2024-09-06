package com.medhead.patientsmicroservice.Services;

import com.medhead.patientsmicroservice.Entities.Patient;
import com.medhead.patientsmicroservice.Repositories.PatientRepository;
import com.medhead.patientsmicroservice.Services.Impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    private Patient patient1;
    private Patient patient2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        patient1 = new Patient();
        patient1.setPatientId(1L);
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setBirthDate(LocalDate.of(1990, 1, 1));
        patient1.setIdCardNumberList(List.of("ID123", "ID456"));

        patient2 = new Patient();
        patient2.setPatientId(2L);
        patient2.setFirstName("Jane");
        patient2.setLastName("Doe");
        patient2.setBirthDate(LocalDate.of(1992, 2, 2));
        patient2.setIdCardNumberList(List.of("ID789", "ID012"));
    }

    @Test
    public void testFindAll() {
        when(patientRepository.findAll()).thenReturn(List.of(patient1, patient2));

        List<Patient> patients = patientService.findAll();

        assertEquals(2, patients.size());
        assertEquals(patient1, patients.get(0));
        assertEquals(patient2, patients.get(1));
    }

    @Test
    public void testFindById() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient1));

        Optional<Patient> patient = patientService.findById(1L);

        assertTrue(patient.isPresent());
        assertEquals(patient1, patient.get());
    }

    @Test
    public void testFindByName() {
        when(patientRepository.findPatientByFirstNameAndLastNameIgnoreCase("John", "Doe")).thenReturn(Optional.of(patient1));

        Optional<Patient> patient = patientService.findByName("John", "Doe");

        assertTrue(patient.isPresent());
        assertEquals(patient1, patient.get());
    }

    @Test
    public void testFindByIdCardNumber() {
        when(patientRepository.findPatientByIdCardNumber("ID123")).thenReturn(Optional.of(patient1));

        Optional<Patient> patient = patientService.findByIdCardNumber("ID123");

        assertTrue(patient.isPresent());
        assertEquals(patient1, patient.get());
    }

    @Test
    public void testSave() {
        when(patientRepository.save(any(Patient.class))).thenReturn(patient1);

        Optional<Patient> savedPatient = patientService.save(patient1);

        assertTrue(savedPatient.isPresent());
        assertEquals(patient1, savedPatient.get());
    }

    @Test
    public void testUpdate() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient1));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient1);

        Optional<Patient> updatedPatient = patientService.update(patient1);

        assertTrue(updatedPatient.isPresent());
        assertEquals(patient1, updatedPatient.get());
    }

    @Test
    public void testUpdatePatientNotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Patient> updatedPatient = patientService.update(patient1);

        assertFalse(updatedPatient.isPresent());
    }
}