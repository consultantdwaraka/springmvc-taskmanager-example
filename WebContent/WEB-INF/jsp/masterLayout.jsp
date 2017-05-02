<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Expires" content="0">
<spring:url value="/resources/css/bootstrap.min.css" var="mainCss" />
<spring:url value="/resources/css/jquery-ui.css" var="jQueryCss" />
<spring:url value="/resources/css/jquery-ui.min.css" var="jQueryMinCss" />


<spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />
<spring:url value="/resources/js/jquery-3.1.0.min.js" var="jQueryJs" />
<spring:url value="/resources/js/jquery-ui.js" var="jQueryUIJs" />
<spring:url value="/resources/js/jquery-ui.min.js" var="jQueryUIMinJs" />
<spring:url value="/resources/js/jquery.tablesorter.js"
	var="tableSorter" />
<spring:url value="/resources/js/jquery.tablesorter.widgets.js"
	var="tableSorterWidgets" />
<spring:url value="/resources/js/jquery.searcher.js" var="filter" />

<link href="${mainCss}" rel="stylesheet" />
<link href="${jQueryCss}" rel="stylesheet" />
<link href="${jQueryMinCss}" rel="stylesheet" />
<link
	href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css"
	rel="stylesheet" />

<script src="${bootstrapJs}"></script>
<script src="${jQueryJs}"></script>
<script src="${jQueryUIJs}"></script>
<script src="${jQueryUIMinJs}"></script>
<script src="${tableSorter}"></script>
<script src="${tableSorterWidgets}"></script>
<script src="${filter}"></script>
<script>
	$(document).ready(function() {
		$("#startDate").datepicker({
			dateFormat : "dd-mm-yy"
		});
		$("#endDate").datepicker({
			dateFormat : "dd-mm-yy"
		});
		$(window).scroll(function() {
			if ($(this).scrollTop() > 50) {
				$('#back-to-top').fadeIn();
			} else {
				$('#back-to-top').fadeOut();
			}
		});
		// scroll body to 0px on click
		$('#back-to-top').click(function() {
			$('#back-to-top').tooltip('hide');
			$('body,html').animate({
				scrollTop : 0
			}, 800);
			return false;
		});

		$('#back-to-top').tooltip('show');
	});
</script>
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<style>
.footer {
	position: relative;
	bottom: 0;
	width: 100%;
	/* Set the fixed height of the footer here */
	height: 60px;
	background-color: #f5f5f5;
}

.back-to-top {
	cursor: pointer;
	position: fixed;
	bottom: 0;
	right: 20px;
	display: none;
}
</style>
</head>
<body>
	<div style="width: 100%">
		<!-- Header -->
		<tiles:insertAttribute name="header" />
		<!-- Body Page -->

		<div class="container" style="width: auto; margin-left: 100px;">
			<c:if test="${not empty loginUser}">
				<div class="page-header">
					<h1>
						<tiles:insertAttribute name="title" ignore="true" />
					</h1>
				</div>
			</c:if>
			<tiles:insertAttribute name="body" />
		</div>
		<!-- Footer Page -->
		<footer class="panel-footer">
		<div class="container">
			<p class="text-muted">
				<tiles:insertAttribute name="footer" />
			</p>
		</div>
		</footer>

	</div>
	<a id="back-to-top" href="#" class="btn btn-primary btn-md back-to-top"
		role="button" title="Back to Top" data-toggle="tooltip"
		data-placement="top"> <span> Back To Top</span>
	</a>
</body>
</html>