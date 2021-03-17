package com.brahim.model;




import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name="visits")
public class Visit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="visit_id")
	private int id;
	//private Date dateVisit;
	@Column(name = "date_Visit",columnDefinition = "DATE")
	private LocalDate date_Visit;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	//@JsonBackReference
	@JsonIgnore 
	private Patient patient;
	
	@ManyToOne
    @JoinColumn(name="doctor_id")// change
	@JsonIgnore 
	//@JsonBackReference
	private Doctor doctor;

	
	
	
	
	
	
	
	

}
