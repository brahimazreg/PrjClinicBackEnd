package com.brahim.visit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.brahim.dao.VisitRepository;
import com.brahim.model.Doctor;
import com.brahim.model.Patient;
import com.brahim.model.Speciality;
import com.brahim.model.Visit;
import com.brahim.service.VisitService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class VisitServiceTest {
	
	@Autowired
	private VisitService visitService;
	@MockBean
	private VisitRepository visitRepository;
	
	//Test get Allvisits
	@Test
	@Rollback(false)
	public void getAllVisitsTest() {
		LocalDate date_visit=LocalDate.now();
		Speciality sp = new Speciality(10,"speciality","description");
		Doctor doctor = new Doctor(11,"Bob","Tom","1111",sp);
		Patient patient = new Patient(12,"Balou","Rob","1111",doctor);
		
		when(visitRepository.findAll()).thenReturn(Stream
				.of(new Visit(999,date_visit,patient,doctor)).collect(Collectors.toList()));
		assertEquals(1,visitService.getAllVisits().size());
		
	}
	
	// Test getVisitById() method
	@Test
	@Rollback(false)
	public void getVisitByIdTest() {
		LocalDate date_visit=LocalDate.now();
		Speciality sp = new Speciality(10,"speciality","description");
		Doctor doctor = new Doctor(11,"Bob","Tom","1111",sp);
		Patient patient = new Patient(12,"Balou","Rob","1111",doctor);
		int id =1;
		when(visitRepository.findById(id)).thenReturn(Optional.of(new Visit(999,date_visit,patient,doctor)));
		assertEquals(999,visitService.getVisitById(id).getId());
	}
    
	//Test createVisit() method
	@Test
	@Rollback(false)
	public void createVisitTest() {
		LocalDate date_visit=LocalDate.now();
		Speciality sp = new Speciality(10,"speciality","description");
		Doctor doctor = new Doctor(11,"Bob","Tom","1111",sp);
		Patient patient = new Patient(12,"Balou","Rob","1111",doctor);
		Visit visit = new Visit(100,date_visit,patient,doctor);
		when(visitRepository.save(visit)).thenReturn(visit);
		assertEquals(100,visitService.createVisit(visit).getId());
	}
	//Test deleteVisit() method
	@Test
	@Rollback(false)
	public void deleteVisitTest() {
		int id = 1;
		LocalDate date_visit=LocalDate.now();
		Speciality sp = new Speciality(10,"speciality","description");
		Doctor doctor = new Doctor(11,"Bob","Tom","1111",sp);
		Patient patient = new Patient(12,"Balou","Rob","1111",doctor);
		Visit visit = new Visit(100,date_visit,patient,doctor);
		when(visitRepository.findById(id)).thenReturn(Optional.of(visit));
		visitService.deleteVisit(id);
		verify(visitRepository,times(1)).delete(visit);
		
	}
	
	// Test getVisitByForeignKey() method
	@Test
	@Rollback(false)
	public void getVisitByForeignKey() {
		LocalDate date_visit=LocalDate.now();
		Speciality sp = new Speciality(10,"speciality","description");
		Doctor doctor = new Doctor(11,"Bob","Tom","1111",sp);
		Patient patient = new Patient(12,"Balou","Rob","1111",doctor);
		Visit visit = new Visit(100,date_visit,patient,doctor);
		when(visitRepository.getVisitByForeignKey(patient.getId())).thenReturn(visit);
		assertEquals(100,visitService.getVisitByForeignKey(patient.getId()).getId());
	}
}
