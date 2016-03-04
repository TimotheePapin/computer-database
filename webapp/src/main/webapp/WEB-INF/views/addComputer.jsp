<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/head.jsp"></jsp:include>
<body>
	<jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>
	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>
						<spring:message code="add.computer" />
					</h1>
					<form:form modelAttribute="ComputerDTO" action="addComputer" method="POST" commandeName="addComputer" onsubmit="return validation(this)">
						<fieldset>
							<div class="form-group">
								<form:label path="name" for="computerName"><spring:message code="computer.name" /></form:label>
								<spring:message code="computer.name" var="messageName"/>
								<form:input path="name" type="text" name="name" class="form-control" id="computerName"
									placeholder="${messageName}" onblur="checkName(this)"></form:input>
								<form:errors path="name" id="nameError" style="color:red"></form:errors>
							</div>
							<div class="form-group">
								<form:label path="introduced" for="introduced"><spring:message	code="title.introduced" /></form:label>
								<spring:message code="introduced.date" var="messageIntr"/>
								<form:input path="introduced" type="text" class="form-control" id="introduced"
									placeholder="${messageIntr}" name="introduced"
									onblur="checkIntroduced(this)"></form:input>
								<form:errors path="introduced" id="introducedError" style="color:red"></form:errors>
							</div>
							<div class="form-group">
								<form:label path="discontinued" for="discontinued"><spring:message code="title.discontinued" /></form:label>
								<spring:message code="discontinued.date" var="messageDisc"/>
								<form:input path="discontinued" type="text" class="form-control" id="discontinued"
									placeholder="${messageDisc}" name="discontinued"
									onblur="checkDiscontinued(this)"></form:input>
								<form:errors path="discontinued" id="discontinuedError" style="color:red"></form:errors>
							</div>
							<div class="form-group">
								<form:label path="companyId" for="companyId"><spring:message code="company" /></form:label>
								<form:select path="companyId" class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>
									<c:forEach items="${Companies}" var="company">
										<option value="${company.id}"><c:out
												value="${company.name}" /></option>
									</c:forEach>
								</form:select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="<spring:message code="add"/>"
								class="btn btn-primary">
							<spring:message code="or" />
							<a href="dashboard" class="btn btn-default"><spring:message
									code="cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<script type="text/javascript">
		var regex_date = "<spring:message code='date.regex'/>";
		var warning = "<spring:message code='add.warning'/>";
	</script>
	<script type="text/javascript" src="js/validation.js"></script>
</body>
</html>