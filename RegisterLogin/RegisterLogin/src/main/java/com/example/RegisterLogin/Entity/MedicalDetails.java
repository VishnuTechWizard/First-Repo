package com.example.RegisterLogin.Entity;

public class MedicalDetails {

	private boolean smoke;
	private boolean drink;
	private boolean medicalConditions;
	private boolean surgeries;
	private boolean medicationsOrTreatments;

	public boolean isSmoke() {
		return smoke;
	}

	public void setSmoke(boolean smoke) {
		this.smoke = smoke;
	}

	public boolean isDrink() {
		return drink;
	}

	public void setDrink(boolean drink) {
		this.drink = drink;
	}

	public boolean isMedicalConditions() {
		return medicalConditions;
	}

	public void setMedicalConditions(boolean medicalConditions) {
		this.medicalConditions = medicalConditions;
	}

	public boolean isSurgeries() {
		return surgeries;
	}

	public void setSurgeries(boolean surgeries) {
		this.surgeries = surgeries;
	}

	public boolean isMedicationsOrTreatments() {
		return medicationsOrTreatments;
	}

	public void setMedicationsOrTreatments(boolean medicationsOrTreatments) {
		this.medicationsOrTreatments = medicationsOrTreatments;
	}

	public MedicalDetails() {
		super();
		// TODO Auto-generated constructor stub
	}


	

}
