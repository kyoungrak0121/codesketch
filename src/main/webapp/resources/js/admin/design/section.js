//Call the dataTables jqgrid

$(document).ready(function() {
	"use strict"; // Start of use strict
	
	$("#sectionJqgrid").jqGrid({
		// data load 
		url:"/admin/design/section/load",
		customEditUrl:"/admin/design/section/save",
		colNames:["sectionSeq","title","filePath","visible"],
        colModel:[	
					{name:"sectionSeq", index:"section_seq", formatter:"integer",hidden:true,},		       		
		       		{name:"title", index:"title", editable:true, editrules:{required:true,}},
		       		{name:"filePath", index:"file_path", editable:true,},		       		
		       		{name:"visible", index:"visible", editable:true, edittype:"checkbox", editoptions: {value:"y:n"} , editrules:{ required:true,} },
		       	], 	
		jsonReader: {
       	    id: "sectionSeq",
       	},
       	sortname:  "section_seq",
        sortorder: "asc",
    });
});
