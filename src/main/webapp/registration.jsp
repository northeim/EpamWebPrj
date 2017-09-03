<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Registration</title>
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

    <jsp:include page="header.jsp"/>

    <div class="row">
        <div class="col-sm-offset-4 col-sm-4">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Register New User</h3>
                </div>

                <div class="panel-body">
                    <div class="col-sm-offset-1 col-sm-10">
                        <form class="form-horizontal" role="form" name="addUser"
                              method="post"
                              action="registration">
                            <div class="form-group">
                                <input class="form-control" type="text"
                                       name="login"
                                       placeholder="Your Login" required="required">
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="password"
                                       name="password" placeholder="Your Password"
                                       required="required">
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="text"
                                       name="firstName"
                                       placeholder="First Name">
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="text"
                                       name="secondName" placeholder="Second Name">
                            </div>
                            <div class="form-group">
                                <input class="form-control" type="email"
                                       name="email" placeholder="Email">
                            </div>
                            <div class="form-group">
                                <input class="btn btn-primary col-sm-12" type="submit"
                                       value="Create User"><br/>
                            </div>
                            <div class="form-group">
                                <a class="btn btn-default col-sm-12"
                                   href="${pageContext.servletContext.contextPath}/login.jsp">Back to
                                    Login</a>
                            </div>
                            <c:if test="${!empty userTableStatus}">
                                <div class="form-group">
                                    <div class="alert alert-success" role="alert"><h4>${userTableStatus}</h4></div>
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>

</div>

</body>
</html>
