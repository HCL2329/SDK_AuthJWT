package com.durgesh.exception;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	
	// Model User class custom api validation function 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		Map<String, Object> errors = new HashMap<>();
		errors.put("timestamp", new Date());
		errors.put("status", status.value());
		ex.getBindingResult().getAllErrors().forEach((error) ->{
			
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(TechnologyNotFoundException.class)
    public ResponseEntity<Object> handleTechnologyNotFoundException(
    		TechnologyNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Technology Not Exist");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
	
	
	@ExceptionHandler(CourseNameNotFoundException.class)
    public ResponseEntity<Object> handleCourseNameNotFoundException(
    		CourseNameNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "CourseName Not Exist");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(CourseDurationNotFoundException.class)
    public ResponseEntity<Object> handleCourseDurationNotFoundException(
    		CourseDurationNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Sorry ,we could not find any results for your search,Try adjusting your search");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
	
	
	@ExceptionHandler(PageNotFoundException.class)
    public ResponseEntity<Object> handlePageNotFoundException(
    		PageNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Page number must be greater than zero");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    
	
	 // error handle for @Valid
   /* @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", status.value());

        //Get all errors
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, headers, status);
        }
        
        */

}
