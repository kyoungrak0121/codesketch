<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


	<!-- contents -->

	<c:forEach var="section" items="${viewDto.sectionDtoList}">

		${selection.filePath}
		<c:import url="${section.filePath}" />	
	</c:forEach>	
	
	
	
	
	
	