<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!-- Acttion Button -->
<div class="container-fluid">
	<div class="btn-toolbar justify-content-end mt-3 mb-3" role="toolbar"
		aria-label="Toolbar with button groups">
		
		<!-- custom button -->
		<div class="btn-group mr-3" role="group" aria-label="Btn Group">
			<c:forTokens items="${param.buttoms}" delims="," var="letter">
				<c:out value="${letter}" escapeXml="false"></c:out>
			</c:forTokens>
		</div>
		
		
		<!-- common button group -->
		<div class="btn-group" role="group" aria-label="Btn Group">
			<button type="button" id="commonRegisterBtn" class="btn btn-sm btn-outline-primary" >등록</button> 
			<button type="button" id="commonSaveBtn" class="btn btn-sm btn-outline-secondary" >저장</button> <!-- insert & update --> 
			<button type="button" id="commonDeleteBtn" class="btn btn-sm btn-outline-danger" >삭제</button>		
		</div>
	
	</div>
</div>

