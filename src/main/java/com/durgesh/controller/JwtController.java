package com.durgesh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.entity.JwtRequest;
import com.durgesh.entity.JwtResponse;
import com.durgesh.service.JwtService;

@RestController
@CrossOrigin("http://localhost:4200/")
@RequestMapping("/api/v1.0/lms/courses")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/login"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
