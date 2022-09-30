package com.durgesh.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Course {
	@Id
	@GeneratedValue
	private Integer courseId;
	
	private String courseName;
	
	
	private Double courseDuration;
	

	private String courseDescription;
	
	
	private String technology;
	
	
	private String launchUrl;
}
