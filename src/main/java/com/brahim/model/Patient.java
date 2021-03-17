package com.brahim.model;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Table(name="patients")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="patient_id")
	private int id;
	private String firstName;
	private String lastName;
	private String phone;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	//@JsonBackReference
	@JsonIgnore 
	private Doctor doctor;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.REMOVE,  mappedBy = "patient",orphanRemoval = true)
	//@JsonManagedReference
	@JsonIgnore 
	private Collection<Visit> visits = new ArrayList<Visit>();

	public Patient(int id, String firstName, String lastName, String phone, Doctor doctor) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.doctor=doctor;
	}

	


	
	
	

}
