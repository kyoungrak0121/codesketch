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
	$("body").on("click","#relatedSectionBtn",function(){
		
		var $sectionGrid = $("#sectionRelationJqgrid"),
			selRowId = $("#viewRelationJqgrid").jqGrid("getGridParam", "selrow");
		
		$("#sectionRelationJqgridPager_left").show();
		
		$sectionGrid.jqGrid("showCol","priority");
		// current select row section load
		getDataSectionJqgrid(selRowId);
		
	});
	
	//  All Btn clicked in popup
	$("body").on("click","#allSectionBtn",function(){
		// all load 
		var $sectionGrid = $("#sectionRelationJqgrid");
		
		$("#sectionRelationJqgridPager_left").hide();
		$sectionGrid.jqGrid("hideCol","priority");
		getDataSectionJqgrid(0);
	});
	
	$("body").on("click","#relationSaveBtn",function(){
		// related save 
		var viewGId = "viewRelationJqgrid" , sectionGID = "sectionRelationJqgrid", 
			viewArr, sectionArr ;		
			
		viewArr = getSelectedJqGridData(viewGId) ;
			 
		if( viewArr.length > 0){
		
			if(! $("#allSectionBtn").hasClass("active")){
				// Related Section
				// section update info 
				
				if(saveJqGrid(sectionGID)){
					sectionArr = getJqGridData(sectionGID);
					
					console.log(viewArr);
					console.log(sectionArr);
					
					if(! isEmpty(sectionArr)){
						
					}else{
						// 변경 값이 없습니다.
						var messageCode = "E000304";
						showModalMessage(messageCode);
					}
				}
			}else{
				// All Section 
				// view-section relateed 
				
				sectionArr = getSelectedJqGridData(sectionGID);	
				
				if(sectionArr.length > 0){
					
					console.log(viewArr);
					console.log(sectionArr);
					
					resetSelectionJqGrid(viewGId);
					resetSelectionJqGrid(sectionGID);
					
				}else{
					var messageCode = "E000600";
					showModalMessage(messageCode);
					return;
				}
			}
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
        		getDataSectionJqgrid(rowid);
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
			$this.find("#"+rowid).attr("aria-selected") == "false"	? 	$this.setSelection(rowid) : "" ;			
			$("#"+gID+"_iledit").trigger('click');
		},
	});
}

function getDataSectionJqgrid(rowid){
	
	var $viewGrid = $("#viewRelationJqgrid"), 
		$sectionJqgrid = $("#sectionRelationJqgrid");
	
	clearDataJqGrid("sectionRelationJqgrid");
	
	$sectionJqgrid.jqGrid("clearGridData", true);
	
	if(rowid !== null){
		
		var view_seq = $viewGrid.jqGrid ("getCell", rowid, "viewSeq");
		
		view_seq = view_seq === false ? 0 : view_seq ;
		
		$sectionJqgrid.jqGrid("setGridParam",{ "url":"/admin/design/section/load" , "postData":{ "view_seq": view_seq}, }); 
	}else{
		$sectionJqgrid.jqGrid("setGridParam",{ "url":"" ,}); 
	}
		
	$sectionJqgrid.trigger('reloadGrid');
}





