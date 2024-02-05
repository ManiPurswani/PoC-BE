package com.phicom.poc.model;

public class TestForm {

	private String formId;
	private String firstName;
	private String lastName;
	
	public TestForm(String formId, String firstName, String lastName) {
		super();
		this.formId = formId;
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFormId() {
		return formId;
	}
	public void setFormID(String formId) {
		this.formId = formId;
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

}
