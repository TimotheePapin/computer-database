<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
		</div>
	</header>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: ${Computer.id}</div>
					<h1>Edit Computer</h1>
					<form action="editComputer" method="POST"
						onsubmit="return validation(this)">
						<input type="hidden" value="0" /> <input type="hidden" name="id"
							value="${Computer.id}">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									type="text" name="computerName" class="form-control"
									id="computerName" value="${Computer.name}"
									onblur="checkName(this)">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date (dd/mm/yyyy)</label> <input
									type="text" class="form-control" id="introduced"
									value="${Computer.introduced}" name="introduced"
									onblur="checkIntroduced(this)">
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date (dd/mm/yyyy)</label>
								<input type="text" class="form-control" id="discontinued"
									value="${Computer.discontinued}" name="discontinued"
									onblur="checkDiscontinued(this)">
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${Companies}" var="company">
										<option value="${company.id}"><c:out
												value="${company.name}" /></option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript" src="js/validation.js"></script>
</body>
</html>