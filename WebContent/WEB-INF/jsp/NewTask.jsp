<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Task</title>
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
		$("#newTaskForm").submit(function() {
			$("*").removeAttr("disabled");
		});
	});
</script>

</head>

<body>
	<form:form method="post" commandName="newTaskForm" action="createTask"
		role="form" autocomplete="off" id="newTaskForm"
		style="min-height: 650px">
		<spring:url value="${(loginUser.empRole !='Boss' && loginUser.empRole !='Manager' && not empty taskId)}"
			var="frole"></spring:url>
		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="taskId">Task ID:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="taskId" cssClass="form-control" readonly="true"></form:input>
			</div>
			<div class="col-xs-2">
				<form:errors path="taskId"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="taskName">Task Name:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="taskName" cssClass="form-control"
					readonly="${frole}"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="taskName" cssClass="text-danger"></form:errors>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="taskDescription">Task Description:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="taskDescription" cssClass="form-control"
					readonly="${frole}"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="taskDescription" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="taskType">Nature Of Task:</form:label>
			</div>
			<div class="col-xs-4">
				<form:select path="taskType" cssClass="form-control"
					disabled="${frole}">
					<form:option label="---Select Nature Of Work---" value=""></form:option>
					<form:options items="${workList}"></form:options>
				</form:select>
			</div>
			<div class="col-xs-5">
				<form:errors path="taskType" cssClass="text-danger"></form:errors>
			</div>
		</div>
		<c:if test="${(loginUser.empRole=='Manager' || loginUser.empRole=='Boss')}">
			<div class="form-group row">
				<div class="col-xs-2">
					<form:label path="taskEstimatedCost">Task Actual Cost:</form:label>
				</div>
				<div class="col-xs-4">
					<form:input path="taskEstimatedCost" cssClass="form-control"></form:input>
				</div>
				<div class="col-xs-5">
					<form:errors path="taskEstimatedCost" cssClass="text-danger"></form:errors>
				</div>
			</div>
		</c:if>
		<c:if test="${(loginUser.empRole=='Manager' || loginUser.empRole=='Boss')}">
			<div class="form-group row">
				<div class="col-xs-2">
					<form:label path="taskCost">Task Cleared Balance:</form:label>
				</div>
				<div class="col-xs-4">
					<form:input path="taskCost" cssClass="form-control"></form:input>
				</div>
				<div class="col-xs-5">
					<form:errors path="taskCost" cssClass="text-danger"></form:errors>
				</div>
			</div>
		</c:if>
		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="taskStartDate">Task Start Date:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="taskStartDate" cssClass="form-control"
					id="startDate" disabled="${frole}"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="taskStartDate" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="taskCompletionDate">Task End Date:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="taskCompletionDate" cssClass="form-control"
					id="endDate" disabled="${frole}"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="taskCompletionDate" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="assignedTo">Task Assign To:</form:label>
			</div>
			<div class="col-xs-4">
				<form:select path="assignedTo" cssClass="form-control"
					disabled="${frole}">
					<form:option label="---Choose employee to assign task---" value=""></form:option>
					<form:options items="${empList}"></form:options>
				</form:select>
			</div>
			<div class="col-xs-5">
				<form:errors path="assignedTo" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="taskStatus">Task Status:</form:label>
			</div>
			<div class="col-xs-4">
				<c:set var="taskIdExist" value="${empty taskId}"></c:set>
				<form:select path="taskStatus" cssClass="form-control"
					disabled="${taskIdExist}">
					<%-- <form:option label="---Select Task Status---" value="NONE"></form:option> --%>
					<form:options items="${taskStatus}"></form:options>
				</form:select>
			</div>
			<div class="col-xs-5">
				<form:errors path="taskStatus" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="reasonForDelayOrPreCompletion">Reason:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="reasonForDelayOrPreCompletion"
					cssClass="form-control" disabled="${taskIdExist}"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="reasonForDelayOrPreCompletion"
					cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="customerName">Customer name:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="customerName" cssClass="form-control"
					readonly="${frole}"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="customerName" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="customerPhoneNumber">Customer Phone:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="customerPhoneNumber" cssClass="form-control"
					readonly="${frole}"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="customerPhoneNumber" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2">
				<form:label path="customerEmail">Customer Address:</form:label>
			</div>
			<div class="col-xs-4">
				<form:input path="customerEmail" cssClass="form-control"
					readonly="${frole}"></form:input>
			</div>
			<div class="col-xs-5">
				<form:errors path="customerEmail" cssClass="text-danger"></form:errors>
			</div>
		</div>

		<div class="form-group row">
			<div class="col-xs-2"></div>
			<div class="row">
				<button type="submit" value="Create Task" class="btn btn-primary"
					style="margin-left: 15px">
					<c:choose>
						<c:when test="${not empty taskId}">
							Save Task
						</c:when>
						<c:otherwise>
							Create Task
						</c:otherwise>
					</c:choose>
				</button>
				<button type="button"
					onclick="document.getElementById('newTaskForm').reset();"
					value="Reset" class="btn btn-warning" style="margin-left: 15px">Reset</button>
			</div>
			<div class="col-xs-2"></div>
		</div>

	</form:form>
</body>
</html>