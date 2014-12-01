$(document).ready(function() {
    $('#otp-form').bootstrapValidator({
    	excluded: [':disabled', ':hidden', ':not(:visible)'],
        fields: {
        	OTP: {
                validators: {                
                	notEmpty: {
                        message: 'One time verification password is required'
                    },
                    stringLength: {
                        min: 4,
                        message: 'Not a valid OTP, please try again.'
                    }
                }
            },
        }
    });
});