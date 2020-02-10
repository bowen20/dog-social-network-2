package com.spring.security.web.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.spring.security.constraint.FieldMatch;

import javax.validation.constraints.AssertTrue;

/**
 * @author Bowen Ng
 *
 */

@FieldMatch.List({
        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
})

public class UpdateDto {

	private Long id;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String password;

	@NotEmpty
	private String confirmPassword;
	
    @Email
    @NotEmpty
    private String email;
	 
    @Email
    @NotEmpty
	private String confirmEmail;

    @NotEmpty
    private String breed;
    
    @NotEmpty
    private String color;
    
    @NotEmpty
    private String zipCode;
    
    @NotEmpty
    private String name;
    
    @NotEmpty
    private String age;
    
    @NotEmpty
    private String weight;

	private String fileName;
    
    

    public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	
	  public String getConfirmPassword() { return confirmPassword; }
	  
	  public void setConfirmPassword(String confirmPassword) { this.confirmPassword
	  = confirmPassword; }
	 

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	
	  public String getConfirmEmail() { return confirmEmail; }
	  
	  public void setConfirmEmail(String confirmEmail) { this.confirmEmail =
	  confirmEmail; }

	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	 
}
