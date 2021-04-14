package com.brahim.speciality.Test;

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

import com.brahim.dao.SpecialityRepository;
import com.brahim.model.Speciality;
import com.brahim.projection.ProjectSpeciality;
import com.brahim.service.SpecialityService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SpecialityServiceTest {

	@Autowired
	private SpecialityService specialityService;
	@MockBean
	private SpecialityRepository specialityRepository;
	
	// Test getAllSpecialities() method
	@Test
	@Rollback(false)
	public void getAllSpecialitiesTest() {
		when(specialityRepository.findAll()).thenReturn(Stream.of(new Speciality(99,"speciality","description")).collect(Collectors.toList()));
	    assertEquals(1,specialityService.getAllSpecialities().size());
	}
	
	// Test getSpecialityById() method
	@Test
	@Rollback(false)
	public void getSpecialityByIdTest() {
		int id =1;
		when(specialityRepository.findById(id)).thenReturn(Optional.of(new Speciality(99,"speciality","description")));
		assertEquals(99,specialityService.getSpecialityById(id).getId());
	}
	
	//Test createSpeciality() method
	@Test
	@Rollback(false)
	public void createSpecialityTest() {
		Speciality sp = new Speciality(99,"speciality","description");
		when(specialityRepository.save(sp)).thenReturn(sp);
		assertEquals(99,specialityService.createSpeciality(sp).getId());
	}
	
	// Test deleteSpeciality() method
	@Test
	@Rollback(false)
	public void deleteSpecialityTest() {
		int id =1;
		Speciality sp = new Speciality(99,"speciality","description");
		when(specialityRepository.findById(id)).thenReturn(Optional.of(sp));
		specialityService.deleteSpeciality(id);
		verify(specialityRepository,times(1)).delete(sp);
	}
	
	//Test  List<ProjectSpeciality> getSpecialitiesForSelect() method
	@Test
	@Rollback(false)
	public void getSpecialitiesForSelectTest() {
		when(specialityRepository.getSpecialitiesForSelect()).thenReturn(Stream.of(new ProjectSpeciality(99,"speciality")).collect(Collectors.toList()));
	    assertEquals(1,specialityService.getSpecialitiesForSelect().size());
	}
}
