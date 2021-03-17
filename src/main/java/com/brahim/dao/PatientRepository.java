package com.brahim.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brahim.model.Patient;
import com.brahim.projection.Project;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
	
	 @Query("select   new com.brahim.projection.Project(p.id, p.firstName,p.lastName,p.phone,v.date_Visit,d.fname)from Patient p,Doctor d join p.visits v join d.visits v where p.id=v.patient"
			  ) public List<Project> listPatients();
	 
}
