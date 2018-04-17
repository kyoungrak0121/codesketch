//Call the dataTables jqgrid

$(document).ready(function() {
	"use strict"; // Start of use strict
	
	
	$("#viewJqgrid").jqGrid({
		// data load 
		url:"/admin/design/view/load",
		customEditUrl:"/admin/design/view/save",
		colNames:["viewSeq","title","requestUri","filePath", "visible"],
        colModel:[	
					{name:"viewSeq", index:"view_seq", formatter:"integer",},
		       		
		       		{name:"title", index:"title", editable:true, editrules:{required:true,}},
		       		{name:"requestUri", index:"request_uri", editable:true,},
		       		
		       		{name:"filePath", index:"file_path", editable:true,},
		       		{name:"visible", index:"visible", editable:true, edittype:"checkbox", editoptions: {value:"y:n"} , editrules:{ required:true,} },
		       	], 	
		jsonReader: {
       	    id: "viewSeq",
       	},
       	sortname:  "view_seq",
        sortorder: "asc",
    });
	

	$("#relationBtn").click(function(){
		console.log("relationBtn click");
		
		showModal("View-Section Relation","contents","button","modal-lg");
		return ;
		
		$.ajax({
			method: "GET",
			url: "/admin/design/view/relation",
			dataType: "json",
			success:function(result){	
				// 서버로 부터 받은 메시지 코드 
				var messageCode = result.code ; 
				if(result.value === "success"){
					//(title,body,footer)
					showModal("View-Section Relation",result.data,"button","modal-lg");
					
				}else{
					var messageCode = "E000500";
					showModalMessage(messageCode);
				}
			},
			error:function() {
				var messageCode = "E000500";
				showModalMessage(messageCode);
			}
		});	
	});
});
