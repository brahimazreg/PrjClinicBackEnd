package com.brahim.projection;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public  class Project {
	private int id;
	private String firstName;
	private String lastName;
	private String phone;
	@Column(name = "date_visit",columnDefinition = "DATE")
	private LocalDate date_visit=LocalDate.now();
	// to add the name of the doctor
	private String fName;
	

}
