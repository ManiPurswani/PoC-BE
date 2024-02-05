package com.phicom.poc.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phicom.poc.model.Field;
import com.phicom.poc.model.FormData;
import com.phicom.poc.model.PreRequisite;
import com.phicom.poc.model.RequestFields;
import com.phicom.poc.model.RequestService;
import com.phicom.poc.model.ResponseData;
import com.phicom.poc.model.Validation;
import com.phicom.poc.model.ValidationServiceResponse;




@RestController
@RequestMapping("/validationservice")
public class ValidationService {
	
	
	@PostMapping
	public ResponseEntity<ResponseData> createFormDetails(@RequestBody RequestService validationServiceRequest) {
       
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseData responseData = new ResponseData();
		
		
        ValidationServiceResponse response = new ValidationServiceResponse();
        List<ValidationServiceResponse> formFieldResponses = new ArrayList<>();
        List<RequestFields> serviceRequestFieldsCopy = validationServiceRequest.getFormFields();

        boolean isFieldAvailable = false;
        

		try {
			
			
			List<RequestFields> serviceRequestFields = validationServiceRequest.getFormFields();
			
			
			// Load the JSON file
            ClassPathResource resource = new ClassPathResource("data.json");

            // ObjectMapper to read the JSON file and map it to Java object
            FormData formData = objectMapper.readValue(resource.getInputStream(), FormData.class);

            // Access fields values form Form formation JSON
            List<Field> fields = formData.getForm().getFields();
            
           
            
            for (Field formFormationfield : fields) {
            	
                // Read id, field_label, and required from fields array
                int id = formFormationfield.getId();
                String fieldLabel = formFormationfield.getFieldLabel();
                boolean required = formFormationfield.isRequired();
                String valueType = formFormationfield.getValue_type();
                
                 // Get validation related values
                List<Validation> validations = formFormationfield.getValidation();
                List<PreRequisite> preRequisites = formFormationfield.getPreRequisites();
            	response = new ValidationServiceResponse();
            	response.setName(fieldLabel);
            	response.setStatus("");
            	response.setReason("");
	                for (Validation validation : validations) {

	                    String key = validation.getKey();
	                    String type = validation.getType();
	                    String value = validation.getValue();
	                    String errorMessage = validation.getError_message();
	                    
	        	        // Validate form fields
	        	        for (RequestFields serviceRequestField : serviceRequestFields) {
	        	            //ValidationServiceResponse response = new ValidationServiceResponse();
	        	        	
	        	        	if(fieldLabel.equals(serviceRequestField.getName())) {
	    	                	isFieldAvailable = true;
	        	        		//response = new ValidationServiceResponse();
	            	            response.setName(serviceRequestField.getName());
	            	            response.setValue(serviceRequestField.getValue());
	            	            
	            	           
	            	        	if (valueType.equals("num")) {
	            	        		try {
	            	        			int val = (Integer) serviceRequestField.getValue();
	            	        		} catch (ClassCastException e) {
	            	        			response.setStatus("failure");
	        		                    response.setReason(errorMessage);
	        		                    break;
	            	        		}
	        	            	    int val = (Integer) serviceRequestField.getValue();
	        	            	    String stringValue = String.valueOf(val);
	        	            	    if(!key.isBlank() && key.equalsIgnoreCase("regex_check")) {
	        	            	    	if (!stringValue.matches("^[0-9]+$")) {
	            	            	    	response.setStatus("failure");
	            		                    response.setReason(errorMessage);
	            	            	    } else {
	        	            	    		if(response.getStatus() == null  || !response.getStatus().equals("failure")) {
	            	            	    		response.setStatus("success");
	                		                    response.setReason("");
	            	            	    	}
	            		              }
	        	            	    } else if(!key.isBlank() && key.equalsIgnoreCase("value_check")) {
	        	            	    	if(type.equalsIgnoreCase("greater")) {
	        	            	    		if (!(val > Integer.parseInt(value))) {
	                	            	    	response.setStatus("failure");
	                		                    response.setReason(errorMessage);
	                	            	    }
	        	            	    	} else {
	        	            	    		if(response.getStatus() == null  || !response.getStatus().equals("failure")) {
	            	            	    		response.setStatus("success");
	                		                    response.setReason("");
	            	            	    	}
	        	            	    	}
	        	            	    } 
	        	            	    
	        	            	} else {
	        	            		if(!key.isBlank() && key.equalsIgnoreCase("regex_check")) {
	        	            			if (!((String) serviceRequestField.getValue()).matches(value)) {
	        	            				response.setStatus("failure");
	        	            				response.setReason(errorMessage);
	        	            			} else {
	        	            				response.setStatus("success");
	        	            				response.setReason("");
	        	            			}
	        	            		}
	        	            	}
	                            //formFieldResponses.add(response);
	                            break;
	        	        	}
	
	        	        }
	
	        	        //formFieldResponses.add(response);
	                }
	                
	                //formFieldResponses.add(response);
	                
	                if (preRequisites != null) {
	                	boolean isPreviousStepSuccess = false;
	                	for (PreRequisite preRequisite : preRequisites) {
	                		
		                	String field_Label = preRequisite.getField_label();
		                    String value = preRequisite.getValue();
		                    String logic = preRequisite.getLogic();
		                    //String errorMessage = validation.getError_message();
		                    
		                    
		        	        // Validate form fields
		                    //List<RequestFields> 
		                    serviceRequestFieldsCopy = validationServiceRequest.getFormFields();
		        	        for (RequestFields serviceRequestField : serviceRequestFields) {
		        	            //ValidationServiceResponse response = new ValidationServiceResponse();
		        	        	
		        	        	if(fieldLabel.equals(serviceRequestField.getName())) {
		        	        		isFieldAvailable = true;
		            	            response.setName(serviceRequestField.getName());
		            	            response.setValue(serviceRequestField.getValue());
		            	            
		            	            String requiredPreReqLabel = preRequisite.getField_label();
		            	            String requiredPreReqValue = preRequisite.getValue();
		            	            
		            	            if(!formFormationfield.getOptions().contains(serviceRequestField.getValue())) {
		            	            	response.setStatus("Failure");
	            	            		response.setReason("Enter valid value for " + serviceRequestField.getName() + ".");
	            	            		break;
		            	            }
		            	            Optional<String> foundValue = serviceRequestFieldsCopy.stream()
		            	                    .filter(obj -> obj.getName().equals(requiredPreReqLabel))
		            	                    .map(obj -> (String) obj.getValue())
		            	                    .findFirst();
		            	            
		            	            if(foundValue.isPresent()) {
		            	            	  
		            	            	  if(preRequisite.getLogic() == null) {
		            	            		  
			            	            	  if(value.equals(foundValue.get())) {
			            	            		  isPreviousStepSuccess = true;
			            	            		  response.setStatus("success");
			            	            		  response.setReason("");
		            	            		  } else {
		            	            			  isPreviousStepSuccess = false;
			            	            		  response.setStatus("Failure");
			            	            		  response.setReason(preRequisite.getError_message());
		            	            		  
		            	            		  }
		            	            	  } else if(!preRequisite.getLogic().equals("or") || !isPreviousStepSuccess) {
		            	            		  if(value.equals(foundValue.get())) {
			            	            		  isPreviousStepSuccess = true;
			            	            		  response.setStatus("success");
			            	            		  response.setReason("");
		            	            		  } else {
		            	            			  isPreviousStepSuccess = false;
			            	            		  response.setStatus("Failure");
			            	            		  response.setReason(preRequisite.getError_message());
		            	            		  
		            	            		  }
		            	            	  }

		            	            } else {
		            	            	response.setStatus("Failure");
	            	            		response.setReason(preRequisite.getError_message());
		            	            }
		            	          
	        	        		
		        	        	}
		        	        }
		        	       
		        	        	
		                }
	                }
	                
	                
	                if(isFieldAvailable = true) {
	                	final String name = response.getName();
	                	Optional<RequestFields> fieldAvailable = validationServiceRequest.getFormFields().stream()
	                            .filter(obj -> obj.getName().equals(name))
	                            .findFirst();
	                	Optional<Object> value = validationServiceRequest.getFormFields().stream()
        	                    .filter(obj -> obj.getName().equals(name))
        	                    .map(obj ->  obj.getValue())
        	                    .findFirst();
	                	response.setValue(value);
	                	if(fieldAvailable.isPresent()) {
	                		formFieldResponses.add(response);
	                	}else {
	                		// Field already present in response. Do not add. 
	                	}
	                	
	                	isFieldAvailable = false;
	                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
        // Prepare response
        //ResponseData responseData = new ResponseData();
    	responseData.setFormType(validationServiceRequest.getForm_type());
        responseData.setBankName(validationServiceRequest.getBank_name());
        responseData.setFormFields(formFieldResponses);

        return ResponseEntity.ok(responseData);

    }
	

}
