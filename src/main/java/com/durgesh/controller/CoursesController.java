package com.durgesh.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.dto.CourseDto;
import com.durgesh.entity.Course;
import com.durgesh.service.UserService;

@RestController
@RequestMapping("/api/v1.0/lms/courses")
@CrossOrigin("http://localhost:4200/")
public class CoursesController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	//Zuul call url
	//http://ctsjava1373.iiht.tech:8787/learning-management-system-user/api/v1.0/lms/courses/info/C++
	
	@GetMapping("/info/{technology}")
	public ResponseEntity<List<CourseDto>> getCourseByTech(@PathVariable(name = "technology") String technology) {
		
		List<CourseDto> allTechnology = userService.getByTechnology(technology)
		.stream().map(course->modelMapper.map(course,CourseDto.class)).collect(Collectors.toList());
		
		
		return new ResponseEntity<>(allTechnology,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CourseDto>> getAllCourse(){
		
		List<CourseDto> allCourse = userService.getAllCourse().stream().map(course->modelMapper.map(course,CourseDto.class)).collect(Collectors.toList());
		
		return new ResponseEntity<>(allCourse,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete/{courseName}")
	public ResponseEntity<String> deleteCourseByCourseName(@PathVariable(name = "courseName") String courseName) {
		
		userService.deleteByCourseName(courseName);
		
		return new ResponseEntity<>("deleted "+courseName,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/{technology}/{courseDuration}")
	public ResponseEntity<List<CourseDto>> getByDurationRange(@PathVariable("technology")String technology,@PathVariable("courseDuration")Double courseDuration) {
		
		List<Course> courseList = userService.getByDurationRange(technology, courseDuration);
		
		List<CourseDto> allCourse = courseList.stream().map(course->modelMapper.map(course,CourseDto.class)).collect(Collectors.toList());

		
		return new ResponseEntity<>(allCourse,HttpStatus.OK);
		
	}
	


}
