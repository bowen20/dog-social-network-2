package com.spring.security.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.model.Sessiontracker;
import com.spring.security.repository.SessionRepository;

@Service
public class SessionServiceImpl implements SessionService {

	@Autowired
	private SessionRepository sessionRepository;

	
	
	@Override
	public Sessiontracker save(String email, Long session) {
		  Sessiontracker sessiontracker = new Sessiontracker();
	        sessiontracker.setId(session);
	        sessiontracker.setLoginTime("0");
	        sessiontracker.setLogoutTime("0");
	        sessiontracker.setSessionStatus(1);
	        sessiontracker.setEmail(email);
	        System.out.println("attempted to save the session");
	        return sessionRepository.save(sessiontracker);
	}



	@Override
	public Sessiontracker findById(Long id) {
		
		return sessionRepository.findById(id);
	}



}
