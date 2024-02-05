package com.phicom.poc.model;

public class PreRequisite {
	
	private String field_label;
	private String value;
    private String logic;
    private String error_message;
    
	public String getField_label() {
		return field_label;
	}
	public void setField_label(String field_label) {
		this.field_label = field_label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLogic() {
		return logic;
	}
	public void setLogic(String logic) {
		this.logic = logic;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	} 
    
}
