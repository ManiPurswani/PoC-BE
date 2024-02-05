package com.phicom.poc.services;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.phicom.poc.model.TestForm;

@RestController
@RequestMapping("/testservice")
public class TestService {
	
	TestForm form;
	
	@GetMapping("{formId}")
	public TestForm getFormDetails(String formId) {
		
		return form;
		//return new Form("CardForm", "Manish", "Purswani");
	}
	
	@PostMapping
	public String createFormDetails(@RequestBody TestForm form) {
		
		this.form = form;
		return "form created....";
	}
	
	@PutMapping
	public String updateFormDetails(@RequestBody TestForm form) {
		
		this.form = form;
		return "form Updated....";
	}
	
	@DeleteMapping("{formId}")
	public String deleteFormDetails(String formId) {
		
		this.form = null;
		return "form deleted....";
	}
	
	/*
	 package com.phicom.poc.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.phicom.poc.model.FormField;
import com.phicom.poc.model.ValidationServiceResponse;
import com.phicom.poc.model.RequestData;
import com.phicom.poc.model.ResponseData;


@RestController
@RequestMapping("/validationservice")
public class ValidationService {
	
	
	@PostMapping
	public ResponseEntity<ResponseData> createFormDetails(@RequestBody RequestData requestData) {
       
        ResponseData responseData = new ResponseData();
		List<FormField> formFields = requestData.getFormFields();
        List<ValidationServiceResponse> formFieldResponses = new ArrayList<>();

        // Validate form fields
        for (FormField field : formFields) {
            ValidationServiceResponse response = new ValidationServiceResponse();
            response.setName(field.getName());
            response.setValue(field.getValue());

            // Perform validation
            if ("first_name".equals(field.getName())) {
            	System.out.println("field.getName() - " + field.getName() + " - " + field.getValue());
                if (!((String) field.getValue()).matches("^[a-zA-Z]+$")) {
                    response.setStatus("failure");
                    response.setReason("First name can only have characters.");
                } else {
                    response.setStatus("success");
                    response.setReason("");
                }
			}
            
            if ("last_name".equals(field.getName())) {
            	System.out.println("field.getName() - " + field.getName() + " - " + field.getValue());
                if (!((String) field.getValue()).matches("^[a-zA-Z]+$")) {
                    response.setStatus("failure");
                    response.setReason("Last name can only have characters.");
                } else {
                    response.setStatus("success");
                    response.setReason("");
                }
            } 

            if ("age".equals(field.getName())) {
            	System.out.println("field.getName() - " + field.getName() + " - " + field.getValue());
                if (!((String) field.getValue()).matches("^[0-9]+$")) {
                    response.setStatus("failure");
                    response.setReason("Age can only be an integer.");
                } else {
                    response.setStatus("success");
                    response.setReason("");
                }
            }
            
            formFieldResponses.add(response);
        }

        // Prepare response
        //ResponseData responseData = new ResponseData();
    	System.out.println("FormType - " + requestData.getForm_type() + " - " + requestData.getBank_name());

        responseData.setFormType(requestData.getForm_type());
        responseData.setBankName(requestData.getBank_name());
        responseData.setFormFields(formFieldResponses);

        return ResponseEntity.ok(responseData);
    }
	

}

	 */

}
