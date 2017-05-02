<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign in</title>

</head>
<div class="container"
	style="width: 527px; padding-top: 25px; min-height: 730px;">
	<form:form class="form-horizontal" action="validateLogin" method="post"
		role="form" autocomplete="off">
		<h2 class="form-signin-heading">Please sign in</h2>

		<c:if test="${not empty errorMessage}">
			<label class="control-label col-sm-12"></label>
			<div class="panel-group" >
				<div class="panel panel-danger" style="width:587px">
					<div class="panel-heading text-center" style="width:585px">${errorMessage}</div>

				</div>
			</div>
		</c:if>

		<div class="form-group">
			<label class="control-label col-sm-2" for="empId">Login&nbsp;ID</label>
			<div class="col-sm-10">
				<input type="text" id="empId" name="empId"
					placeholder="Enter Login ID" class="form-control" required
					autofocus></input>
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-sm-2" for="inputPassword">Password</label>
			<div class="col-sm-10">
				<input type="password" id="inputPassword" name="passwd"
					class="form-control" placeholder="Enter Password" required>
				</input>
			</div>
		</div>
		<!-- <div class="checkbox">
          <label>
            <input type="checkbox" value="remember-me"> Remember me
          </label>
        </div> -->
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-12">
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
					in</button>
			</div>
		</div>
	</form:form>
</div>