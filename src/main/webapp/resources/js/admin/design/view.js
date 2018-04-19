//Call the dataTables jqgrid

$(document).ready(function() {
	"use strict"; // Start of use strict
	
	$("#viewJqgrid").jqGrid({
		// data load 
		url:"/admin/design/view/load",
		customEditUrl:"/admin/design/view/save",
		colNames:["viewSeq","title","requestUri","filePath", "visible"],
        colModel:[	
					{name:"viewSeq", index:"view_seq", formatter:"integer", hidden:true,},
		       		
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
		$.ajax({
			method: "GET",
			url: "/admin/design/view/relation",
			dataType: "html",
			success:function(result){	
				// 서버로 부터 받은 메시지 코드 
				showModal("View-Section Relation",result,"<button type='button' id='relationSaveBtn' class='btn btn-sm btn-outline-secondary' >저장</button>","modal-lg");
				loadPopupViewJqgrid();
				loadPopupSectionJqgrid();
				
			},
			error:function() {
				var messageCode = "E000500";
				showModalMessage(messageCode);
			}
		});	
	});
	
	// Relation Popup Event
	// Reated Btn clicked in popup
	$("body").on("click","#relationSectionBtn",function(){
		
		var $sectionGrid = $("#sectionRelationJqgrid");
		
		$("#sectionRelationJqgridPager_left").show();
		
		$sectionGrid.jqGrid("showCol","priority");
		
		// current select row section load
		getDataSectionJqgrid("relation");
		
	});
	
	//  All Btn clicked in popup
	$("body").on("click","#allSectionBtn",function(){
		// all load 
		var $sectionGrid = $("#sectionRelationJqgrid");
		
		$("#sectionRelationJqgridPager_left").hide();
		$sectionGrid.jqGrid("hideCol","priority");
		getDataSectionJqgrid("all");
	});
	
	$("body").on("click","#relationSaveBtn",function(){
		// related save 
		var $this = $(this),
			viewGId = "viewRelationJqgrid" , 
			sectionGId = "sectionRelationJqgrid", 
			viewArr={}, 
			sectionArr ={};		
			
		viewArr = getSelectedJqGridData(viewGId) ;
		
		if(! isEmpty(viewArr) ){
			
			// validation check
			
			if(! $("#allSectionBtn").hasClass("active")){
				// Related Section
				// section update info 
				if(saveJqGrid(sectionGId)){
					
					sectionArr = getJqGridData(sectionGId);
					
					if(isEmpty(sectionArr)){
						// 변경 값이 없습니다.
						var messageCode = "E000304";
						showModalMessage(messageCode);
						return ;
					}
				}
			}else{
				// All Section 
				// view-section relateed 
				sectionArr["insert"] = getSelectedJqGridData(sectionGId);	
				
				if(isEmpty(sectionArr) ){
					// 선택 값이 없습니다.
					var messageCode = "E000600";
					showModalMessage(messageCode);
					return;
				}
			}
			
			// server call 
			$.ajax({
				method: "POST",
				url: "/admin/design/view/relation/save",
				dataType: "json",
				data: {"view" : JSON.stringify(viewArr), "section" : JSON.stringify(sectionArr)  },
				beforeSend:function(xhr){
					$this.removeClass("requestRunning").addClass("requestRunning");
				},
				success:function(result){	
					// 서버로 부터 받은 메시지 코드 
					var messageCode = result.code ; 
					if(result.value === "success"){
						
						showModalMessage(messageCode);
						
						clearDataJqGrid(viewGId);
						clearDataJqGrid(sectionGId);
						
						$("#"+viewGId).trigger("reloadGrid");
						$("#"+sectionGId).trigger("reloadGrid");
					}else{
						showModalMessage(messageCode);
					}
				},
				
				error:function() {
					var messageCode = "E000500";
					showModalMessage(messageCode);
				},
				complete: function() {
					$this.removeClass("requestRunning");
		        }
			});	
			
		}else{
			var messageCode="E000600";
			showModalMessage(messageCode);
			return;
		}
	});
});

function loadPopupViewJqgrid(){

	$("#viewRelationJqgrid").jqGrid({
		// data load 
		url:"/admin/design/view/load",
		colNames:["viewSeq","title","requestUri","filePath", "visible"],
        colModel:[	
					{name:"viewSeq", index:"view_seq", formatter:"integer", hidden:true,},
		       		{name:"title", index:"title",},
		       		{name:"requestUri", index:"request_uri",},
		       		{name:"filePath", index:"file_path",},
		       		{name:"visible", index:"visible",},
		       	], 	
		jsonReader: {
       	    id: "viewSeq",
       	},
    
       	multiselect:false, 			   // multi check box false
       	
       	pager: "#viewRelationJqgridPager",
       	sortname:  "view_seq",
        sortorder: "asc",
        
        onSelectRow: function(rowid, status, e){ 
        	if(! $("#allSectionBtn").hasClass("active")){
        		// 여기서 가져오기 
        		getDataSectionJqgrid("relation");
        	}
        },
    });
}

function loadPopupSectionJqgrid(){
		
	$("#sectionRelationJqgrid").jqGrid({
		// data load 

		colNames:["sectionSeq","title","priority","filePath","visible"],
        colModel:[	
					{name:"sectionSeq", index:"section_seq", formatter:"integer",hidden:true,},		       		
		       		{name:"title", index:"title",},
		       		{name:"priority", index:"section_priority", formatter:"integer", editable:true, editrules:{required:true,} },
		       		{name:"filePath", index:"file_path",},		       		
		       		{name:"visible", index:"visible",},
		       	], 	
		jsonReader: {
       	    id: "sectionSeq",
       	},
    	
       	pager: "#sectionRelationJqgridPager",
       	sortname:  "section_seq",
        sortorder: "asc",
  	
    })   
    .jqGrid("inlineNav",{restoreAfterSelect:false, add: false, editParams : editSettings })
	.jqGrid("navGrid",{add: false, edit: false, del: true, search: false, refresh: false},{},{},delSettings,{},{})
	.jqGrid("setGridParam",{
		 onSelectRow: function (rowid,status,e){
			if(e.target){
				var gID = $.jgrid.jqID(this.id); 
				$("#"+gID+"_ilsave").trigger('click');
			}
	    },
		ondblClickRow : function(rowid,iRow,iCol,e){
			var $this = $(this), gID = $.jgrid.jqID(this.id); 
			$this.find("#"+rowid).attr("aria-selected") == "false" ? 	$this.setSelection(rowid) : "" ;			
			$("#"+gID+"_iledit").trigger('click');
		},
	});
}

function getDataSectionJqgrid(type = "relation"){
	
	var $viewGrid = $("#viewRelationJqgrid"), 
		$sectionJqgrid = $("#sectionRelationJqgrid");
	
	clearDataJqGrid("sectionRelationJqgrid");
	
	$sectionJqgrid.jqGrid("clearGridData", true);
	
	// 
	if( type === "relation"){
		console.log("relation");
		var viewSeq = $viewGrid.jqGrid("getGridParam", "selrow");
		
		viewSeq = viewSeq ? viewSeq : 0 ;
		
		console.log(viewSeq);
		
		$sectionJqgrid.jqGrid("setGridParam",{ 
												"url":"/admin/design/view/load" , 
												"postData":{ "view_seq": viewSeq},
												"jsonReader" : {			
													root : function (obj) { return obj.data[0].sectionDtoList; }
												},
											});
		
	}else{ 
		console.log("all");
		$sectionJqgrid.jqGrid("setGridParam",{ 
												"url":"/admin/design/section/load",
												"postData":{},
												"jsonReader" : {			
													root : function (obj) { return obj.data; }
												},
											}); 
	}
		
	$sectionJqgrid.trigger('reloadGrid');
}





