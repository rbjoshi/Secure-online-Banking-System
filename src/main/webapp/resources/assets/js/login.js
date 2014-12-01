$(document).ready(function() {
    
	$.fn.bootstrapValidator.validators.password = {
	    validate: function(validator, $field, options) {
	        var value = $field.val();
	        if (value === '') {
	            return true;
	        }
	
	        if (value.length < 8) {
	            return false;
	        }
	        if (value === value.toLowerCase()) {
	            return false;
	        }
	        if (value === value.toUpperCase()) {
	            return false;
	        }
	        if (value.search(/[0-9]/) < 0) {
	            return false;
	        }
	
	        return true;
	    }
	};
	
	$('#login-form').bootstrapValidator({
    	excluded: [':disabled', ':hidden', ':not(:visible)'],
        live: 'enabled',
        fields: {
        	password: {
                validators: {  
                	notEmpty: {
                		message: 'Password can not be empty'
                	},
                	password: {
                		message: 'Password is not valid'
                	}
                }
            },
        }
    });
});