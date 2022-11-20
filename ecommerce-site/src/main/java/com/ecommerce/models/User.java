package com.ecommerce.models;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author yaswanth.perumalla
 * 
 *         User model class
 *
 */
@Document
public class User {
	public User() {
	}

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String loginId;
	private String password;
	private String confirmPassword;
	private String contactNumber;

	public String getId() {
		return id;
	}

	public User setId(String id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getLoginId() {
		return loginId;
	}

	public User setLoginId(String loginId) {
		this.loginId = loginId;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public User setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
		return this;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public User setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
		return this;
	}

}
