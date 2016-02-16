<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
				<div id="langue">
					<a href="?language=en"><img class="flags" src="fonts/english.jpeg" alt="uk flag"></a>
					<a href="?language=fr"><img class="flags" src="fonts/french.jpeg" alt="fr flag"></a>
				</div>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<c:out value="${webPage.dbSize}" />
				<spring:message code="computer.found" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="" method="GET" class="form-inline">
						<input type="search" id="searchbox" name="search" class="form-control"
							placeholder="<spring:message code="search.name"/>"
							<c:if test="${webPage.search ne \"\"}">
							value="${webPage.search}"
							</c:if>/> 
							<input type="submit" id="searchsubmit"
							value="<spring:message code="filter"/>" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="addComputer"><spring:message
							code="add.computer" /></a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();">
						<spring:message code="edit"/></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="#" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><c:if test="${webPage.by eq \"computer.name\"}">
								<c:if test="${webPage.order eq \"ASC\"}">
									<a id="orderComputerName"
										href="dashboard?search=${webPage.search}&by=computer.name&order=DESC">
										<spring:message code="computer.name"/></a>
								</c:if>
								<c:if test="${webPage.order eq \"DESC\"}">
									<a id="orderComputerName"
										href="dashboard?search=${webPage.search}&by=computer.name&order=ASC">
										<spring:message code="computer.name"/></a>
								</c:if>
							</c:if> <c:if test="${webPage.by ne \"computer.name\"}">
								<a id="orderComputerName"
									href="dashboard?search=${webPage.search}&by=computer.name&order=ASC">
									<spring:message code="computer.name"/></a>
							</c:if></th>
						<th><c:if test="${webPage.by eq \"computer.introduced\"}">
								<c:if test="${webPage.order eq \"ASC\"}">
									<a id="orderComputerIntroduced"
										href="dashboard?search=${webPage.search}&by=computer.introduced&order=DESC">
										<spring:message code="introduced.date"/></a>
								</c:if>
								<c:if test="${webPage.order eq \"DESC\"}">
									<a id="orderComputerIntroduced"
										href="dashboard?search=${webPage.search}&by=computer.introduced&order=ASC">
										<spring:message code="introduced.date"/></a>
								</c:if>
							</c:if> <c:if test="${webPage.by ne \"computer.introduced\"}">
								<a id="orderComputerIntroduced"
									href="dashboard?search=${webPage.search}&by=computer.introduced&order=ASC">
									<spring:message code="introduced.date"/></a>
							</c:if></th>
						<th><c:if test="${webPage.by eq \"computer.discontinued\"}">
								<c:if test="${webPage.order eq \"ASC\"}">
									<a id="orderComputerDiscontinued"
										href="dashboard?search=${webPage.search}&by=computer.discontinued&order=DESC">
										<spring:message code="discontinued.date"/></a>
								</c:if>
								<c:if test="${webPage.order eq \"DESC\"}">
									<a id="orderComputerDiscontinued"
										href="dashboard?search=${webPage.search}&by=computer.discontinued&order=ASC">
										<spring:message code="discontinued.date"/></a>
								</c:if>
							</c:if> <c:if test="${webPage.by ne \"computer.discontinued\"}">
								<a id="orderComputerDiscontinued"
									href="dashboard?search=${webPage.search}&by=computer.discontinued&order=ASC">
									<spring:message code="discontinued.date"/></a>
							</c:if></th>
						<th><c:if test="${webPage.by eq \"company.name\"}">
								<c:if test="${webPage.order eq \"ASC\"}">
									<a id="orderCompanyName"
										href="dashboard?search=${webPage.search}&by=company.name&order=DESC">
										<spring:message code="company"/></a>
								</c:if>
								<c:if test="${webPage.order eq \"DESC\"}">
									<a id="orderCompanyName"
										href="dashboard?search=${webPage.search}&by=company.name&order=ASC">
										<spring:message code="company"/></a>
								</c:if>
							</c:if> <c:if test="${webPage.by ne \"company.name\"}">
								<a id="orderCompanyName"
									href="dashboard?search=${webPage.search}&by=company.name&order=ASC">
									<spring:message code="company"/></a>
							</c:if></th>
					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach items="${webPage.computersDTO}" var="computer">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${computer.id}"></td>
							<td name="computer"><a href="editComputer?id=${computer.id}"
								onclick=""><c:out value="${computer.name}" /></a></td>
							<td name="introduced"><c:out value="${computer.introduced}" /></td>
							<td name="discontinued"><c:out
									value="${computer.discontinued}" /></td>
							<td name="company"><c:out value="${computer.company}" /></td>
						</tr>
					</c:forEach>

				</tbody>
			</table>
		</div>
	</section>
	<footer class="navbar-fixed-bottom">
		<mylib:pagination webPage="${webPage}" />
	</footer>
	<script type="text/javascript">
		var delete_message = "<spring:message code='delete.message'/>";
	</script>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>