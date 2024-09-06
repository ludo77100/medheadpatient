package com.medhead.patientsmicroservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medhead.patientsmicroservice.Controllers.PatientController;
import com.medhead.patientsmicroservice.Entities.Care;
import com.medhead.patientsmicroservice.Entities.Patient;
import com.medhead.patientsmicroservice.Entities.PostalAddress;
import com.medhead.patientsmicroservice.Services.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PatientService patientService;

    private List<Patient> patientList;
    private PostalAddress postalAddress1;
    private PostalAddress postalAddress2;
    private Patient patient1;
    private Patient patient2;
    private Care care1;
    private Care care2;
    private Care care3;

    @BeforeEach
    public void setUp() {
        postalAddress1 = new PostalAddress();
        postalAddress1.setPostalAddressId(1L);
        postalAddress1.setStreetAddress("1 mn street");
        postalAddress1.setComplementaryAddressLine("test");
        postalAddress1.setCity("Paris");
        postalAddress1.setPostalCode("75001");
        postalAddress1.setState("StateTest");
        postalAddress1.setCountry("France");

        postalAddress2 = new PostalAddress();
        postalAddress2.setPostalAddressId(2L);
        postalAddress2.setStreetAddress("2 mn street");
        postalAddress2.setCity("Paris");
        postalAddress2.setPostalCode("75002");
        postalAddress2.setState("StateTest2");
        postalAddress2.setCountry("France");

        patient1 = new Patient();
        patient1.setPatientId(1L);
        patient1.setBirthDate(LocalDate.of(2000, 1, 1));
        patient1.setNationality("Nationality 1");
        patient1.setFirstName("John");
        patient1.setLastName("DOE");
        patient1.setIdCardNumberList(List.of("7339073939"));
        patient1.setAddress(postalAddress1);

        patient2 = new Patient();
        patient2.setPatientId(2L);
        patient2.setBirthDate(LocalDate.of(2000, 2, 2));
        patient2.setNationality("Nationality 2");
        patient2.setFirstName("Karen");
        patient2.setLastName("DOE");
        patient2.setIdCardNumberList(List.of("93243073939", "1233789939"));
        patient2.setAddress(postalAddress2);

        care1 = new Care();
        care1.setCareId(1L);
        care1.setOpenCare(false);
        care1.setCareDateStart(LocalDate.of(2010, 2, 3));
        care1.setCareDateEnd(LocalDate.of(2010, 2, 4));
        care1.setAssignmentHospitalId(1L);
        care1.setAssignmentSpecialityId(2L);
        care1.setCareLatitude(48.867279);
        care1.setCareLongitude(2.781492);
        care1.setPatient(patient1);

        care2 = new Care();
        care2.setCareId(2L);
        care2.setOpenCare(true);
        care2.setCareDateStart(LocalDate.of(2015, 2, 3));
        care2.setAssignmentHospitalId(4L);
        care2.setAssignmentSpecialityId(2L);
        care2.setCareLatitude(49.133848);
        care2.setCareLongitude(2.572024);
        care2.setPatient(patient1);

        care3 = new Care();
        care3.setCareId(3L);
        care3.setOpenCare(true);
        care3.setCareDateStart(LocalDate.of(2015, 2, 3));
        care3.setAssignmentHospitalId(3L);
        care3.setAssignmentSpecialityId(2L);
        care3.setCareLatitude(48.850909);
        care3.setCareLongitude(2.344342);
        care3.setPatient(patient2);

        patientList = Arrays.asList(patient1, patient2);
    }

    @Test
    public void whenGetPatientList_thenReturnsPatientList() throws Exception {
        when(patientService.findAll()).thenReturn(patientList);

        mockMvc.perform(get("/patient/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patientList)));
    }

    @Test
    public void whenGetPatientById_thenReturnsPatient() throws Exception {
        when(patientService.findById(1L)).thenReturn(java.util.Optional.of(patient1));

        mockMvc.perform(get("/patient/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patient1)));
    }

    @Test
    public void whenGetPatientByLastAndFirstNameIgnoreCase_thenReturnsPatient() throws Exception {
        when(patientService.findByName("John", "DoE")).thenReturn(java.util.Optional.of(patient1));

        mockMvc.perform(get("/patient/name")
                        .param("firstName", "John")
                        .param("lastName", "DoE")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patient1)));
    }

    @Test
    public void whenGetPatientByIdCardNumber_thenReturnsPatient() throws Exception {
        when(patientService.findByIdCardNumber("7339073939")).thenReturn(java.util.Optional.of(patient1));

        mockMvc.perform(get("/patient/idCardNumber")
                        .param("idCardNumber", "7339073939")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patient1)));
    }

    @Test
    public void whenGetPatientByIdCardNumberWithMultipleEntryInDb_thenReturnsPatient() throws Exception {
        when(patientService.findByIdCardNumber("93243073939")).thenReturn(java.util.Optional.of(patient2));

        mockMvc.perform(get("/patient/idCardNumber")
                        .param("idCardNumber", "93243073939")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patient2)));
    }

    @Test
    public void whenAddPatient_thenReturnsSavedPatient() throws Exception {
        when(patientService.save(any(Patient.class))).thenReturn(Optional.ofNullable(patient1));

        mockMvc.perform(post("/patient/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient1)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patient1)));
    }

    @Test
    public void whenUpdatePatient_thenReturnsUpdatedPatient() throws Exception {
        when(patientService.update(any(Patient.class))).thenReturn(Optional.ofNullable(patient1));

        mockMvc.perform(put("/patient/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patient1)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patient1)));
    }

    @Test
    public void whenDeletePatient_thenReturnsNoContent() throws Exception {
        when(patientService.deleteById(anyLong())).thenReturn(true);

        mockMvc.perform(delete("/patient/delete/{id}", 1L))
                .andExpect(status().isNoContent());
    }

    @Test
    public void whenDeletePatientNotFound_thenReturnsNotFound() throws Exception {
        when(patientService.deleteById(anyLong())).thenReturn(false);

        mockMvc.perform(delete("/patient/delete/{id}", 1L))
                .andExpect(status().isNotFound());
    }

}