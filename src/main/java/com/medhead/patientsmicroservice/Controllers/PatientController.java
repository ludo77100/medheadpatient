package com.medhead.patientsmicroservice.Controllers;

import com.medhead.patientsmicroservice.Entities.Patient;
import com.medhead.patientsmicroservice.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService ;

    @GetMapping("/all")
    @Secured("ROLE_SUPER_ADMIN")
    public ResponseEntity<List<Patient>> findAllPatient(){
        List<Patient> patientList = patientService.findAll();
        return new ResponseEntity<>(patientList, HttpStatus.OK);
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<Patient> findPatientById(@PathVariable Long patientId) {
        Optional<Patient> patient = patientService.findById(patientId) ;
        return patient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
        }

    @GetMapping("/name")
    public ResponseEntity<Patient> findPatientByName(@RequestParam String firstName, @RequestParam String lastName) {
        Optional<Patient> patient = patientService.findByName(firstName, lastName) ;
        return patient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/idCardNumber")
    public ResponseEntity<Patient> findPatientByIdCardNumber(@RequestParam String idCardNumber){
        Optional<Patient> patient = patientService.findByIdCardNumber(idCardNumber) ;
        return patient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/save")
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient){
        Optional<Patient> newPatient = patientService.save(patient) ;
        return newPatient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());    }

    @PutMapping("/update")
    public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient){
        Optional<Patient> updatedPatient = patientService.update(patient);
        return updatedPatient.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    //TODO AttachPatientToAnother

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        boolean isRemoved = patientService.deleteById(id);
        if (!isRemoved) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
