package com.spring.security.service;

import com.spring.security.model.User;
import com.spring.security.web.dto.UpdateDto;
import com.spring.security.web.dto.UserRegistrationDto;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);
    
    User update(UpdateDto userdto);
    
    User findById(Long id);
    
    public List<User> findAll();


	User upload(String email, String fileName);
        
}
