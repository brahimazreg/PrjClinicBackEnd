package com.brahim.contoller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.brahim.dao.DoctorRepository;
import com.brahim.model.Doctor;
import com.brahim.model.Patient;
import com.brahim.model.Speciality;
import com.brahim.model.Visit;
import com.brahim.projection.DoctorWithSpeciality;
import com.brahim.projection.Project;
import com.brahim.projection.ProjectDoctor;
import com.brahim.projection.ProjectDoctorDetails;
import com.brahim.projection.ProjectSpeciality;
import com.brahim.service.DoctorService;
import com.brahim.service.PatientService;
import com.brahim.service.SpecialityService;
import com.brahim.service.VisitService;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {
	LocalDate date ;
	@Autowired
	private PatientService patientService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private VisitService visitService;
	
	
	@Autowired
	private SpecialityService specialityService;
	//************************************* Manage  Patient  **************************
	
	// get all patients but only in patient table
	
	  @GetMapping("/patients")
	  public List<Patient> getAllPatients(){
		 return  patientService.findAllPatients(); 
		 }
	 
	 
	
	// get all patients by joining doctors tabe and visits table  to get more details for a patient his doctor and his visit
	@GetMapping("/patientsDetails")
	public List<Project> getAllPatientsDetails(){
		return patientService.getAllPatients();
	}
	
	
	// get Patient by Id
	@GetMapping("patients/{id}")
	public Patient getPatientById(@PathVariable (value="id") int id) {
		return patientService.getPatientById(id);
	}
	
	// create a patient replaced by register patient
	/*
	 * @PostMapping("/patients") public Patient createPatient(@RequestBody Patient
	 * patient) { return patientService.createPatient(patient); }
	 */
	
	
	// register patient
	@PostMapping("/patients/{doctorId}")
	public Patient registerPatient(@RequestBody Patient patient,@PathVariable int doctorId ) {
		Doctor doctor = getDoctorById(doctorId);
		patient.setDoctor(doctor);
		//doctor.getPatients().add(patient);
		this.patientService.createPatient(patient);
		// create a visit for this patient
		Visit visit = new Visit();
		visit.setDate_Visit(date.now());
		visit.setDoctor(doctor);
		visit.setPatient(patient);
	    this.visitService.createVisit(visit);
		return patient;
	}
	
	// update a patient
	@PutMapping("/patients/{id}/{docId}")
	public Patient updatePatient(@PathVariable (name="id") int id,@PathVariable(name="docId") int docId,@RequestBody Patient patient) {
		Doctor doctor = getDoctorById(docId);
		//Doctor doctor =doctorService.getDoctorByForeignKey(id);
		Patient updatedPatient =  getPatientById( id);
		patient.setDoctor(doctor);
		updatedPatient.setFirstName(patient.getFirstName());
		updatedPatient.setLastName(patient.getLastName());
		updatedPatient.setPhone(patient.getPhone());
		updatedPatient.setDoctor(patient.getDoctor());
				patientService.updatePatient(id);
	   // update table visit
		Visit visit = visitService.getVisitByForeignKey(updatedPatient.getId());
		visit.setDate_Visit(date.now());
		visit.setDoctor(doctor);
		visit.setPatient(updatedPatient);
		
		visitService.updateVisit(visit.getId());
		return updatedPatient;
	}
	
	@DeleteMapping("/patients/{id}")
	public Patient deletePatient(@PathVariable int id) {
		//Patient deledtedPatient =  getPatientById(id); 
		Patient deledtedPatient = patientService.getPatientById(id);  // change
		patientService.deletePatient(id);
		return deledtedPatient;
	}

	
	//************************************* Manage Doctor  **************************
	
	// get all doctors ( just id and first name to populate the select)
	@GetMapping("/selectdoctors")
	public List<ProjectDoctor> getDoctors(){
		return doctorService.getSelectDoctors();
	}
	
	// get all doctors with all fields)
	@GetMapping("/doctors")
	public List<Doctor>  getAllDoctors(){
		return this.doctorService.getAllDoctors();
		
	}
	
	// get doctor by Id
	@GetMapping("/doctors/{id}")
	public Doctor getDoctorById(@PathVariable int id) {
		return this.doctorService.getDoctorById(id);
	}
	
	// update doctor
	@PutMapping("/doctors/{id}/{spId}")
	public Doctor updateDoctor(@PathVariable int id,@RequestBody Doctor doctor,@PathVariable int spId) {
		Speciality speciality = this.specialityService.getSpecialityById(spId);
		Doctor updatedDoctor =getDoctorById(id);
		updatedDoctor.setFname(doctor.getFname());
		updatedDoctor.setLname(doctor.getLname());
		updatedDoctor.setPhone(doctor.getPhone());
		updatedDoctor.setSpeciality(speciality);
		
		//updatedDoctor.setSpeciality(doctor.getSpeciality());
	
		return this.doctorService.updateDoctor(id) ;
		}
	
	//create doctor not used changed by registerDoctor
	@PostMapping("/doctors")
	public Doctor createDoctor(@RequestBody Doctor doctor) {
		return this.doctorService.addDoctor(doctor);
	}
		
	
	
	@PostMapping("/doctors/{speciality_id}")
	public Doctor registerDoctor(@PathVariable int speciality_id,@RequestBody Doctor doctor) {
		Speciality speciality= this.specialityService.getSpecialityById(speciality_id);
		doctor.setSpeciality(speciality);
		this.doctorService.addDoctor(doctor);
		return doctor;
	}
	// get all doctors and their specialities
	@GetMapping("/doctorAndSpeciality")
	public List<ProjectDoctorDetails> getAllDoctorsAndSpecialities(){
		return this.doctorService.getDoctorsAndSpecialities();
	}
	
	// delete doctor
	@DeleteMapping("/doctors/{id}")
	public Doctor deleteDoctor(@PathVariable int id) {
		//Doctor deletedDoctor = getDoctorById(id);
		Doctor deletedDoctor =doctorService.getDoctorById(id);  // change
		this.doctorService.deleteDoctor(id);
		return deletedDoctor;
	}
	
	// get a doctor by Id  and his speciality
	@GetMapping("/doctorAndSpeciality/{doctorId}")
	public DoctorWithSpeciality getDoctorAndHisSpecialityById(@PathVariable int doctorId){
		return this.doctorService.getDoctorAndHisSpecialityById(doctorId);
	}
	
	//************************************* Manage visit  **************************
	
	// get all visits
	
	@GetMapping("/visits")
	public List<Visit> getAllVisits(){
		return this.visitService.getAllVisits();
	}
	
	// get visit by id
	@GetMapping("/visits/{id}")
	public Visit getVisitById(@PathVariable int id) {
		return this.visitService.getVisitById(id);
	}
	
	// create visit
	@PostMapping("/visits")
	public Visit createVisit(Visit visit) {
		return this.visitService.createVisit(visit);
	}
	
	// update visit
	@PutMapping("/visits/{id}")
	public Visit updateVisit(int id,Visit visit) {
		Visit updatedVisit = getVisitById(id);
		updatedVisit.setDate_Visit(visit.getDate_Visit());
		updatedVisit.setDoctor(visit.getDoctor());
		updatedVisit.setPatient(visit.getPatient());
		this.visitService.updateVisit(id);
		return updatedVisit;
	}
	
	// delete visit
	@DeleteMapping("/visits/{id}")
	public Visit deleteVisit(@PathVariable int id) {
		return this.visitService.deleteVisit(id);
		
	}
	
	
	//************************************* Manage speciality  **************************
	// get all specialities
	@GetMapping("/specialities")
	public List<Speciality> getAllSpecialities(){
		return specialityService.getAllSpecialities();
	}
	
	// get speciality by id
	
	@GetMapping("/specialities/{id}")
	public Speciality getSpecialityById(@PathVariable int id) {
		 Speciality speciality = specialityService.getSpecialityById(id);
		 return speciality;
	}
	
	// create speciality
	
	@PostMapping("/specialities")
	public Speciality createSpeciality(@RequestBody Speciality speciality) {
		return specialityService.createSpeciality(speciality);
	}
	
	// update speciality
	@PutMapping("/specialities/{id}")
	public Speciality updateSpeciality(@PathVariable int id,@RequestBody Speciality speciality) {
		Speciality updatedSpeciality = getSpecialityById(id);
		updatedSpeciality.setSpeciality(speciality.getSpeciality());
		updatedSpeciality.setDescription(speciality.getDescription());
		
		
		specialityService.updateSpeciality(id);
		
		return updatedSpeciality;
	}
	
	// delete speciality
	@DeleteMapping("/specialities/{id}")
	public Speciality deleteSpeciality(@PathVariable int id) {
		return specialityService.deleteSpeciality(id);
		
	}
	
	// get specialities for the dropdown list
	@GetMapping("/selectSpecialities")
	public List<ProjectSpeciality> getSpecialitiesForSelect(){
		return this.specialityService.getSpecialitiesForSelect();
	}
	
}
