package com.spring.security.service;

import com.spring.security.model.Sessionid;
import com.spring.security.model.User;

public interface IdService {

    Sessionid findById(Long id);
    
    Sessionid increment(String email);

    Long getSession();
}
