<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Movie House</title>
    <link rel="shortcut icon" href="resourses/img/movie_logo.jpg">
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="resourses/style/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="resourses/style/bootstrap-theme.min.css">
    <!-- Add user library -->
    <link rel="stylesheet" href="resourses/style/style.css">
    <!-- Add icon library -->
    <link rel="stylesheet" href="resourses/style/font-awesome.min.css">
    <!-- JQuery -->
    <script src="resourses/js/jquery.min.js"></script>
    <!-- Latest compiled and minified JavaScript -->
    <script src="resourses/js/bootstrap.min.js"></script>
</head>
<body>

    <div class="container">

        <jsp:include page="header.jsp"/>

        <!-- Content -->

        <!-- Разделитель -->
        <div class="row">
            <h4 class="text-uppercase">
                <span style="padding-left: 60px">
                    <strong>Movie's List</strong>
                </span>
            </h4>
        </div>
        <hr/>
        <!-- Киносеансы -->
        <div class="row">
            <c:if test="${!empty eventList}">
                <c:forEach var="event" items="${eventList}">
                    <div class="col-sm-3 text-center">
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <small><em>${event.eventData} in
                                        ${event.eventTime}</em></small>
                                <br>
                                <div class="movie-win">
                                    <div class="movie-name">
                                        <a class="movie-win" href="${pageContext.servletContext.contextPath}/event?id=${event.id}">
                                            <small><strong>${event.eventName}</strong></small><br>
                                            <c:forEach var="film" items="${filmList}">
                                                <c:if test="${film.id == event.filmId}">
                                                    <small><strong>${film.name}</strong></small>
                                        </a>
                                    </div>
                                    <div class="movie-img">
                                        <a href="${pageContext.servletContext.contextPath}/event?id=${event.id}">
                                            <img src="resourses/img/${film.coverPath}">
                                        </a>
                                    </div>
                                                </c:if>
                                            </c:forEach>
                                </div>
                                <hr/>
                                <a class="btn btn-info btn-block"
                                          href="${pageContext.servletContext.contextPath}/booking?id=${event.id}">
                                    <small>Buy Ticket</small>
                                </a>
                            </div>
                        </div>

                    </div>
                </c:forEach>
            </c:if>
        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>

    </div>

</body>
</html>
