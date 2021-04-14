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
@Table(name="specialities")
public class Speciality {
	public Speciality(int id, String speciality, String description) {
		super();
		this.id = id;
		this.speciality = speciality;
		this.description = description;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="speciality_id")// for the foreign key
	private int id;
	private String speciality;
	private String description;
	
	
	
	
	  @OneToMany(fetch = FetchType.EAGER,cascade=CascadeType.REMOVE,mappedBy ="speciality")
	  //@JsonManagedReference
	  
	  @JsonIgnore 
	  private Collection<Doctor> doctors = new ArrayList<Doctor>();
	 
	
	
	 
	

}
