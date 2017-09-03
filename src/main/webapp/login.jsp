<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>Hall Schematic</title>
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
              <h2 class="panel-title text-center"><strong>Login User</strong></h2>
            </div>
            <div class="panel-body">
              <div class="col-sm-offset-1 col-sm-10">
                <form role="form" method="post" action="login" class="form-horizontal">
                  <div class="form-group">
                    <input
                            class="form-control" type="text" name="login"
                            placeholder="Login" required autofocus>
                  </div>
                  <div class="form-group">
                    <input class="form-control" type="password" name="password"
                           placeholder="Password">
                  </div>
                  <div class="form-group">
                    <input class="btn btn-primary col-sm-12" type="submit"
                           name="action"
                           value="Login">
                  </div>
                  <div class="form-group">
                    <a
                            class="btn btn-default col-sm-12"
                            href="${pageContext.servletContext.contextPath}/registration.jsp">Register new
                      user</a>
                  </div>
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