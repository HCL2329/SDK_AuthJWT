package com.durgesh.jwt.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.jwt.entity.Role;


public interface RoleDao extends JpaRepository<Role, String> {

	

	

}
