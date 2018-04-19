
/***********************************************************************************/
/* JqGrid 공통 함수 정의 시작 */
/***********************************************************************************/

// modal common position setting 
jQuery.extend(jQuery.jgrid, {
	
    showModal : function(h) {
    	var documentElement = document.documentElement, w = window, left = 1024, top = 768,
    	targetModal = $("#" + h.w[0].id), offsetGbox = targetModal.offset();

		if (w.innerWidth !== undefined) {
			left = w.innerWidth;
			top = w.innerHeight;
		} else if (documentElement != null && documentElement.clientWidth !== undefined && documentElement.clientWidth !== 0) {
			left = documentElement.clientWidth;
			top = documentElement.clientHeight;
		}
		left = left / 2 - parseInt(targetModal.outerWidth(), 10) / 2 - offsetGbox.left +
				((w.pageXOffset !== undefined) ? w.pageXOffset : (documentElement || document.body.parentNode || document.body).scrollLeft);
		top = top / 2 - 25 - offsetGbox.top +
				((w.pageYOffset !== undefined) ? w.pageYOffset : (documentElement || document.body.parentNode || document.body).scrollTop);
		
		targetModal.css("top", top + "px");
		targetModal.css("left", left+ "px");
	 	
		// show modal
		h.w.show();	 
    },
    /*
    closeModal : function(h) {
        h.w.hide("slow").attr("aria-hidden", "true");
        if(h.o) {h.o.remove();}
    }
    */
});

