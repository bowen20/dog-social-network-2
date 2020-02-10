package com.spring.security.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Sessiontracker {

	@Id
	private Long id;
	private String loginTime;
	private String logoutTime;
	private int sessionStatus;
	private String email;
	
	public Sessiontracker() {
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getSessionStatus() {
		return sessionStatus;
	}
	public void setSessionStatus(int sessionStatus) {
		this.sessionStatus = sessionStatus;
	}
	
    @Override
    public String toString() {
        return "Sessiontracker{" +
                "id=" + id +
                ", loginTime='" + loginTime + '\'' +
                ", logoutTime='" + logoutTime + '\'' +
                ", sessionStatus='" + sessionStatus + '\'' + 
                ", email='" + email +
                '}';
    }
}