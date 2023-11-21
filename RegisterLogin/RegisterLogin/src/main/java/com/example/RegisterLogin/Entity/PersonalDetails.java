package com.example.RegisterLogin.Entity;

public class PersonalDetails {
	
	
	private String title;
	private String firstName;
	private String lastName;
	private String gender;
	private String dob;
	private int age;
	private String address;
	private String state;
	private String country;
	private String city;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public PersonalDetails(String title, String firstName, String lastName, String gender, String dob, int age,
			String address, String state, String country, String city) {
		super();
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.age = age;
		this.address = address;
		this.state = state;
		this.country = country;
		this.city = city;
	}
	public PersonalDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	

}
