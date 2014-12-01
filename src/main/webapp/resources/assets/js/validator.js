$(document).ready(function() {
	$(".signup-submit").click(function(){
		var flag = false;
		$(".numeric").each(function() {
			  var number = $(this).text();
			  var pattern = /^\d{10}$/;
		      if (!pattern.test(number)) {
		    	  alert("Phone number and SSN should be 10 digit numeric value.")
		           return false;
		      }
		});
		return true;
	});
	
	var counter = 0;
	
	$(".balance").keyup(function(e) {
		counter++;
		var val = $(this).val();
		var ans;
		var len = val.length - 1;
		if(counter == 1) {
			ans=0.00;
		}
		else{
			ans=val.substr(1,);
		}
		var last = val.substr(len,1);
		val = 10*ans + (last*(0.01));
		$(this).val(val);
	});
});