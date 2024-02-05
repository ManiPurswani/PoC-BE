package snippet;

public class Snippet {
	consider this below JSON format, from this i want to read "id", "field_label", "required" from "fields" array. Also "type" and "value" from "validation" array which is part of "fields" array
	
	{
	  "form": {
	    "form_type": "Credit Card Form",
	    "bank_name": "HDFC",
	    "tabs": [
	      {
	        "id": 1,
	        "name": "Personal Details",
	        "order": 1
	      },
	      {
	        "id": 2,
	        "name": "Create Password",
	        "order": 2
	      },
	      {
	        "id": 3,
	        "name": "Tab 3",
	        "order": 3
	      }
	    ],
	    "fields": [
	      {
	        "id": 1,
	        "tab": 1,
	        "order": 1,
	        "field_label": "first_name",
	        "description": "[Validation: Only alphabets]",
	        "showFieldName": true,
	        "showField": true,
	        "showDescription": true,
	        "input_type": "text",
	        "required": true,
	        "validation": [
	          {
	            "key": "regex_check",
	            "type": "equal",
	            "value": "^[a-zA-Z]+$",
	            "error_message": "First name can only have characters."
	          }
	        ]
	      },
	      {
	        "id": 3,
	        "tab": 1,
	        "order": 2,
	        "field_label": "age",
	        "description": "[18 - 70 years]",
	        "showField": true,
	        "showFieldName": true,
	        "showDescription": true,
	        "input_type": "text",
	        "required": true,
	        "validation": [
	          {
	            "key": "regex_check",
	            "type": "equal",
	            "value": "^[0-9]+$",
	            "error_message": "Age can only be an integer."
	          },
	          {
	            "logic": "and",
	            "key": "value_check",
	            "type": "greater",
	            "value": "18",
	            "error_message": "Age has to be greater than 18."
	          }
	        ]
	      }
	    ]
	  }
	}
}

