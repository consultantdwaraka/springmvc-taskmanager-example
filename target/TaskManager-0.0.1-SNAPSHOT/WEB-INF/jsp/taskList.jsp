<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.row {
	margin-left: 100px;
	margin-right: 0px;
}

.col-sm-6  {
	font-size:12px;
}
.col-xs-6 {
	font-size:12px;
}

.ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset {
	text-align: center;
	float: none !important;
}

.ui-dialog .ui-dialog-titlebar-close {
	display: none;
}

.ui-dialog>.ui-widget-header {
	background: #428bca;
	color: #FFFFFF;
}
</style>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#taskList").searcher({
							inputSelector : "#filter"
						});
						$("#taskList")
								.on(
										'click',
										'#btnDelete',
										function() {
											var $tr = $(this).closest("tr")
											var $tds = $tr
													.find("td:nth-child(1)");
											$("#dialog")
													.dialog(
															{
																resizable : false,
																height : "auto",
																width : 400,
																title : "Delete Task",
																modal : true,
																buttons : {
																	"Yes" : function() {
																		var url = String(
																				location.href)
																				.replace(
																						"#",
																						"")
																				+ "/task/"
																				+ $tds
																						.text()
																				+ "/deleteTask";
																		$
																				.get(
																						url,
																						function(
																								data) {
																							location
																									.reload();
																							$(
																									"#dialog")
																									.dialog(
																											"close");
																						});
																	},
																	"No" : function() {
																		$(this)
																				.dialog(
																						"close");
																	}
																}
															});
										});
						var $table = $("#taskList").tablesorter({
							headers : {
								5 : {
									sorter : false
								},
								12 : {
									sorter : false
								},
								13 : {
									sorter : false
								}
							},
							sortList : [ [ 6, 1 ], [ 8, 1 ] ]
						});
						$(".tr")
								.on(
										"dblclick",
										function(event) {
											var $tr = $(this).closest("tr")
											var $tds = $tr
													.find("td:nth-child(1)");
											var url = String(location.href)
													.replace("#", "")
													+ "/task/"
													+ $tds.text()
													+ "/getTask";
											$
													.get(
															url,
															function(data) {
																if (data != null) {
																	var obj = $
																			.parseJSON(data);
																	var startDate = $.datepicker
																			.formatDate(
																					'mm/dd/yy',
																					new Date(
																							obj.taskStartDate));
																	var endDate = $.datepicker
																			.formatDate(
																					'mm/dd/yy',
																					new Date(
																							obj.taskCompletionDate));
																	var creationDate = $.datepicker
																			.formatDate(
																					'mm/dd/yy',
																					new Date(
																							obj.taskCreationDate));
																	$(
																			"#myModal")
																			.html(
																					'');
																	$(
																			"#myModal")
																			.append(
																					"<form role='form'>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'><label>Task Id:</label></div> <div class='col-xs-6'><label>"
																							+ obj.taskId
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'><label>Task Name:</label></div> <div class='col-xs-6'><label>"
																							+ obj.taskName
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'><label> Task Description:</label></div> <div class='col-xs-6'><label>"
																							+ obj.taskDescription
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'><label>Nature Of Task:</label></div> <div class='col-xs-6'><label>"
																							+ obj.taskType
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Task Start Date:</label></div> <div class='col-sm-6'><label>"
																							+ startDate
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Task End Date:</label></div> <div class='col-sm-6'><label>"
																							+ endDate
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Task Creation Date:</label></div> <div class='col-sm-6'><label>"
																							+ creationDate
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Task Assign To:</label></div> <div class='col-sm-6'><label>"
																							+ obj.assignedTo
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Task Created By:</label></div> <div class='col-sm-6'><label>"
																							+ obj.taskCreatedBy
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Task Status:</label></div> <div class='col-sm-6'><label>"
																							+ obj.taskStatus
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Reason:</label></div> <div class='col-sm-6'><label>"
																							+ obj.reasonForDelayOrPreCompletion
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Customer name:</label></div> <div class='col-sm-6'><label>"
																							+ obj.customerName
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Customer Phone:</label></div> <div class='col-sm-6'><label>"
																							+ obj.customerPhoneNumber
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"<div class='form-group row' > <div class='col-xs-4'> <label>Customer Address:</label></div> <div class='col-sm-6'><label>"
																							+ obj.customerEmail
																							+ "</label></div></div>");
																	$(
																			"#myModal")
																			.append(
																					"</form>");
																}
															});

											$("#myModal")
													.dialog(
															{
																modal : true,
																height : 700,
																width : "80%",
																title : 'View Task Details',
																buttons : {
																	"Close" : function() {
																		$(this)
																				.dialog(
																						"close");
																	}
																}
															}).dialog('open');
										});
					}

			);
