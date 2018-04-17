"use strict"; // Start of use strict

function isEmpty(obj) {
	
    // null and undefined are "empty"
    if (obj == null && obj == undefined ) return true;

    // Assume if it has a length property with a non-zero value
    // that that property is correct.
    if (obj.length > 0)    return false;
    if (obj.length === 0)  return true;

    // If it isn't an object at this point
    // it is empty, but it can't be anything *but* empty
    // Is it empty?  Depends on your application.
    if (typeof obj !== "object") return true;

    // Otherwise, does it have any properties of its own?
    // Note that this doesn't handle
    // toString and valueOf enumeration bugs in IE < 9
    for (var key in obj) {
        if (obj.hasOwnProperty.call(obj, key)) return false;
    }

    return true;
}


String.prototype.hashCode = function() {
	var hash = 0, i, chr;
	if (this.length === 0) return hash;
	for (i = 0; i < this.length; i++) {
	    chr   = this.charCodeAt(i);
	    hash  = ((hash << 5) - hash) + chr;
	    hash |= 0; // Convert to 32bit integer
	}
	return hash;
};

function showModal(title,body,footer,size = undefined){
	
	var size = typeof size !== "undefined" ? size : "";  //modal-lg
	var zIndex = 1040 + (10 * $('.modal:visible').length);
	var id = "commonModal"+zIndex ;
	
	if($("#" + id).length == 0) {
		// it doesn't exist create and clone
		var $modal = $.parseHTML($("#commonModal")[0].outerHTML);
		$modal[0].id = id;
		$("body").append($modal);
	}

	$modal = $("#"+id);
	
	$modal.find(".modal-dialog").removeClass("modal-lg").addClass(size);
	$modal.find(".modal-title").html(title);
	$modal.find(".modal-body").html(body);
	$modal.find(".modal-footer").html(footer);
	
	$modal.css('z-index', zIndex);
	
	$("#commonModal"+zIndex).modal({
		  keyboard: false,
	});

	$('.modal-backdrop').not('.modal-stack').css('z-index', zIndex - 1).addClass('modal-stack');
}

function showModalMessage(messageCode, message = ""){
	showModal(code[messageCode].value, "["+code[messageCode].code+"]\n"+code[messageCode].message,"",undefined);
}


