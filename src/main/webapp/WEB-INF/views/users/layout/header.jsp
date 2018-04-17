<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<header id="header" class="fixed-top">
	<!-- Navigation -->
	<nav class="navbar navbar-expand-lg navbar-dark" id="mainNav">
		<div class="container">
			<a class="navbar-brand text-uppercase js-scroll-trigger " href="/"><strong>CodeSketch</strong></a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#navbarResponsive"
				aria-controls="navbarResponsive" aria-expanded="false"
				aria-label="Toggle navigation">
				Menu <i class="fa fa-bars"></i>
			</button>
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav text-uppercase ml-auto">
				<c:forEach var="menuList" items="${menuDtoList}" varStatus="status">
					<li class="nav-item">
						<a class="nav-link js-scroll-trigger <c:if test="${status.index == 0}"> active </c:if>"
							<c:if test="${menuList.linkUrl != null && menuList.linkUrl != ''}"> href="${menuList.linkUrl}" </c:if> 
						>
							${menuList.title}
						</a>
					</li>
				</c:forEach>
				</ul>
			</div>
		</div>
	</nav>
</header>
