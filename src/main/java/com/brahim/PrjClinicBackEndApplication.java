package com.brahim;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brahim.dao.DoctorRepository;
import com.brahim.dao.PatientRepository;
import com.brahim.dao.SpecialityRepository;
import com.brahim.dao.VisitRepository;
import com.brahim.model.Doctor;
import com.brahim.model.Patient;
import com.brahim.model.Speciality;
import com.brahim.model.Visit;

@SpringBootApplication
public class PrjClinicBackEndApplication implements CommandLineRunner{
	LocalDate date1 = LocalDate.of(1962,5,28);
	LocalDate date2 = LocalDate.of(1970,1,17);
	LocalDate date3 = LocalDate.of(1994,9,23);
	
	LocalDate d1 =LocalDate.of(2020,1,17);
	LocalDate d2 =LocalDate.of(2021,2,10);
	LocalDate d3 =LocalDate.of(2021,3,10);
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private VisitRepository visitrepository;
	@Autowired
	private SpecialityRepository specialityRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(PrjClinicBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Testing dao layer
		
		
		
		  Speciality sp1 =specialityRepository.save(new
		  Speciality(1,"Neurology"," the scientific study of the nervous system."));
		  
		  Speciality
		  sp2=specialityRepository.save(new
		  Speciality(2,"Pediatrics","the medical care of infants, children, and adolescents."));
		  
		  Speciality sp3 =specialityRepository.save(new
				  Speciality(3,"Anesthesiology","The branch of medicine specializing in the use of agents that cause insensibility to pain."));
		  Speciality sp4 =specialityRepository.save(new
				  Speciality(4,"Dermatology"," Diseases, cancers, cosmetic and ageing conditions of the skin."));
		  
		  
		  Doctor doc1 =doctorRepository.save(new
		  Doctor(1,"Bob","Marley","12121212",sp1)); Doctor doc2
		  =doctorRepository.save(new Doctor(2,"Sarah","Lambert","999999",sp2)); Doctor
		  doc3 =doctorRepository.save(new Doctor(3,"Marc","Derix","444444",sp3));
		  
		  
		  
		  Patient p1 = patientRepository.save(new
		  Patient(1,"Brahim","Azreg","666666",doc2));
		  
		  Patient p2 = patientRepository.save(new
		  Patient(2,"Fatiha","Yaoti","7070707",doc1)); Patient p3 =
		  patientRepository.save(new
		  Patient(3,"Ismael","Azreg","33333333",doc3));
		  
		  Visit v1= visitrepository.save(new Visit(1,d1 ,p1,doc2));
		  
		  
		  Visit v2 = visitrepository.save(new Visit(2,d2,p2,doc1));
		  
		  Visit v3 = visitrepository.save(new Visit(3,d3,p3,doc3));
		 
		 
		
		
		
		
		
	}

}
