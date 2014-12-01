$( document ).ready(function() {
    
	var settings = {
		// Not one of the best ways to stop user from seeing source code via inspect element.
		// But, still as a course project we will be able to stop majority of the testers to go to console panel
		// NOT A BEST PRACTICE..!
		disableRightClick : function() {
			$(document).bind('contextmenu', function(event) {
				return false;
			})
		},
	};
	
	settings.disableRightClick();
});
