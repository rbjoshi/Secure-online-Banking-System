$(document).ready(function() {
    $('#login-form').bootstrapValidator({
    	excluded: [':disabled', ':hidden', ':not(:visible)'],
        live: 'enabled',
        fields: {
        	username: {
                validators: {
                    notEmpty: {
                        message: 'Username is required'
                    },
                    stringLength: {
                        min: 6,
                        max: 14,
                        message: 'Username must be more than 6 and less than 14 characters long'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: 'Username can only consist of alphabetical, number and underscore'
                    }
                }
            },
            'termsAndCondition[]': {
            	validators: {
            		choice :{
            			min: 1,
            			max:1,
            			message : "Please accept Terms and Conditions to continue."
            		}            
            	}
            }
        }
    });
});