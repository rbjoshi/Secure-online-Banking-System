$(document).ready(function() {
	$("#get-pii").on('click', function(e) {
		e.preventDefault();
		
		setTimeout(function() {
			$("#get-pii-form").hide();
			$(".loader").show();
			$("#get-pii-form").submit();
		}, 2000);
		
	});
});