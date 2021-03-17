package com.brahim.projection;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorWithSpeciality {
	private int id;
	private String fname;
	private String lname;
	private String phone;
	private String speciality;

}