jQuery.extend(jQuery.jgrid.defaults, {
	/* http://www.trirand.com/jqgridwiki/doku.php?id=wiki:events */
	/* http://www.trirand.com/blog/jqgrid/jqgrid.html */
	
	// Designlayout
	guiStyle: "bootstrap4",
    iconSet: "fontAwesome",
    autowidth: true,
    height: 'auto', 
    
	// data
    multiselect:true, 			   // check box
    idPrefix:"",
    headertitles:true,
    rownumbers: true,              // 행번호 표시
    
    // sorting 
    multiSort: true,
    
    //paging
    pager: "#jqgridPager",
    rowNum: 20,					// grid 행의 갯수,-1하면 무한으로 보여줌
    rowList: [20,50,100,200],   //  한 화면에서 볼 수 있는 row의 수를 조절 가능
	
    // dataLoad
    //loadonce: true,
    navOptions: { reloadGridOptions: { fromServer: true } },
    mtype : 'GET',
    
    datatype : "json",	
	jsonReader : {			
		root : function (obj) { return obj.data; },
        page : function (obj) { return obj.pagingDto.page; },
        total : function (obj) { return obj.pagingDto.totalPage; },
        records : function (obj) { return obj.pagingDto.records; },
    	repeatitems : false,
	},
    
	// upload setting 
	keyName:false,
	
	// parameter name
	prmNames: {
        id: "_rowid", page: "page", rows: "pageSize", 
        oper: "oper", sort: "sidx", order: "sord", 
    },
    
    // custom value for editor 
    originData:[], insertData:[], updateData:[], deleteData:[], 
    
	beforeRequest : function() {
		var $this = $(this);
		$this.parents("div.ui-jqgrid-bdiv").find("div.no-data").empty().remove();
		if (this.p) {
			if (this.p.datatype == "json") {
				if (this.p.url == "") {
					//$(this).noData();
					return false;
				}
			}
		}
	},
	beforeProcessing : function(data, status, xhr){
		
		var $this = $(this), gID = $.jgrid.jqID(this.id), p = $this.jqGrid("getGridParam") ;
		
		$this.jqGrid("hideCol","cb");
		
		if(!isEmpty(p.customEditUrl)){
					
			onBeforeSaveLocal = function(rowid,options ){
				var $this = $(this), p = $this.jqGrid("getGridParam"),
				 	rowData = $(this).getRowData(rowid), rowHash = JSON.stringify(rowData).hashCode();
			
				if( p.originData[rowid] === undefined && p.originData[rowid] !== rowHash){
					p.originData[rowid] = rowHash;
				}
				
				return false;
			},
			onAfterSaveLocal = function (rowid,options) {
				var $this = $(this), p = $this.jqGrid("getGridParam"),
					rowData = $(this).getRowData(rowid), rowHash = JSON.stringify(rowData).hashCode();
			
				if(p.originData[rowid] !== undefined && p.originData[rowid] !== rowHash ){
		    		type = rowid.indexOf("jqg") === 0 ?  "insert" : "update" ;	
		    		if(type === "insert"){ p.insertData[""+rowid+""] = rowData; }
		    		else{ p.updateData[""+rowid.toString()+""] = rowData; }
				}
		
				return false; 
		    },
		    
		    initSetting = {
		    		keys:true,
	    			oneditfunc : onBeforeSaveLocal,
		    		aftersavefunc : onAfterSaveLocal,
		    },
		    
		    addSettings = initSetting ,
		    editSettings = initSetting ,
		    delSettings = {
		
	            // because I use "local" data I don't want to send the changes to the server
		        // so I use "processing:true" setting and delete the row manually in onclickSubmit
		        onclickSubmit: function (options, rowidArr) {
		        	var $this = $(this), gID = $.jgrid.jqID(this.id), p = $this.jqGrid("getGridParam"), newPage = p.page;
		        	
		        	// reset the value of processing option to true to
                    // skip the ajax request to "clientArray".
                    options.processing = true;
                    
                    // custom       
                    var  rowidSplit = rowidArr.split(',');                    
                    
                    for( var i in rowidSplit) {                    	
                    	rowid = rowidSplit[i];

                    	var rowData = $this.getRowData(rowid);
                    	var id = p.jsonReader.id; 
                    	
                    	if (p.insertData.hasOwnProperty(rowid)){ delete p.insertData[rowid] ; }
                		if (p.updateData.hasOwnProperty(rowid)){ delete p.updateData[rowid] ; }
                		
                    	// 실제 db에 저장된 data인 경우
                    	if(rowData[id] > 0){
                    		p.deleteData[rowid] = rowData;
                    	}
                    	
                        // delete the row
                        $this.jqGrid("delRowData", rowid);
                        if (p.treeGrid) {
                            $this.jqGrid("delTreeNode", rowid);
                        } else {
                            $this.jqGrid("delRowData", rowid);
                        }  
                    }
                    
                    // del modal hide
                    $.jgrid.hideModal("#delmod" + gID, {
                        gb: "#gbox_" + gID,
                        jqm: options.jqModal,
                        onClose: options.onClose
                    });
                    
                    // set paging
                    if (p.lastpage > 1) {// on the multipage grid reload the grid
                        if (p.reccount === 0 && newPage === p.lastpage) {
                            // if after deliting there are no rows on the current page
                            // which is the last page of the grid
                            newPage--; // go to the previous page
                        }
                        // reload grid to make the row from the next page visable.
                        $this.trigger("reloadGrid", [{page: newPage}]);
                    }
		            return true;
		        },
		        processing: true 
		    };
		    
	    	$this.jqGrid("filterToolbar")
	    		 
			 	 //.jqGrid('inlineNav',pagerid, parameters);
			 	 .jqGrid("inlineNav",{restoreAfterSelect:false, addParams:{addRowParams : addSettings}, editParams:editSettings})
			 	 
			 	 //.jqGrid('navGrid','#gridpager',{parameters},prmEdit, prmAdd, prmDel, prmSearch, prmView);
			 	 .jqGrid("navGrid",{add: false, edit: false, del: true, search: false, refresh: false},{},{},delSettings,{},{})
			 	 
			 	 //setMethod
			 	 .jqGrid("setGridParam",{
						onSelectRow: function (rowid,status,e){
							if(e.target){
								$("#"+gID+"_ilsave").trigger('click');
							}
			            },
						ondblClickRow : function(rowid,iRow,iCol,e){
							$this.find("#"+rowid).attr("aria-selected") == "false"	? 	$this.setSelection(rowid) : "" ;			
							$("#"+gID+"_iledit").trigger('click');
						},
			 	 });
	
	    	
			// inlinenav btn custom
			//add btn
			$("#commonRegisterBtn").on("click",function(e){
				$('#'+gID+"_iladd").trigger('click');
			});
			
			//delete btn
			$("#commonDeleteBtn").on("click",function(e){
				$('#del_'+gID).trigger('click');
			});
			
			//save btn
			$("#commonSaveBtn").on("click",function(e){
				e.preventDefault();
				
				if(saveJqGrid(gID)){
					var data = getJqGridData(gID);
						
					if(!isEmpty(data)){
						
						if($this.hasClass("requestRunning")){ return false;}
						 
						data = JSON.stringify(data);

						$.ajax({
							method: "POST",
							url: p.customEditUrl,
							dataType: "json",
							data: {"data" : data},
							beforeSend:function(xhr){
								$this.removeClass("requestRunning").addClass("requestRunning");
							},
							success:function(result){	
								// 서버로 부터 받은 메시지 코드 
								var messageCode = result.code ; 
								if(result.value === "success"){
									clearDataJqGrid(gID);
									showModalMessage(messageCode);
									$("#menuJqgrid").trigger("reloadGrid");	
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
						// 변경 값이 없습니다.
						var messageCode = "E000304";
						showModalMessage(messageCode);
					}	
				}
			});
		}
	},
	
	loadError : function(xhr, status, error) {
		var $this = $(this);
		$this.parents("div.ui-jqgrid-bdiv").find("div.no-data").empty().remove();
		$this.parents("div.ui-jqgrid-bdiv").append('<div class="no-data">Load Error</div>');
	},
});

function saveJqGrid(gID){
	$("#"+gID+"_ilsave").trigger('click');
	
	var p = $("#"+gID)[0].p, savedRow = p.savedRow, len = savedRow.length; 
	
	return len > 0 ? false : true ;  
}

// update , delete , insert 
function getChangeJqGridData(obj){
	tempArr=[];
	
	for(var k in obj){
		tempArr.push(obj[k]);
	}
	return tempArr; 
}

function getJqGridData(gID){
	var p = $("#"+gID)[0].p, uploadData = {};
	
	uploadData["delete"] = getChangeJqGridData(p.deleteData);
	uploadData["update"] = getChangeJqGridData(p.updateData);
	uploadData["insert"] = getChangeJqGridData(p.insertData); 
	
	for(var key in uploadData) {
	    if(isEmpty(uploadData[key])){
	    	delete uploadData[key];
	    }
	}
	
	return uploadData; 
};


function getSelectedJqGridData(gID){
	var $this = $("#"+gID), p = $("#"+gID)[0].p , tempArr=[], jqGridRowId=[] ;

	if(p.multiselect === true){ // ture 
		jqGridRowId = $this.jqGrid("getGridParam", "selarrrow");
	}else{ // false 
		jqGridRowId.push($this.jqGrid("getGridParam", "selrow"));	
	}
	
	jqGridRowId.forEach( function( element, index ){
		if( element !== null && element !== undefined ){ tempArr.push($this.getRowData(element)); }
	});
	
	return tempArr; 
}

function resetSelectionJqGrid(gID){
	var $this = $("#"+gID) ;
	
	$this.jqGrid("resetSelection");
	
	return true;
}

function clearDataJqGrid(gID){
	var p = $("#"+gID)[0].p ;
	
	p.insertData = {};
	p.updateData = {};
	p.deleteData = {};
	
	return true;
}


/***********************************************************************************/
/* JqGrid 공통 함수 정의 끝  */
/***********************************************************************************/

/***********************************************************************************/
/* common 공통 함수 정의 시작  */
/***********************************************************************************/

function isEmpty(obj) {
	
    // null and undefined are "empty"
    if (obj === null || obj === undefined ) return true;

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


/***********************************************************************************/
/* common 공통 함수 정의 끝 */
/***********************************************************************************/

(function($) {
	"use strict"; // Start of use strict
	
  // Configure tooltips for collapsed side navigation
  $('.navbar-sidenav [data-toggle="tooltip"]').tooltip({
    template: '<div class="tooltip navbar-sidenav-tooltip" role="tooltip"><div class="arrow"></div><div class="tooltip-inner"></div></div>'
  })
  // Toggle the side navigation
  $("#sidenavToggler").click(function(e) {
    e.preventDefault();
    $("body").toggleClass("sidenav-toggled");
    $(".navbar-sidenav .nav-link-collapse").addClass("collapsed");
    $(".navbar-sidenav .sidenav-second-level, .navbar-sidenav .sidenav-third-level").removeClass("show");
  });
  // Force the toggled class to be removed when a collapsible nav link is clicked
  $(".navbar-sidenav .nav-link-collapse").click(function(e) {
    e.preventDefault();
    $("body").removeClass("sidenav-toggled");
  });
  // Prevent the content wrapper from scrolling when the fixed side navigation hovered over
  $('body.fixed-nav .navbar-sidenav, body.fixed-nav .sidenav-toggler, body.fixed-nav .navbar-collapse').on('mousewheel DOMMouseScroll', function(e) {
    var e0 = e.originalEvent,
      delta = e0.wheelDelta || -e0.detail;
    this.scrollTop += (delta < 0 ? 1 : -1) * 30;
    e.preventDefault();
  });
  // Scroll to top button appear
  $(document).scroll(function() {
    var scrollDistance = $(this).scrollTop();
    if (scrollDistance > 100) {
      $('.scroll-to-top').fadeIn();
    } else {
      $('.scroll-to-top').fadeOut();
    }
  });
  // Configure tooltips globally
  $('[data-toggle="tooltip"]').tooltip()
  // Smooth scrolling using jQuery easing
  $(document).on('click', 'a.scroll-to-top', function(event) {
    var $anchor = $(this);
    $('html, body').stop().animate({
      scrollTop: ($($anchor.attr('href')).offset().top)
    }, 1000, 'easeInOutExpo');
    event.preventDefault();
  });
 
})(jQuery); // End of use strict
