package com.brahim.projection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//This class used as projection to map jpa the result is used to display  specialities
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectSpeciality {
	private int id;
	private String speciality;

}
