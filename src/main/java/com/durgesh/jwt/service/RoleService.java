package com.durgesh.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.durgesh.jwt.dao.RoleDao;
import com.durgesh.jwt.entity.Role;

@Service
public class RoleService {

    @Autowired
    private RoleDao roleDao;

    public Role createNewRole(Role role) {
        return roleDao.save(role);
    }
}
