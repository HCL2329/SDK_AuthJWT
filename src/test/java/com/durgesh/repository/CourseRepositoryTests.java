package com.durgesh.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.durgesh.dao.CourseRepository;
import com.durgesh.entity.Course;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CourseRepositoryTests {

	@Autowired
	CourseRepository courseRepository;

	
	private Course myCourse;

	@BeforeEach
	void init() {

		myCourse = new Course(1, "python general-purpose programming", 3.5,
				"Python is a high-level, general-purpose programming language. Its design philosophy"
						+ " emphasizes code readability with the use of significant indentation",
				"Python", "http://www.pythontech.com");

	}

	@Test
	@DisplayName("saveCourse")
	void save() {
		Course newcourse = courseRepository.save(myCourse);
		assertNotNull(newcourse);
		assertThat(newcourse.getCourseId()).isNotEqualTo(null);
	}

	@Test
	@DisplayName("AllCourse")
	void getAllCourses() {
		courseRepository.save(myCourse);

		List<Course> list = courseRepository.findAll();

		assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(6, list.size());
	}

	@Test
	@DisplayName("CourseName")
	void getByCourseName() {
		courseRepository.save(myCourse);

		String coursename = "python general-purpose programming";
		List<Course> findByCourseName = courseRepository.findByCourseName(coursename);

		assertNotNull(findByCourseName);

	}

	@Test
	@DisplayName("getByDurationRange")
	void getByDurationRange() {
		courseRepository.save(myCourse);

		String technology = "python";
		Double courseDuration = 2.0;
		List<Course> courses = courseRepository.findByDurationRange(technology, courseDuration);

		assertNotNull(courses);

	}

	@Test
	@DisplayName("deleteByCourseName")
	void deleteByCourseName() {
		courseRepository.save(myCourse);

		String coursename = "python general-purpose programming";

		courseRepository.deleteByCourseName(coursename);
		verify(courseRepository, times(1)).delete(myCourse);
		

	}

}
