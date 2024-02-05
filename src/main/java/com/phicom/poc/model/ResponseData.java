package com.phicom.poc.model;

import java.util.List;

public class ResponseData {
    
	private String formType;
    private String bankName;
    private List<ValidationServiceResponse> formFields;
    
	public String getFormType() {
		return formType;
	}
	public void setFormType(String formType) {
		this.formType = formType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public List<ValidationServiceResponse> getFormFields() {
		return formFields;
	}
	public void setFormFields(List<ValidationServiceResponse> formFields) {
		this.formFields = formFields;
	}


}
