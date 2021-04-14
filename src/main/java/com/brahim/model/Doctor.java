package com.brahim.model;

import java.util.ArrayList;
import java.util.Collection;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Table(name="doctors")
public class Doctor {
	

	public Doctor(int id, String fName, String lname, String phone, Speciality speciality) {
		super();
		this.id = id;
		this.fname = fName;
		this.lname = lname;
		this.phone = phone;
		this.speciality = speciality;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doctor_id")  // change
	private int id;
	private String fname;
	private String lname;
	private String phone;
	
	@OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.REMOVE,mappedBy = "doctor",orphanRemoval = true)
	//@JsonManagedReference
	@JsonIgnore 
	private Collection<Patient> patients = new ArrayList<Patient>();
	
	@OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.REMOVE,mappedBy = "doctor",orphanRemoval = true)
	//@JsonManagedReference
	@JsonIgnore 
	private Collection<Visit> visits = new ArrayList<Visit>();
	
	
	  @ManyToOne()
	  
	  @JoinColumn(name="speciality_id")
	  //@JsonBackReference
	  
	  @JsonIgnore 
	  private Speciality speciality;
	 
	
	

	
	 
	
	

}
