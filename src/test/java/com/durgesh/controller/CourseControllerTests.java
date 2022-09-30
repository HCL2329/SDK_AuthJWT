package com.durgesh.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.durgesh.dto.CourseDto;
import com.durgesh.entity.Course;
import com.durgesh.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = {CourseControllerTests.class})
public class CourseControllerTests {
	
	
	@MockBean
	private UserService userService;
	
	@InjectMocks
	private CoursesController coursesController;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	private List<Course> course;
	
	
	@BeforeEach
	void init() {

		 course=new ArrayList<>();
		 Course pythonCourse = new Course(1, "python general-purpose programming", 3.5,
				"Python is a high-level, general-purpose programming language. Its design philosophy"
						+ " emphasizes code readability with the use of significant indentation",
				"Python", "http://www.pythontech.com");

		 Course	javaCourse = new Course(2, "java general-purpose programming", 3.5,
				"java is a high-level, general-purpose programming language. Its design philosophy"
						+ " emphasizes code readability with the use of significant indentation",
				"java", "http://www.pythontech.com");

		
		course.add(pythonCourse);
		course.add(javaCourse);
		
	}
	
	@Test
	@DisplayName("getCourseByTech")
	void getCourseByTech() throws Exception {
	
		
		String technology = "java";
		
		when(userService.getByTechnology(technology)).thenReturn(course);
		ResponseEntity<List<CourseDto>> courseByTech = coursesController.getCourseByTech(technology);
		
		assertEquals(HttpStatus.FOUND, courseByTech.getStatusCode());
		assertEquals(1, courseByTech.getBody().size());
	
	}

	

	@Test
	@DisplayName("AllCourse")
	void shouldFetchAllCourse() throws Exception {
		
		when(userService.getAllCourse()).thenReturn(course);
		ResponseEntity<List<CourseDto>> allCourse = coursesController.getAllCourse();
		assertEquals(HttpStatus.FOUND, allCourse.getStatusCode());
		assertEquals(2, allCourse.getBody().size());
		
	}
	
	@Test
	public void test_deleteCourseByCourseName() {
		
		 Course	javaCourse = new Course(2, "java general-purpose programming", 3.5,
					"java is a high-level, general-purpose programming language. Its design philosophy"
							+ " emphasizes code readability with the use of significant indentation",
					"java", "http://www.pythontech.com");
		String courseName="java general-purpose programming";
		
		userService.deleteByCourseName(courseName);// mock
		
		ResponseEntity<String> CourseName = coursesController.deleteCourseByCourseName(courseName);
		
		assertEquals(HttpStatus.OK, CourseName.getStatusCode());
		
	}
	
}
