<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<jsp:include page="/WEB-INF/views/head.jsp"></jsp:include>
<body>
    <jsp:include page="/WEB-INF/views/header.jsp"></jsp:include>

    <section id="main">
        <div class="container">
            <div class="alert alert-danger">
            	<spring:message code="404"/>
                <br/>
                <!-- stacktrace -->
            </div>
        </div>
    </section>

    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/dashboard.js"></script>

</body>
</html>