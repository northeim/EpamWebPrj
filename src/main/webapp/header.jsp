<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Header</title>
    <meta charset="utf-8">
    <!--<meta name="viewport" content="width=device-width, initial-scale=1.0">-->
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

<!-- Header -->
    <div class="row">

        <div class="col-sm-2 text-center">
            <a href="${pageContext.servletContext.contextPath}/events">
                <img
                        src="${pageContext.servletContext.contextPath}/resourses/img/movie_logo.jpg"
                        class="img-circle" width="150">
            </a>
        </div>

        <div class="col-sm-3 text-right">
            <br>
            <em><small>
                The cinema should make the viewer forget that he is in the cinema.
            </small></em>
            <br/>
            <small><strong>Roman Polanski</strong></small>
        </div>

        <div class="col-sm-offset-3 col-sm-4">
            <ul class="nav navbar-nav navbar-right">
                <c:if test="${user.role.levelAccess == 10}">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/admin">
                            <span class="glyphicon glyphicon-wrench"> Admin
                            </span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${empty user}">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/login.jsp">
                            <span class="glyphicon glyphicon-log-in"> Login
                            </span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${!empty user}">
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/userInfo">
                            <span class="glyphicon
                            glyphicon-user"> ${user.nickName}</span>
                        </a>
                    </li>
                    <li>
                        <a href="${pageContext.servletContext.contextPath}/logout">
                            <span class="glyphicon glyphicon-log-out"> Logout
                            </span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
    <hr/>

</body>
</html>