package com.brahim.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.brahim.model.Visit;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Integer>{
	 
	
	@Query(value="select p.patient_id,v.visit_id,v.date_visit,v.doctor_id,v.patient_id from patients p join visits v on p.patient_id=v.patient_id  where v.patient_id = :patId",nativeQuery=true)
    public Visit getVisitByForeignKey(@Param("patId") int patId);

}
