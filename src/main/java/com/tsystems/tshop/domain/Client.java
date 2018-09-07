package com.tsystems.tshop.domain;

import java.time.LocalDate;

public class Client {
	
	private String name;
	private String surname;
	private LocalDate birthdate;
	private String email;
	private String password;
	
	public Client() {}
	
	public Client(final String name, final String surname, final LocalDate birthdate, final String email) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
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
	
	

}
