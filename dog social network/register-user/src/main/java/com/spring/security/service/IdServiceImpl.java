package com.spring.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.model.Sessionid;
import com.spring.security.model.Sessiontracker;
import com.spring.security.repository.IdRepository;

@Service
public class IdServiceImpl implements IdService {

	@Autowired
	private IdRepository idRepository;
	
	@Autowired
	private SessionService sessionService;
	
	@Override
	public Sessionid findById(Long id) {
		return idRepository.findById(id);
	}

	@Override
	public Sessionid increment(String email) {
		Sessionid sessionid = idRepository.findById((long) 0);
		long session = sessionid.getSession();
		session++;
		sessionid.setSession(session);
		sessionService.save(email, session);
		return idRepository.save(sessionid);
	}

	@Override
	public Long getSession() {
		Sessionid sessionid = idRepository.findById((long) 0);
		long session = sessionid.getSession();
		return session;
	}

}
