$(document).ready(function() {
    $('#transaction-form').bootstrapValidator({
    	excluded: [':disabled', ':hidden', ':not(:visible)'],
        live: 'enabled',
        fields: {
        	balance: {
                validators: {
                    notEmpty: {
                        message: 'Amount is required'
                    },
                    stringLength: {
                        max: 10,
                        message: 'The amount field can have atmost 10 digits'
                    },
                    regexp: {
                        regexp: /^[1-9]{1}[0-9]+(.\d{2})?$/,
                        message: 'Amount should be in the write format XX.XX'
                    }
                }
            },
            
            address: {
                validators: {
                    notEmpty: {
                        message: 'Address field is required'
                    }
                }
            },
            
            phone: {
                validators: {
                    notEmpty: {
                        message: 'Phone number field is required'
                    },
                    regexp: {
                        regexp: /^[1-9]{1}[0-9]{9}$/,
                        message: 'Please enter a valid 10 digit phone number'
                    }
            		
                }
            },
            
            emailId: {
                validators: {
                	notEmpty: {
                        message: 'Email field is required'
                    },
                    emailAddress: {
                        message: 'Please enter a valid email address'
                    }
                }
            },
            
        }
    });
    
});