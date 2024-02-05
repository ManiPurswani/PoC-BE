package com.phicom.poc.model;

import java.util.List;


public class RequestService {
	
    private String form_type;
    private String bank_name;
    private List<RequestFields> formFields;
    
	public String getForm_type() {
		return form_type;
	}
	public void setForm_type(String form_type) {
		this.form_type = form_type;
	}
	public String getBank_name() {
		return bank_name;
	}
	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}
	public List<RequestFields> getFormFields() {
		return formFields;
	}
	public void setFormFields(List<RequestFields> formFields) {
		this.formFields = formFields;
	}


}