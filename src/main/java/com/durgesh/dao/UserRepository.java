package com.durgesh.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.entity.User;


public interface UserRepository extends JpaRepository<User,String> {

	
}
