<%@ page import="by.gsu.epamlab.controllers.Constant" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Error Page</title>
    <link rel="shortcut icon" href="resourses/img/movie_logo.jpg">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="resourses/style/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="resourses/style/bootstrap-theme.min.css">
    <!-- Add user library -->
    <link rel="stylesheet" href="resourses/style/style.css">
    <!-- Add icon library -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- JQuery -->
    <script src="resourses/js/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="resourses/js/bootstrap.min.js"></script>
</head>
<body>

    <div class="container">
        <!-- Header -->
        <jsp:include page="header.jsp"/>

        <!-- Content -->
        <div class="row">
            <div class="danger">
                <p>
                    <strong>Error</strong>
                    <span class="glyphicon glyphicon-exclamation-sign"></span>
                    <c:choose>
                        <c:when test="${!empty errorMessage }">
                            <c:out value="${errorMessage}"/>
                        </c:when>
                        <c:otherwise>
                            <c:out value="<%=Constant.Errors.ERROR_UNKNOWN%>"/>
                        </c:otherwise>
                    </c:choose>
                </p>
            </div>
                <c:if test="${!empty prevPage}">
                    <a class="btn default" href="${prevPage}">Back</a>
                </c:if>
                <a class="btn default"
                   href="login.jsp">Login Page</a>
        </div>
        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>
