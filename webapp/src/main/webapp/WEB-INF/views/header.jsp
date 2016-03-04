<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="dashboard"> Application - Computer
			Database </a>
			<a class="logout" href="logout"><spring:message code="logout" /></a>
			<div id="langue">
				<a href="?language=en"><img class="flags" src="fonts/english.jpeg" alt="uk flag"></a>
				<a href="?language=fr"><img class="flags" src="fonts/french.jpeg" alt="fr flag"></a>
			</div>
	</div>
</header>