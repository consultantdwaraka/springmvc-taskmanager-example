<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Profile</title>
<style type="text/css">
.row {
	margin-left: 10px;
	margin-right: 0px;
}
.text-danger {
 margin-left:25px;
 color: #d94442;
}
</style>
<script>
	$(document).ready(function() {
		$("#newProfileForm").submit(function() {
			$("*").removeAttr("disabled");
		});
	});
</script>

</head>

<body>
	<form:form method="post" commandName="newEmpForm" action="createUser"
		role="form" autocomplete="off" id="newEmpForm"
		style="min-height: 650px">
		<spring:url value="${not empty empId}"
			var="frole"></spring:url>
		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="empId">Employee ID:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="empId" cssClass="form-control" readonly="${frole}"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="empId" cssClass="text-danger"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="empName">Employee Name:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="empName" cssClass="form-control" ></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="empName" cssClass="text-danger"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="empPwd">Password:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="empPwd" cssClass="form-control"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="empPwd" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="empRole">Employee Role:</form:label>
			</div>
			<div class="col-xs-4">
				<form:select path="empRole" cssClass="form-control">
					<form:option label="---Assign Role---" value=""></form:option>
					<form:options items="${userRole}"></form:options>
				</form:select>
			</div>
			<div class="col-xs-5">
				<form:errors path="empRole" cssClass="text-danger"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="empRole">Employee Status:</form:label>
			</div>
			<div class="col-xs-4">
				<form:select path="empStatus" cssClass="form-control">
					<form:options items="${userStatus}"></form:options>
				</form:select>
			</div>
			<div class="col-xs-5">
				<form:errors path="empRole" cssClass="text-danger"></form:errors>
			</div>
		</div>
			<div class="form-group row">
				<div class="col-xs-2">
					<form:label path="contactNo">Employee Contact No:</form:label>
				</div>
				<div class="col-xs-4">
					<form:input path="contactNo" cssClass="form-control"></form:input>
				</div>
				<div class="col-xs-5">
					<form:errors path="contactNo" cssClass="text-danger"></form:errors>
				</div>
			</div>
		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="contactAddress">Employee Contact Address:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="contactAddress" cssClass="form-control"
					id="contactAddress"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="contactAddress" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2"></div>
			<div class="row">
				<button type="submit" value="Create Profile" class="btn btn-primary"
					style="margin-left: 15px">
					<c:choose>
						<c:when test="${not empty empId}">
							Save Employe
						</c:when>
						<c:otherwise>
							Create Employe
						</c:otherwise>
					</c:choose>
				</button>
				<button type="button"
					onclick="document.getElementById('newEmpForm').reset();"
					value="Reset" class="btn btn-warning" style="margin-left: 15px">Reset</button>
			</div>
			<div class="col-xs-2"></div>
		</div>

	</form:form>
</body>
</html>