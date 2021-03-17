package com.brahim.projection;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


// This class used as projection to map jpa the result is used to fill the dropdown lis
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDoctor {
	private int id;
	private String fname;
	

}
