<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(document).ready(
			function() {
				$("#userList").searcher({
					inputSelector : "#filter"
				});
				$("#userList").on(
						'click',
						'#btnDelete',
						function() {
							var $tr = $(this).closest("tr")
							var $tds = $tr.find("td:nth-child(1)");
							$("#dialog").dialog(
									{
										resizable : false,
										height : "auto",
										width : 400,
										title : "Delete Task",
										modal : true,
										buttons : {
											"Yes" : function() {
												var url = String(location.href)
														.replace("#", "")
														+ "/"
														+ $tds.text()
														+ "/deleteEmp";
												$.get(url, function(data) {
													location.reload();
													$("#dialog")
															.dialog("close");
												});
											},
											"No" : function() {
												$(this).dialog("close");
											}
										}
									});
						});
				
				$("#userList").tablesorter();
			});
</script>
<div class="container" style="width: auto;">
	<div class="text-right">
		<input id="filter" placeholder="Search" class="form-control"
			style="display: inline-block; width: 200px; float: left"> <a
			href="userList" class="btn btn-success" role="button" id="refresh">Refresh</a>
		<c:if
			test="${not empty loginUser && (loginUser.empRole=='Manager' || loginUser.empRole=='Boss' )}">
			<a href="newUser" class="btn btn-info" role="button">Create New
				Profile</a>
		</c:if>
	</div>
	<spring:url
		value="${(loginUser.empRole=='Manager' || loginUser.empRole=='Boss')? 'visibility':'hidden'}"
		var="mRole"></spring:url>
	<div class="panel table-responsive" style="min-height: 680px">
		<c:set var="today1" value="<%=new java.util.Date()%>">
		</c:set>
		<fmt:formatDate pattern="dd-MM-yyyy" value="${today1}" var="today" />
		<table id="userList"
			class="table table-striped table-hover tablesorter">
			<thead>
				<tr class="bg-primary">
					<th style="cursor: pointer;">Employee&nbsp;#</th>
					<th style="cursor: pointer;">Employee&nbsp;Name</th>
					<th style="cursor: pointer;">Role</th>
					<th style="cursor: pointer;">Contact Number</th>
					<th style="cursor: pointer;">Contact Address</th>
					<th style="cursor: pointer;">Status</th>
					<th>Modify</th>
					<th>Remove</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="task" items="${userList}">
					<spring:url
						value="${(task.empRole=='Boss')? 'disabled':'enabled'}"
						var="deletionRole"></spring:url>

					<spring:url
						value="${((loginUser.empRole=='Boss')||((task.empRole=='Manager' && loginUser.empId == task.empId)||(loginUser.empRole == 'Manager' && task.empRole=='Administrator')) || (loginUser.empId == task.empId && task.empRole == 'Administrator')||(task.empRole == 'User'))? 'enabled':'disabled'}"
						var="modifyRole"></spring:url>

					
						
					<tr class="tr">
						<td>${task.empId}</td>
						<td>${task.empName}</td>
						<td>${task.empRole}</td>
						<td>${task.contactNo}</td>
						<td>${task.contactAddress}</td>
						<td>${task.empStatus}</td>
						<td><spring:url value="/${task.empId}/editEmp" var="editEmp"></spring:url>
							<button type="button" class="btn btn-warning btn-sm"
								onclick="location.href='${editEmp}'" ${modifyRole}>Edit</button></td>
						<td><spring:url value="/${task.empId}/deleteEmp"
								var="removeTask"></spring:url>
							<button type="button" id="btnDelete" onclick="${removeTask}"
								class="btn btn-danger btn-sm" ${deletionRole}>Remove</button>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div id="dialog" style="display: none" align="center">Do you want
		to delete this record?</div>
</div>