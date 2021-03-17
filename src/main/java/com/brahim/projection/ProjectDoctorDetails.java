package com.brahim.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This class used as projection to map jpa the result is used to display doctor's details after selecting the liknk doctos
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ProjectDoctorDetails {
	private int id;
	private String fName;
	private String lName;
	private String phone;
	private String speciality;
	

}
