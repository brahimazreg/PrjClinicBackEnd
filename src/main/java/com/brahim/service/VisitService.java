package com.brahim.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brahim.dao.VisitRepository;
import com.brahim.exception.ResourceNotFoundException;
import com.brahim.model.Visit;

@Service
public class VisitService {
	
	
	@Autowired
	private VisitRepository visitRepository;
	
	// get all visit 
	public List<Visit> getAllVisits(){
		return  this.visitRepository.findAll();
		
	
	}
	
	// get visit byId
	public Visit getVisitById(int id) {
		Visit visit = new Visit();
		visit=this.visitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no visit with this id :" +id));
	    return visit;
	}
    
	// create visit
	public Visit createVisit(Visit visit) {
		this.visitRepository.save(visit);
		return visit;
	}
	
	// update visit
	public Visit updateVisit(int id) {
		Visit updatedVisit = getVisitById(id);
		this.visitRepository.save(updatedVisit);
		return updatedVisit;
	}
	
	// delete visit
	public Visit deleteVisit(int id) {
		Visit deletedVisit = this.getVisitById(id);
		this.visitRepository.deleteById(id);
		return deletedVisit;
	}
	
	// get visit foreign key
	public Visit getVisitByForeignKey(int patientId) {
		Visit visit = visitRepository.getVisitByForeignKey(patientId);
		return visit;
		
	}
	
	
	
}
