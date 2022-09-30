package com.durgesh.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.durgesh.dao.CourseRepository;
import com.durgesh.dao.UserRepository;
import com.durgesh.entity.Course;
import com.durgesh.entity.User;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTests {

	@Mock
	private CourseRepository courseRepository;
	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userServiceImpl;
//	@InjectMocks
//	private IUserService userService;

	private Course pythonCourse;
	private Course javaCourse;
	private User user;

	@BeforeEach
	void init() {

		pythonCourse = new Course(1, "python general-purpose programming", 3.5,
				"Python is a high-level, general-purpose programming language. Its design philosophy"
						+ " emphasizes code readability with the use of significant indentation",
				"Python", "http://www.pythontech.com");

		javaCourse = new Course(2, "java general-purpose programming", 3.5,
				"java is a high-level, general-purpose programming language. Its design philosophy"
						+ " emphasizes code readability with the use of significant indentation",
				"java", "http://www.pythontech.com");

		user = new User("durgesh", "durgesh", "Gupta", "durgesh");

	}

	@Test
	@DisplayName("save user")
	void save() {

		when(userRepository.save(any(User.class))).thenReturn(user);

		User saveUser = userServiceImpl.registerNewUser(user);

		assertNotNull(saveUser);
		assertThat(saveUser.getUserName()).isEqualTo("durgesh");
	}

	@Test
	@DisplayName("AllCourse")
	void getAllCourse() {

		List<Course> list = new ArrayList<>();
		list.add(pythonCourse);
		list.add(javaCourse);

		when(courseRepository.findAll()).thenReturn(list);

		List<Course> allCourse = userServiceImpl.getAllCourse();

		assertEquals(2, allCourse.size());
		assertNotNull(allCourse);
	}

	@Test
	@DisplayName("getByTechnology")
	void getByTechnology() {
		
		List<Course> list = new ArrayList<>();
		
		list.add(pythonCourse);
		list.add(javaCourse);


		String course = "java";

		when(courseRepository.findByTechnology(course)).thenReturn((list));
		List<Course> technology = userServiceImpl.getByTechnology(javaCourse.getTechnology());
		assertNotNull(technology);
		
	}
	
	
	@Test
	@DisplayName("getByDurationRange")
	void getByDurationRange() {
		
		List<Course> list = new ArrayList<>();
		
		list.add(pythonCourse);
		list.add(javaCourse);


		String technology = "python";
		Double courseDuration = 2.0;

		when(courseRepository.findByDurationRange(technology, courseDuration)).thenReturn((list));
		
		assertNotNull(userServiceImpl.getByDurationRange(technology, courseDuration));
		
	}
	
	
	
	

}
