package com.durgesh.jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.jwt.entity.User;


public interface UserDao extends JpaRepository<User,String> {

	
}
