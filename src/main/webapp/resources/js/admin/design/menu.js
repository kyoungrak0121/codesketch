//Call the dataTables jqgrid

$(document).ready(function() {
	"use strict"; // Start of use strict
	
	$("#menuJqgrid").jqGrid({
		// data load 
		url:"/admin/design/menu/load",
		customEditUrl:"/admin/design/menu/save",
		colNames:["menuSeq","parentMenuSeq","standard","depth", "priority","title","linkUrl","visible"],
        colModel:[	
					{name:"menuSeq", index:"menu_seq", formatter:"integer",},
		       		{name:"parentMenuSeq", index:"parent_menu_seq", formatter:"integer", editable:true, editrules:{number:true} },
		       		{name:"standard", index:"standard", formatter:"integer", editable:true, editrules:{number:true, required:true}},
		       		{name:"depth", index:"depth", formatter:"integer", editable:true, editrules:{number:true,}},
		       		{name:"priority", index:"priority", formatter:"integer", editable:true, editrules:{number:true}},
		       		{name:"title", index:"title", editable:true, editrules:{required:true,}},
		       		{name:"linkUrl", index:"link_url", editable:true,},
		       		{name:"visible", index:"visible", editable:true, edittype:"checkbox", editoptions: {value:"y:n"} , editrules:{ required:true,} },
		       	], 	
		jsonReader: {
       	    id: "menuSeq",
       	},
       	
       	sortname:  "standard,depth,priority",
        sortorder: "asc",
    }) ;

});
