<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- content-wrapper -->
<div class="content">
	
	<!-- container-fluid-->
	<div class="container-fluid">
		<div class="row justify-content-end mb-3">
			<div class="col-6">
				<ul class="nav nav-tabs" id="myTab" role="tablist">
					<li class="nav-item">
						<a class="nav-link active" id="relatedSectionBtn" data-toggle="tab" href="#" role="tab">Related</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" id="allSectionBtn" data-toggle="tab" href="#" role="tab" >All</a>
					</li>
				</ul>
			</div>
		</div>
		
		<div class="row">
			<div class="col-6">
				<!-- DataTables Card-->
				<div class="card mb-6">
					<div class="card-header">
						View Area
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<!-- Jqgrid  -->
							<table id="viewRelationJqgrid"></table>
							<div id="viewRelationJqgridPager"></div>
						</div>
					</div>
				</div>	
			</div>
			
			<div class="col-6">
				<!-- DataTables Card-->
				<div class="card mb-6">
					<div class="card-header">
						Section Area
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<!-- Jqgrid  -->
							<table id="sectionRelationJqgrid"></table>
							<div id="sectionRelationJqgridPager"></div>
						</div>
					</div>
				</div>	
			</div>
		</div>
	</div>
	<!-- /.container-fluid-->
	

</div>
<!-- /.content-wrapper-->


