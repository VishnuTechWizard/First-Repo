package com.example.RegisterLogin.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserDetails {

	@Id
	private String id;	
	private String user_id;
	private PersonalDetails personalDetails;
	private ProfessionalDetails professionalDetails;
	private MedicalDetails medicalDetails;
	private String status;
	private int formStatus;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public PersonalDetails getPersonalDetails() {
		return personalDetails;
	}

	public void setPersonalDetails(PersonalDetails personalDetails) {
		this.personalDetails = personalDetails;
	}

	public ProfessionalDetails getProfessionalDetails() {
		return professionalDetails;
	}

	public void setProfessionalDetails(ProfessionalDetails professionalDetails) {
		this.professionalDetails = professionalDetails;
	}

	public MedicalDetails getMedicalDetails() {
		return medicalDetails;
	}

	public void setMedicalDetails(MedicalDetails medicalDetails) {
		this.medicalDetails = medicalDetails;
	}
	

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public int getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(int formStatus) {
		this.formStatus = formStatus;
	}

	public UserDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserDetails(String id, String user_id, PersonalDetails personalDetails,
			ProfessionalDetails professionalDetails, MedicalDetails medicalDetails, String status, int formStatus) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.personalDetails = personalDetails;
		this.professionalDetails = professionalDetails;
		this.medicalDetails = medicalDetails;
		this.status = status;
		this.formStatus = formStatus;
	}


	}


