$(document).ready(function() {
    $('#transfer-form').bootstrapValidator({
    	excluded: [':disabled', ':hidden', ':not(:visible)'],
        live: 'enabled',
        fields: {
        	amount: {
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
            
                    
            toAccountId : {
            	validators: {
                    notEmpty: {
                        message: 'Please enter recipient account'
                    },
                    stringLength: {
                        max: 10,
                        message: 'The account # can have atmost 10 digits'
                    },
                    regexp: {
                        regexp: /^[1-9]+[0-9]*$/,
                        message: 'Please enter a valid account number'
                    }
            	}
            }
        }
    });
    
});