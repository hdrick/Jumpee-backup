package com.dricks.jumpee.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="users")
public class LoginUser {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int user_id;
	
    @NotEmpty(message = "Email address is required")
	@Column(name="email")
	private String email;
    
	@Column(name="first_name")
    private String firstName;
	
	@Column(name="last_name")
    private String lastName;
	
	@NotEmpty(message = "Password is required")
	@Column(name="password")
	private String password;

	public LoginUser() {}

	public LoginUser(int user_id, String email, String firstName,
			String lastName, String password) {
		this.user_id = user_id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	@Override
	public String toString() {
		return "LoginUser [user_id=" + user_id + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", password=" + password + "]";
	}


	
}
