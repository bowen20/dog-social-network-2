package com.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.model.Sessiontracker;

@Repository
public interface SessionRepository extends JpaRepository<Sessiontracker, Long>{
	
	Sessiontracker findById(Long id);

}