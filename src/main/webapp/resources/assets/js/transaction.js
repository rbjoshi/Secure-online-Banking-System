$(document).ready(function() {
    $('#transaction-form').bootstrapValidator({
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
            
            balance: {
                validators: {
                    notEmpty: {
                        message: 'Amount is required'
                    },
                    regexp: {
                        regexp: /^[1-9]{1}[0-9]+(.\d{2})?$/,
                        message: 'Amount should be in the write format XX.XX'
                    }
                }
            },
            
            transactionTypeName : {
            	validators: {
                    notEmpty: {
                        message: 'Please select a transaction type'
                    },
            	}
            }
        }
    });
    
});