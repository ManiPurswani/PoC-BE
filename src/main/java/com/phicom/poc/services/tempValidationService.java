package com.phicom.poc.services;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.phicom.poc.model.FormField;
import com.phicom.poc.model.ValidationServiceResponse;
import com.phicom.poc.model.FormData;
import com.phicom.poc.model.ResponseData;


@RestController
@RequestMapping("/tempvalidationservice")
public class tempValidationService {
	
	
	@PostMapping
	public ResponseEntity<ResponseData> createFormDetails(@RequestBody FormData requestData) {
       
		
		// Read the contents of the data.js file
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get("D:/POCData/data.js")));
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Create a script engine manager
        ScriptEngineManager manager = new ScriptEngineManager();

        // Create a JavaScript engine
        ScriptEngine engine = manager.getEngineByName("javascript");

        try {
            engine.eval(new FileReader("C:/POCData/data.js"));

            // Access the form object and its fields array
            Object fields = engine.eval("form.fields");

            // Assuming 'fields' is an array
            if (fields instanceof javax.script.Bindings[]) {
                javax.script.Bindings[] fieldArray = (javax.script.Bindings[]) fields;

                // Iterate through the fields array
                for (javax.script.Bindings field : fieldArray) {
                    
                	// Get the id and type values from each validation object
                    Object id1 = field.get("id");
                    Object type1 = field.get("type");

                    // Print the id and type values
                    System.out.println("ID: " + id1 + ", Type: " + type1);
                    
                	// Access the validation array within each field
                    Object validation = field.get("validation");

                    // Assuming 'validation' is an array
                    if (validation instanceof javax.script.Bindings[]) {
                        javax.script.Bindings[] validationArray = (javax.script.Bindings[]) validation;

                        // Iterate through the validation array
                        for (javax.script.Bindings validationObj : validationArray) {
                            // Get the id and type values from each validation object
                            Object id = validationObj.get("id");
                            Object type = validationObj.get("type");

                            // Print the id and type values
                            System.out.println("ID: " + id + ", Type: " + type);
                        }
                    }
                }
            }
        } catch (ScriptException | IOException e) {
            e.printStackTrace();
        }
		
		
		
        ResponseData responseData = new ResponseData();
		List<FormField> formFields = (List<FormField>) requestData.getForm();//requestData.getFormFields();
        List<ValidationServiceResponse> formFieldResponses = new ArrayList<>();

        // Validate form fields
        for (FormField field : formFields) {
            ValidationServiceResponse response = new ValidationServiceResponse();
            response.setName(field.getName());
            response.setValue(field.getValue());

            // Perform validation
            if ("first_name".equals(field.getName())) {
            	if (!((String) field.getValue()).matches("^[a-zA-Z]+$")) {
                    response.setStatus("failure");
                    response.setReason("First name can only have characters.");
                } else {
                    response.setStatus("success");
                    response.setReason("");
                }
			}
            
            if ("last_name".equals(field.getName())) {
            	if (!((String) field.getValue()).matches("^[a-zA-Z]+$")) {
                    response.setStatus("failure");
                    response.setReason("Last name can only have characters.");
                } else {
                    response.setStatus("success");
                    response.setReason("");
                }
            } 

            if ("age".equals(field.getName())) {
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
		/*
		 * responseData.setFormType(requestData.getForm_type());
		 * responseData.setBankName(requestData.getBank_name());
		 */
        responseData.setFormFields(formFieldResponses);

        return ResponseEntity.ok(responseData);
    }
	

}
