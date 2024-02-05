package com.phicom.poc.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties("tabs")
public class Form {

	@JsonProperty("form_type")
    private String formType;

    @JsonProperty("bank_name")
    private String bankName;

    private List<Field> fields;
    
    @JsonIgnore
    private List<Tab> tabs;

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

	public List<Tab> getTabs() {
		return tabs;
	}

	public void setTabs(List<Tab> tabs) {
		this.tabs = tabs;
	}
    
}
