<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- content-wrapper -->
<div class="content-wrapper">

	<%@include file="../common/actionButton.jsp"%>
	
	<!-- container-fluid-->
	<div class="container-fluid">
	
		<%@include file="../common/path.jsp"%>

		<!-- DataTables Card-->
		<div class="card mb-6">
			<div class="card-header">
				View Management
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<!-- Jqgrid  -->
					<table id="sectionJqgrid"></table>
					<div id="jqgridPager"></div>
				</div>
			</div>
			<div class="card-footer small text-muted"></div>
		</div>	
		
	</div>
	<!-- /.container-fluid-->
	
</div>
<!-- /.content-wrapper-->
