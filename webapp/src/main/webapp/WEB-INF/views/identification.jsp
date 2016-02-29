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
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	<c:url value="/j_spring_security_check" var="loginUrl" />
	<form action="${loginUrl}" method="post">
		<c:if test="${error eq true}">
			<p style="color:red">
				<spring:message code="identification.invalid" />
			</p>
		</c:if>
		<c:if test="${logout eq true}">
			<p style="color:red">
				<spring:message code="identification.logout" />
			</p>
		</c:if>
		<p>
			<label for="username"><spring:message code="identification.username" /></label>
			<input type="text" id="username" name="username"/>
		</p>
		<p>
			<label for="password"><spring:message code="identification.password" /></label>
			<input type="password" id="password" name="password"/>
		</p>
		<input type="hidden"
			name="${_csrf.parameterName}"
			value="${_csrf.token}"/>
		<button type="submit" class="btn"><spring:message code="identification.login" /></button>
	</form>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>