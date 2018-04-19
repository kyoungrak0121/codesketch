//Call the dataTables jqgrid

$(document).ready(function() {
	"use strict"; // Start of use strict
	
	$("#codeJqgrid").jqGrid({
		// data load 
		url:"/admin/setting/code/load",
		customEditUrl:"/admin/setting/code/save",
		colNames:["codeSeq","type","seq","value","message", "description","uesd",],
        colModel:[	
        			{name:"codeSeq", index:"code_seq",formatter:"integer",hidden:true,},
					{name:"type", index:"type", editable:true, editoptions:{size:3,minlength:3,maxlength:3, }, editrules:{"custom":true,"custom_func":validateType, required:true,},},
		       		{name:"seq", index:"seq", editable:true, editoptions:{size:4,minlength:4,maxlength:4,},editrules:{"custom":true,"custom_func":validateSeq, required:true,}},
		       		{name:"value", index:"value", editable:true, edittype:'select', editoptions:{value:{1:'success', 2:'fail'}},},
		       		{name:"message", index:"message", editable:true,},
		       		{name:"description", editable:true, index:"description",},
		       		{name:"uesd", index:"uesd", editable:true, edittype:'select', editoptions:{value:{1:'Y', 2:'N'}},},
		       	], 	
		jsonReader: {
       	    id: "codeSeq",
       	},
       	sortname:  "type,seq",
        sortorder: "asc",
    });
	
});

function validateType (value,colname){
	var minLength = 3 , maxLength = 3 ; 
	return value.length <= minLength && value.length >= maxLength ? [true,""] : [false,"seq: Field is MinLength:"+minLength+", MaxLength:"+minLength+" "] ;
}; 

function validateSeq (value,colname){
	var minLength = 4 , maxLength = 4  ; 
	return value.length <= minLength && value.length >= maxLength ? [true,""] : [false,"seq: Field is MinLength:"+minLength+", MaxLength:"+minLength+" "] ;
}; 