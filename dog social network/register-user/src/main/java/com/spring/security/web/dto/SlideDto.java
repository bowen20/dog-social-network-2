package com.spring.security.web.dto;

public class SlideDto {

	private int index;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
    public SlideDto(int index) {
    	this.index = index;
    }
	
}
