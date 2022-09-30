package com.durgesh.integration;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.durgesh.entity.Course;



@SpringBootTest
public class CourseControllerIntegrationTests {
	
	@Test
	void getAllCoursesIntegrationTests() throws JSONException {
		String expected = "[\r\n" + 
				"  {\r\n" + 
				"    \"courseId\": 30,\r\n" + 
				"    \"courseName\": \"python general-purpose programming\",\r\n" + 
				"    \"courseDuration\": 2.5,\r\n" + 
				"    \"courseDescription\": \"Python is a high-level, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation. Python is dynamically-typed and garbage-collected. \",\r\n" + 
				"    \"technology\": \"Python\",\r\n" + 
				"    \"launchUrl\": \"http://www.pythontech.com\"\r\n" + 
				"  }\r\n" + 
				"]";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:8070/api/v1.0/lms/courses/getAll", String.class);

		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody(), false);
	}

	@Test
	void getAllCourseByTechnology() throws JSONException {
		String expected = "[\r\n" + 
				"  {\r\n" + 
				"    \"courseId\": 30,\r\n" + 
				"    \"courseName\": \"python general-purpose programming\",\r\n" + 
				"    \"courseDuration\": 2.5,\r\n" + 
				"    \"courseDescription\": \"Python is a high-level, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation. Python is dynamically-typed and garbage-collected. \",\r\n" + 
				"    \"technology\": \"Python\",\r\n" + 
				"    \"launchUrl\": \"http://www.pythontech.com\"\r\n" + 
				"  }\r\n" + 
				"]";
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:8070/api/v1.0/lms/courses/info/Python",
				String.class);

		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody(), false);
	}
	
	@Test
	public void test_deleteCourseIntegrationTest() throws Exception {

		Course course = new Course(30, "python general-purpose programming", 2.5,"Python is a high-level, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation. Python is dynamically-typed and garbage-collected.","Python","http://www.pythontech.com");

		String expected = " [\r\n" + 
				"  {\r\n" + 
				"    \"courseId\": 30,\r\n" + 
				"    \"courseName\": \"python general-purpose programming\",\r\n" + 
				"    \"courseDuration\": 2.5,\r\n" + 
				"    \"courseDescription\": \"Python is a high-level, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation. Python is dynamically-typed and garbage-collected. \",\r\n" + 
				"    \"technology\": \"Python\",\r\n" + 
				"    \"launchUrl\": \"http://www.pythontech.com\"\r\n" + 
				"  }\r\n" + 
				"]";

		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Course> request = new HttpEntity<Course>(course, headers);

		ResponseEntity<String> res = restTemplate.exchange("http://localhost:8070/api/v1.0/lms/courses/delete/python general-purpose programming",HttpMethod.DELETE,request, String.class);

		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody(), false);
	}
	
	
	@Test
	public void getByDurationRangeTest() throws Exception {

		String expected = " [\r\n" + 
				"  {\r\n" + 
				"    \"courseId\": 30,\r\n" + 
				"    \"courseName\": \"python general-purpose programming\",\r\n" + 
				"    \"courseDuration\": 2.5,\r\n" + 
				"    \"courseDescription\": \"Python is a high-level, general-purpose programming language. Its design philosophy emphasizes code readability with the use of significant indentation. Python is dynamically-typed and garbage-collected. \",\r\n" + 
				"    \"technology\": \"Python\",\r\n" + 
				"    \"launchUrl\": \"http://www.pythontech.com\"\r\n" + 
				"  }\r\n" + 
				"]";

		RestTemplate restTemplate = new RestTemplate();
	

		ResponseEntity<String> res = restTemplate.getForEntity("http://localhost:8070/api/v1.0/lms/courses/get/python/2",
				String.class);

		System.out.println(res.getBody());
		System.out.println(res.getStatusCode());
		
		assertEquals(HttpStatus.OK,res.getStatusCode());
		JSONAssert.assertEquals(expected, res.getBody(), false);
	}
}
