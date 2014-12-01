$(function(){
	
	$("#virtualKeyboardDiv").hide();
	$("#virtualKeyboardLink").click(function(){
		var desc = $("#virtualKeyboardLink").html();
		if(desc === "Show Virtual Keyboard") {
			$("#virtualKeyboardDiv").slideDown(100);
			$("#virtualKeyboardLink").html("Hide Virtual Keyboard");
		} else {
			$("#virtualKeyboardDiv").hide();
			$("#virtualKeyboardLink").html("Show Virtual Keyboard");
		}		
	});
    var $password = ($('#password').length !== 0) ? $('#password') : $("#username");
    shift = false;
    capslock = false;
     
    $('#keyboard li').click(function(){
        var $this = $(this),
        character = $this.html(); // If it's a lowercase letter, nothing happens to this variable
         
        // Shift keys
        if ($this.hasClass('left-shift') || $this.hasClass('right-shift')) {
            $('.letter').toggleClass('uppercase');
            $('.symbol span').toggle();
             
            shift = (shift === true) ? false : true;
            capslock = false;
            return false;
        }
         
        // Caps lock
        if ($this.hasClass('capslock')) {
            $('.letter').toggleClass('uppercase');
            capslock = true;
            return false;
        }
         
        // Delete
        if ($this.hasClass('delete')) {
            var val = $password.val();
             
            $password.val(val.substr(0, val.length - 1));
            return false;
        }
         
        // Special characters
        if ($this.hasClass('symbol')) {
        	character = $('span:visible', $this).html();
        }
        if ($this.hasClass('space')) {
        	character = ' ';
        }
        if ($this.hasClass('tab')) {
        	character = "\t";
        }
        if ($this.hasClass('return')) {
        	character = "\n";
        }
         
        // Uppercase letter
        if ($this.hasClass('uppercase')) {
        	character = character.toUpperCase();
        }
         
        // Remove shift once a key is clicked.
        if (shift === true) {
            $('.symbol span').toggle();
            if (capslock === false) $('.letter').toggleClass('uppercase');
             
            shift = false;
        }
         
        // Add the character
        $password.val($password.val() + character);
    });
});