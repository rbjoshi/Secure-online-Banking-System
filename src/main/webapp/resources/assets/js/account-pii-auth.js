$(document).ready(function() {
    $('#comment-form').bootstrapValidator({
    	excluded: [':disabled', ':hidden', ':not(:visible)'],
        live: 'enabled',
        fields: {
        	comments: {
                validators: {
                    notEmpty: {
                        message: 'A valid description is required required'
                    },
                    stringLength: {
                    	max: 250,
                    	message: 'Commecnts can not be greater than 250 characters. Please reduce!'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9,= ]+$/,
                        message: 'Not a valid message format. Only characters, digits, "," and "=" is allowed. Eg first name = jacob, last name = chain'
                    }
                }
    
            },
        }
    });
});