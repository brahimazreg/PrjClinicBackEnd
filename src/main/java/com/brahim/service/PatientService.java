package com.brahim.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brahim.dao.PatientRepository;
import com.brahim.exception.ResourceNotFoundException;
import com.brahim.model.Patient;
import com.brahim.projection.Project;



@Service

public class PatientService {
	@Autowired
	private PatientRepository patientRepository;

	// get all patients
	
	  public List<Patient> findAllPatients() {
		return this.patientRepository.findAll(); 
	  }
	 
	
		/*
		 * this method retreive data from 3 tables : patient,Visit,Doctor I use an other
		 * class Project to collect result
		 */
	public List<Project> getAllPatients() {
		List<Project> patients = new ArrayList<Project>();
		patients= patientRepository.listPatients();
		return patients;
	}
	
	// get patient by id
	
	public Patient getPatientById(int id) {
		Patient patient =this.patientRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Patient doesn't exist"));
	  return patient;
	}
	
	// create a patient 
	public Patient createPatient(Patient patient) {
		return this.patientRepository.save(patient);
	}
	
	//update patient
	
	public Patient updatePatient(int id) {
		Patient patient =  getPatientById( id);
		patientRepository.save(patient);
		return patient;
	}
	
	// delete a patient
	
	public Patient deletePatient(int id) {
		Patient patient =  getPatientById( id);
		patientRepository.delete(patient);
		return patient;
		
	}
	

}
