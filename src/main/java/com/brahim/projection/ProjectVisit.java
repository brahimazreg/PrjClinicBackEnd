package com.brahim.projection;

import java.time.LocalDate;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectVisit {
	
	private int id ;
	@Column(name = "date_visit",columnDefinition = "DATE")
	private LocalDate date_visit=LocalDate.now();
	private int doctor_visit;
	private int patient_visit;

}
