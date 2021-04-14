package com.brahim.patientTest;

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

import com.brahim.dao.PatientRepository;
import com.brahim.model.Doctor;
import com.brahim.model.Patient;
import com.brahim.projection.Project;
import com.brahim.service.PatientService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PatientServiceTest {
	
	@Autowired
	private PatientService patientService;
	
	@MockBean
	private PatientRepository patientRepository;
	
	// Test findAllPatients() method
	
	@Test
	@Rollback(false)
	public void findAllPatientsTest() {
		Doctor doc1 = new Doctor();
		Doctor doc2 = new Doctor();
		when(patientRepository.findAll()).thenReturn(Stream
				.of(new Patient(7,"test","test","1111",doc1),new Patient(8,"toto","toto","2222",doc2)).collect(Collectors.toList()));
		assertEquals(2,patientService.findAllPatients().size());
	}
	
	// Test getAllPatients() method
	
	@Test
	@Rollback(false)
	public void getAllPatientsTest() {
	    LocalDate date_visit=LocalDate.now();
		Project proj = new Project(999,"test","test","1111",date_visit,"test");
		
		when(patientRepository.listPatients()).thenReturn(
				Stream.of(new Project(999,"test","test","1111",date_visit,"test")).collect(Collectors.toList()));
		assertEquals(1,patientService.getAllPatients().size());
	}
    // Test getPatientById() method
	@Test
	@Rollback(false)
	public void getPatientByIdTest() {
		Doctor doc1 = new Doctor();
		int id=1;
		when(patientRepository.findById(id)).thenReturn(Optional.of(new Patient(999,"test","test","1111",doc1)));
		assertEquals(999,patientService.getPatientById(id).getId());
	}
	
	// Test createPatient() method
	@Test
	@Rollback(false)
	public void createPatientTest() {
		Doctor doc1 = new Doctor();
		Patient patient = new Patient(999,"test","test","1111",doc1);
		when(patientRepository.save(patient)).thenReturn(patient);
		assertEquals(999,patientService.createPatient(patient).getId());
		
	}
	
	// Test deletePatient() method
	@Test
	@Rollback(false)
	public void deletePatient() {
		Doctor doc1 = new Doctor();
		Patient patient = new Patient(999,"test","test","1111",doc1);
		int id =1;
		when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
		patientService.deletePatient(id);
		verify(patientRepository,times(1)).delete(patient);
	}
}