</script>
<div class="container" style="width: auto;">
	<div class="text-right">
		<input id="filter" placeholder="Search" class="form-control"
			style="display: inline-block; width: 200px; float: left"> <a
			href="taskList" class="btn btn-success" role="button" id="refresh">Refresh</a>
		<c:if
			test="${not empty loginUser && (loginUser.empRole=='Manager' || loginUser.empRole=='Administrator' || loginUser.empRole=='Boss' )}">
			<a href="newTask" class="btn btn-info" role="button">Create New
				Task</a>
		</c:if>
	</div>
	<spring:url
		value="${(loginUser.empRole=='Manager')? 'visibility':'hidden'}"
		var="mRole"></spring:url>
	<div class="panel table-responsive" style="min-height: 680px">
		<c:set var="todayDate" value="<%=new java.util.Date()%>">
		</c:set>
		<fmt:formatDate pattern="yyyyMMdd" value="${todayDate}" var="today" />
		<table id="taskList"
			class="table table-striped table-hover tablesorter">
			<thead>
				<tr class="bg-primary">
					<th style="cursor: pointer;">Task&nbsp;#</th>
					<th style="cursor: pointer;">Task&nbsp;Name</th>
					<th style="cursor: pointer;">Type</th>
					<c:if
						test="${(loginUser.empRole=='Manager'|| loginUser.empRole=='Boss')}">
						<th style="cursor: pointer;">Actual Cost</th>
					</c:if>
					<c:if
						test="${(loginUser.empRole=='Manager'|| loginUser.empRole=='Boss')}">
						<th style="cursor: pointer;">Cleared Balance</th>
					</c:if>
					<c:if
						test="${(loginUser.empRole !='Manager' && loginUser.empRole !='Boss')}">
						<th>Description</th>
					</c:if>
					<th style="cursor: pointer;">Status</th>
					<th style="cursor: pointer;">Start&nbsp;Date</th>
					<th style="cursor: pointer;">End&nbsp;Date</th>
					<th style="cursor: pointer;">Assigned&nbsp;To</th>
					<th style="cursor: pointer;">Customer&nbsp;Name</th>
					<c:if test="${(loginUser.empRole=='Boss')}">
						<th style="cursor: pointer;">Created&nbsp;By</th>
					</c:if>
					<th>Modify</th>
					<th>Remove</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="task" items="${taskList}">
					<spring:url
						value="${(loginUser.empRole=='Boss' || loginUser.empRole=='Manager'||(loginUser.empRole=='Administrator' && loginUser.empId == task.taskCreatedBy))? 'enabled':'disabled'}"
						var="role"></spring:url>

					<tr class="tr">
						<td>${task.taskId}</td>
						<td>${task.taskName}</td>
						<td>${task.taskType}</td>
						<c:if
							test="${(loginUser.empRole=='Manager' || loginUser.empRole=='Boss')}">
							<td><c:choose>
									<c:when test="${not empty task.taskEstimatedCost}">
								${task.taskEstimatedCost}
							</c:when>
									<c:otherwise>
								0.0
							</c:otherwise>
								</c:choose></td>
						</c:if>
						<c:if
							test="${(loginUser.empRole=='Manager' || loginUser.empRole=='Boss')}">
							<td><c:choose>
									<c:when test="${not empty task.taskCost}">
								${task.taskCost}
							</c:when>
									<c:otherwise>
								0.0
							</c:otherwise>
								</c:choose></td>
						</c:if>
						<c:if
							test="${(loginUser.empRole !='Manager' && loginUser.empRole !='Boss')}">
							<td
								style="white-space: normal !important; word-wrap: break-word; min-width: 150px; max-width: 300px">${task.taskDescription}</td>
						</c:if>
						<td style="white-space: nowrap !important"><fmt:formatDate
								value="${task.taskCompletionDate}" pattern="yyyyMMdd"
								var="endDate" /> <c:choose>
								<c:when test="${task.taskStatus eq 'New'}">
									<c:choose>
										<c:when test="${today gt endDate}">
											<p style="color: #E74C3C; font-weight: bolder;">${task.taskStatus}</p>
										</c:when>
										<c:otherwise>
											<p style="color: #2E86C1; font-weight: bolder;">${task.taskStatus}</p>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${task.taskStatus eq 'In-progress'}">
									<c:choose>
										<c:when test="${today gt endDate}">
											<p style="color: #E74C3C; font-weight: bolder;">${task.taskStatus}</p>
										</c:when>
										<c:otherwise>
											<p style="color: #DC7633; font-weight: bolder;">${task.taskStatus}</p>
										</c:otherwise>
									</c:choose>
								</c:when>
								<c:when test="${task.taskStatus eq 'Completed'}">
									<p style="color: #145A32; font-weight: bolder;">${task.taskStatus}</p>
								</c:when>
								<c:when test="${task.taskStatus eq 'Overdue'}">
									<p style="color: #E74C3C; font-weight: bolder;">${task.taskStatus}</p>
								</c:when>
								<c:when test="${task.taskStatus eq 'Pre-completed'}">
									<p style="color: #117864; font-weight: bolder;">${task.taskStatus}</p>
								</c:when>
								<c:when test="${task.taskStatus eq 'Approved'}">
									<p style="color: #17202A; font-weight: bolder;">${task.taskStatus}</p>
								</c:when>
								<c:otherwise>
									<p style="color: #1C2833;">${task.taskStatus}</p>
								</c:otherwise>
							</c:choose></td>
						<td><fmt:formatDate pattern="yyyyMMdd"
								value="${task.taskStartDate}" /></td>
						<td><fmt:formatDate pattern="yyyyMMdd"
								value="${task.taskCompletionDate}" /></td>
						<td>${task.assignedTo}</td>
						<td>${task.customerName}</td>
						<%-- <td>${task.customerPhoneNumber}</td>
						<td>${task.customerEmail}</td> --%>
						<c:if test="${(loginUser.empRole=='Boss')}">
							<td>${task.taskCreatedBy}</td>
						</c:if>
						<td><spring:url value="/task/${task.taskId}/editTask"
								var="editTask"></spring:url>
							<button type="button" class="btn btn-warning btn-sm"
								onclick="location.href='${editTask}'">Update</button></td>
						<td><spring:url value="/task/${task.taskId}/deleteTask"
								var="removeTask"></spring:url>
							<button type="button" id="btnDelete" onclick="${removeTask}"
								class="btn btn-danger btn-sm" ${role}>Remove</button>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="dialog" style="display: none" align="center">Do you want
		to delete this record?</div>

	<!-- Modal -->
	<div id="myModal" style="display: none" role="dialog"></div>
</div>