<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- contents -->
<div class="content-wrapper">

	<div class="container-fluid">
		<div class="btn-toolbar justify-content-end mt-3 mb-3" role="toolbar"
			aria-label="Toolbar with button groups">
			<div class="btn-group" role="group" aria-label="Basic example">
				<a id="commonRegisterBtn" class="btn btn-sm btn-outline-primary" href="#">등록</a> 
				<a id="commonSaveBtn" class="btn btn-sm btn-outline-secondary" href="#">저장</a> <!-- insert & update --> 
				<a id="commonDeleteBtn" class="btn btn-sm btn-outline-danger" href="#">삭제</a>
			</div>
		</div>
	</div>

	<div class="container-fluid">
		<!-- Breadcrumbs-->
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
			<li class="breadcrumb-item active">Tables</li>
		</ol>

		<!-- DataTables Card-->
		<div class="card mb-3">
			<div class="card-header">
				<i class="fa fa-table"></i> Menu Management
			</div>
			<div class="card-body">
				<div class="table-responsive">
					<!-- Jqgrid  -->
					<table id="menuJqgrid"></table>
					<div id="jqgridPager"></div>
				</div>
			</div>
			<div class="card-footer small text-muted"></div>
		</div>
	</div>
	
</div>
<!-- /.container-fluid-->
<!-- /.content-wrapper-->

