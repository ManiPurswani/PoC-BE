package com.phicom.poc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties({"tab","order","description","showFieldName","showField","showDescription","input_type"})
public class Field {

	private int id;

    @JsonProperty("field_label")
    private String fieldLabel;

    @JsonProperty("required")
    private boolean required;
    
    @JsonProperty("value_type")
    private String value_type;

   // @JsonProperty("validation")
    private List<Validation> validation;
    
    private List<String> options;
    
    private List<PreRequisite> preRequisites;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldLabel() {
        return fieldLabel;
    }

    public void setFieldLabel(String fieldLabel) {
        this.fieldLabel = fieldLabel;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public List<Validation> getValidation() {
        return validation;
    }

    public void setValidation(List<Validation> validation) {
        this.validation = validation;
    }

	public String getValue_type() {
		return value_type;
	}

	public void setValue_type(String value_type) {
		this.value_type = value_type;
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<PreRequisite> getPreRequisites() {
		return preRequisites;
	}

	public void setPreRequisites(List<PreRequisite> preRequisites) {
		this.preRequisites = preRequisites;
	}


}
