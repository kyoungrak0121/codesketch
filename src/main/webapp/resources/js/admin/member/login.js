//Call the dataTables jqgrid

$(document).ready(function() {
	"use strict"; // Start of use strict
	
	// set jqBootstrapValidation working 
	$("input,select,textarea")
		.not("[type=submit]")
		.jqBootstrapValidation({
		
			filter: function () {
				return $(this).is(":visible");
	  			},
	  		}  	
	);
	
});
