<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML>
<html lang="ko">
	<head>
		<!-- Title,Meta, ETC ... -->
		<t:insertAttribute name="html_head"/>

		<!--  CSS -->
		<t:insertAttribute name="common_css"/>
		
		<!-- Coutom styles for this page  -->
		<link type="text/css" href="<t:getAsString name="include_css"/>?v=${cache}" rel="stylesheet">

	</head>
	<body>
		<!-- Header -->
		<t:insertAttribute name="header"/>
	
	
		<!-- Contents-->
		<t:insertAttribute name="body"/>
		<!-- Footer -->
		<t:insertAttribute name="footer"/>
	
		
		<!-- Side Menu -->
		<t:insertAttribute name="menu"/>
		
		<!-- JS  -->
		<t:insertAttribute name="common_js"/>
		
		<!-- Coutom styles for this page  -->
		<script type="text/javascript" src="<t:getAsString name="include_js"/>?v=${cache}"></script>
		
	</body>
</html>