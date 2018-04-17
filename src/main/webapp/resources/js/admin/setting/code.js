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
					{name:"type", index:"type", editable:true, editoptions:{size:3,}, editrules:{required:true},},
		       		{name:"seq", index:"seq", editable:true, editoptions:{size:4,},editrules:{required:true}},
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
    }) ;
});
