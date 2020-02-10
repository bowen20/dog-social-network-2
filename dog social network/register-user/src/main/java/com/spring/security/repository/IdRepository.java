package com.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.model.Sessionid;

@Repository
public interface IdRepository extends JpaRepository<Sessionid, Long>{

	Sessionid findById(Long id);
	
}
