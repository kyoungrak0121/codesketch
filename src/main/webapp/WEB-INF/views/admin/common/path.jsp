<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- PATH -->
<ol class="breadcrumb">
	<c:forEach var="path" items="${path}" varStatus="status">
		<li class="breadcrumb-item text-uppercase <c:if test="${status.last}"> active </c:if> "> ${path} </li>
	</c:forEach>
</ol>

