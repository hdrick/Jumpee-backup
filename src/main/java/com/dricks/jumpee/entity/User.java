package com.dricks.jumpee.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.dricks.jumpee.helpers.FilterJsonView;
import com.fasterxml.jackson.annotation.JsonView;


@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int user_id;
	
	@JsonView(FilterJsonView.class)
    @NotEmpty(message = "Firstname is required")
	@Column(name="first_name")	
	private String firstName;
	
	@JsonView(FilterJsonView.class)
    @NotEmpty(message = "Lastname is required")
	@Column(name="last_name")
	private String lastName;
	
	@JsonView(FilterJsonView.class)
	@Email
	@Pattern(regexp="^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$", message="Please input valid Email address.")
    @NotEmpty(message = "Email address is required")
	@Column(name="email")
	private String email;
	
	@JsonView(FilterJsonView.class)
	@NotEmpty(message = "Contact number is required")
	@Pattern(regexp = "^(09|\\+639)\\d{9}$", message="Invalid format: Use PH format  +639457517381 | 09457517381 ")
	@Column(name="contact_number")
	private String contactNumber;
	
	@Size(min=8, message="Password should have 8 chracters")
	@Pattern(regexp=".*[A-Z].*.[0-9].*", message="Password should have alphanumeric.")
    @NotEmpty(message = "Password is required")
	@Column(name="password")
	private String password;
	
	@NotEmpty(message = "Confirm Password is required")
	@Column(name="confirm_password")
	private String confirmPassword;
    
	private String token;
	
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime tokenCreationDate;
	
	public User() {}
	
	public User(String firstName, String lastName, String email, String contactNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
	}
	
	public User(int user_id, String firstName, String lastName, String email, String contactNumber, String password,
			String confirmPassword) {
		super();
		this.user_id = user_id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.contactNumber = contactNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public LocalDateTime getTokenCreationDate() {
		return tokenCreationDate;
	}

	public void setTokenCreationDate(LocalDateTime tokenCreationDate) {
		this.tokenCreationDate = tokenCreationDate;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", contactNumber=" + contactNumber + ", password=" + password + "]";
	}
	
	public String unAuthorizeUser() {
		return "Message: Unauthorize User please login first!";
	}
}
