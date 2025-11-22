package com.springboot.binding;




import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@NotBlank(message = "Name should not be empty")
	private String name;
	
	@NotBlank(message = "Email should not be empty")
	@Email(message = "Please enter a valid email address.")
	private String email;
	
	@NotBlank(message = "Please create a password.")
	@Size(min = 6, message = "Password should be at least 6 characters long.")
	private String password;
	
	@NotBlank(message = "Please select your gender.")
	private String gender;
	
	@NotNull(message = "Please enter your phone number.")
	private Long phno;
	
	
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getPhno() {
		return phno;
	}
	public void setPhno(Long phno) {
		this.phno = phno;
	}
	
	
	
	

}
