<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- contents -->
<div class="container login">

	<div class="text-center mx-auto mt-5">
		<h1 class="text-muted">CodeSketch Administrators</h1>
	</div>

	<div class="card card-login mx-auto mt-5">
		<div class="card-header">Login</div>
		<div class="card-body">
			<form novalidate>
				<div class="form-group">
					<label for="Email">Email address</label>
					 <div class="controls">
						<input class="form-control" id="email" type="email" aria-describedby="emailHelp" placeholder="Enter email" required>
				    </div>
				</div>
				<div class="form-group">
					<label for="password">Password</label>
					<div class="controls">
						<input class="form-control" id="password" type="password" placeholder="Password" required>
				    </div>
				</div>
				<div class="form-group">
					<div class="form-check">
						<label class="form-check-label">
							<input class="form-check-input" type="checkbox"> Remember Id
						</label>
					</div>
				</div>
				<button class="btn btn-primary btn-block">Login</button>
			</form>
		
		</div>
	</div>
</div>
<!-- /.container-fluid-->
<!-- /.content-wrapper-->

