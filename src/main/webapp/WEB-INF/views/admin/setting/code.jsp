<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- contents -->
<div class="content-wrapper">

	<%@include file="../common/actionButton.jsp"%>

	<div class="container-fluid">
	
		<%@include file="../common/path.jsp"%>
	
		<!-- DataTables Card-->
		<div class="card mb-3">
			<div class="card-header">
				Code Management
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<!-- Jqgrid  -->
					<table id="codeJqgrid"></table>
					<div id="jqgridPager"></div>
				</div>
			</div>
			<div class="card-footer small text-muted"></div>
		</div>
	</div>
	<!-- /.container-fluid-->
</div>
<!-- /.content-wrapper-->

