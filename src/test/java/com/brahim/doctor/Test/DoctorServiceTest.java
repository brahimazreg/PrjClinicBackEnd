package com.brahim.doctor.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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

import com.brahim.dao.DoctorRepository;
import com.brahim.model.Doctor;
import com.brahim.model.Speciality;
import com.brahim.projection.DoctorWithSpeciality;
import com.brahim.projection.ProjectDoctor;
import com.brahim.projection.ProjectDoctorDetails;
import com.brahim.service.DoctorService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DoctorServiceTest {
	
	@Autowired
	private DoctorService doctorService;
	@MockBean
	private DoctorRepository doctorRepository;
	
	// Test getSelectDoctors() method
	@Test
	@Rollback(false)
	public void getSelectDoctorsTest() {
		when(doctorRepository.getDoctors()).thenReturn(Stream.of(new ProjectDoctor(999,"test")).collect(Collectors.toList()));
		assertEquals(1,doctorService.getSelectDoctors().size());
	}
	
	// Test getAllDoctors() method
	@Test
	@Rollback(false)
	public void getAllDoctorsTest() {
		Speciality sp = new Speciality();
		when(doctorRepository.findAll()).thenReturn(Stream.of(new Doctor(999,"test","test","1111",sp)).collect(Collectors.toList()));
		assertEquals(1,doctorService.getAllDoctors().size());
	}
	
	//Test  getDoctorById() method
	@Test
	@Rollback(false)
	public void  getDoctorByIdTest() {
		Speciality sp = new Speciality();
		int id =1;
		when(doctorRepository.findById(id)).thenReturn(Optional.of(new Doctor(999,"test","test","1111",sp)));
		assertEquals(999,doctorService.getDoctorById(id).getId());
		
	}
	 // Test addDoctor() method
	@Test
	@Rollback(false)
	public void addDoctorTest() {
		Speciality sp = new Speciality();
		Doctor doctor = new Doctor(9999,"test","test","111",sp);
		when(doctorRepository.save(doctor)).thenReturn(doctor);
		assertEquals(doctor,doctorService.addDoctor(doctor));
	}
    
	// Test deleteDoctor() method
	@Test
	@Rollback(false)
	public void deleteDoctorTest() {
		int id=1;
		Speciality sp = new Speciality();
		Doctor doctor = new Doctor(9999,"test","test","111",sp);
		when(doctorRepository.findById(id)).thenReturn(Optional.of(doctor));
		doctorService.deleteDoctor(id);
		verify(doctorRepository,times(1)).delete(doctor);
		
	}
	 // Test getDoctorsAndSpecialities() method
	@Test
	@Rollback(false)
	public void getDoctorsAndSpecialitiesTest() {

		when(doctorRepository.getDoctorsBySpeciality()).thenReturn(Stream.of(new ProjectDoctorDetails(999,"test","test","1111","test")).collect(Collectors.toList()));
	    assertEquals(1,doctorService.getDoctorsAndSpecialities().size());
	}
	
	//Test  getDoctorAndHisSpecialityById() method
	@Test
	@Rollback(false)
	public void getDoctorAndHisSpecialityByIdTest() {
		int docId =1;
		DoctorWithSpeciality docsp = new DoctorWithSpeciality(999,"test","test","111","speciality");
		when(doctorRepository.getDoctorAndHisSpecialityById(docId)).thenReturn(docsp);
		assertEquals(999,doctorService.getDoctorAndHisSpecialityById(docId).getId());
	}
}
