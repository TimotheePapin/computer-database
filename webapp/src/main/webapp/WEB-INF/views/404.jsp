<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
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

    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>
    <script src="../js/dashboard.js"></script>

</body>
</html>