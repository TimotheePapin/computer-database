<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mylib"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/head.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	<c:url value="/j_spring_security_check" var="loginUrl" />
	<div class=logger>
		<form action="${loginUrl}" method="post" class=form>
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
			<button type="submit" class="btn" id="logbtn"><spring:message code="identification.login" /></button>
		</form>
		</div>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
</html>