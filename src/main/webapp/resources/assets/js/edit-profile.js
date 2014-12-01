$(document).ready(function() {
    $('#edit-personal-form').bootstrapValidator({
    	excluded: [':disabled', ':hidden', ':not(:visible)'],
        live: 'enabled',
        fields: {
            firstName: {
                validators: {
                    notEmpty: {
                        message: 'First name field is required'
                    }
                }
            },
            
            lastName: {
                validators: {
                    notEmpty: {
                        message: 'Last name field is required'
                    }
                }
            },
            
            middleName: {
                validators: {
                    notEmpty: {
                        message: 'First name field is required'
                    }
                }
            },
            
            dob: {
            	validators: {
            		notEmpty: {
                        message: 'Date of Birth field is required'
                    },
                    date: {
                        format: 'YYYY/MM/DD',
                        message: 'Please enter a valid date'
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
            
            phoneNumber: {
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
            
            ssn: {
                validators: {
                    notEmpty: {
                        message: 'SSN field is required'
                    },
                    stringLength: {
                        min: 10,
                        message: 'SSN is not valid'
                    },
            		digits: {
            			message: 'Not a valid SSN number'
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
        }
    });
});