package com.brahim.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brahim.dao.DoctorRepository;
import com.brahim.exception.ResourceNotFoundException;
import com.brahim.model.Doctor;
import com.brahim.projection.DoctorWithSpeciality;
import com.brahim.projection.ProjectDoctor;
import com.brahim.projection.ProjectDoctorDetails;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	
	// get all doctors ( just id and first name to populate the select)
	public List<ProjectDoctor> getSelectDoctors(){
		List<ProjectDoctor> doctors = new ArrayList<ProjectDoctor>();
		doctors=doctorRepository.getDoctors();
		return doctors;
	}
    
	// get all doctors 
	public List<Doctor> getAllDoctors(){
		return this.doctorRepository.findAll();
		
	}
	// Get doctor by Id
	
	public Doctor getDoctorById(int id) {
		Doctor doctor = new Doctor();
		doctor=doctorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Doctor not found with this id :" +id));
		return doctor;
	}
	
	// create a doctor
	public Doctor addDoctor(Doctor doctor) {
		this.doctorRepository.save(doctor);
		return doctor;
	}
	
	// update doctor
	
	public Doctor updateDoctor(int id) {
		Doctor updatedDoctor = this.getDoctorById(id);
		this.doctorRepository.save(updatedDoctor);
		return updatedDoctor;
	}
	
	// delete a doctor
	public Doctor deleteDoctor(int id) {
		Doctor deletedDoctor = this.getDoctorById(id);
		this.doctorRepository.delete(deletedDoctor);
		return deletedDoctor;
	}
	
	
	
	//get all doctors and their speciality
	public List<ProjectDoctorDetails> getDoctorsAndSpecialities(){
		return this.doctorRepository.getDoctorsBySpeciality();
	}
	
	// get a doctor by id and his speciality
	public DoctorWithSpeciality getDoctorAndHisSpecialityById(int doctorId){
		return this.doctorRepository.getDoctorAndHisSpecialityById(doctorId);
	}
}
