<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
.navbar-nav1 {
	width: 100%;
	text-align: center;
}

.navbar-nav1>li {
	float: none;
	display: inline-block;
}

.nav>li>a:focus, .nav>li>a:hover {
	background-color: #337ABE;
	font-size: 14px;
	color: #fff;
}
</style>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">RAM &#38; CO</a>
		</div>
		<ul class="nav navbar-nav navbar-right">

			<c:if test="${ not empty loginUser}">
				<li><a href="#">Welcome: <c:out
							value="${loginUser.empName}"></c:out>
				</a></li>
				<li><a href="logOut">Sign Out</a></li>
			</c:if>
		</ul>
	</div>
	<div class="container">
		<ul class="nav navbar-nav1">
			<c:if test="${not empty loginUser}">
				<c:if
					test="${loginUser.empRole =='Manager' || loginUser.empRole =='Administrator' || loginUser.empRole =='Boss'}">
					<li><button onclick="location.href='#'"
							class="btn btn-primary" disabled='true'>Dashboard</button></li>
				</c:if>
				<li><button onclick="location.href='taskList'"
						class="btn btn-primary">Task Manager</button></li>
				<c:if
					test="${loginUser.empRole =='Manager' || loginUser.empRole =='Administrator' || loginUser.empRole =='Boss'}">
					<li><button onclick="location.href='userList'"
							class="btn btn-primary">Profile Manager</button></li>
				</c:if>
			</c:if>
		</ul>
	</div>
</nav>
