package com.brahim.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brahim.dao.SpecialityRepository;
import com.brahim.exception.ResourceNotFoundException;
import com.brahim.model.Speciality;
import com.brahim.projection.ProjectSpeciality;

@Service
public class SpecialityService {
	@Autowired
	private SpecialityRepository specialityRepository;
	
	// get all specialies
	public List<Speciality> getAllSpecialities(){
		
		return this.specialityRepository.findAll();
		
		
	}
	
	// get speciality by id
	public Speciality getSpecialityById(int id) {
		Speciality speciality =this.specialityRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("There is no speciality with this id :"+id));
	    return speciality;
	}
	
	// create speciality
	
	public Speciality createSpeciality(Speciality speciality) {
		this.specialityRepository.save(speciality);
		return speciality;
	}
	
	// update speciality
	public Speciality updateSpeciality(int id) {
		Speciality speciality =getSpecialityById(id);
		this.specialityRepository.save(speciality);
		return speciality;
	}
	
	// delete speciality
	public Speciality deleteSpeciality(int id) {
		Speciality deletedSpeciality = getSpecialityById(id);
		this.specialityRepository.delete(deletedSpeciality);
		return deletedSpeciality;
	}
	// get only id and the name of all specialities
	public List<ProjectSpeciality> getSpecialitiesForSelect(){
		return this.specialityRepository.getSpecialitiesForSelect();
	}

}
