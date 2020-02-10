package com.spring.security.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sessionid {
   
    @Id
    private Long id;
    
    private Long session;
    
    public Sessionid() {
    	
    }



	public Long getSession() {
		return session;
	}


	public void setSession(Long session) {
		this.session = session;
	}




	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	@Override
    public String toString() {
        return "Sessionid{" +
                "id=" + id + '\'' +
                ", session='" + session +
                '}';
    }
}
