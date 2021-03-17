package com.brahim.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.brahim.model.Speciality;
import com.brahim.projection.ProjectSpeciality;
@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer>{
	
	@Query("select new com.brahim.projection.ProjectSpeciality( s.id,s.speciality )from Speciality s")
	public List<ProjectSpeciality> getSpecialitiesForSelect();

}
