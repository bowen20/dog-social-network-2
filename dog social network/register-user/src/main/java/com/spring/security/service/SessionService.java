package com.spring.security.service;

import com.spring.security.model.Sessiontracker;
import com.spring.security.model.User;
import com.spring.security.web.dto.UserRegistrationDto;

public interface SessionService {
    Sessiontracker save(String email, Long session);
    
    Sessiontracker findById(Long id);

    
}