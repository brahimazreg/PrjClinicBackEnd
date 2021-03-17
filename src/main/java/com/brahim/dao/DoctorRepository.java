package com.brahim.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brahim.model.Doctor;
import com.brahim.model.Visit;
import com.brahim.projection.DoctorWithSpeciality;
import com.brahim.projection.ProjectDoctor;
import com.brahim.projection.ProjectDoctorDetails;
@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer>{
	
	//This method return the id and the name of doctors used to populate the select
	@Query("select new com.brahim.projection.ProjectDoctor(d.id,d.fname) from Doctor d")
	public List<ProjectDoctor> getDoctors();
	

 	// get all doctors and their speciaities
	@Query("select  new com.brahim.projection.ProjectDoctorDetails(d.id,d.fname,d.lname,d.phone,s.speciality) from Speciality s join s.doctors d  on d.speciality=s.id ")
    public List<ProjectDoctorDetails> getDoctorsBySpeciality();
	
	// get doctor and his speciality by id
	@Query("select  new com.brahim.projection.DoctorWithSpeciality(d.id,d.fname,d.lname,d.phone,s.speciality) from Speciality s join s.doctors d  on d.speciality=s.id where d.id=:doctorId ")
	public DoctorWithSpeciality getDoctorAndHisSpecialityById(@Param ("doctorId") int doctorId);
	
}
