package com.spring.security.web.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.spring.security.constraint.FieldMatch;

import javax.validation.constraints.AssertTrue;

/**
 * @author Bowen Ng
 *
 */

public class UploadDto {

	private Long id;
	

	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	
	 
}
