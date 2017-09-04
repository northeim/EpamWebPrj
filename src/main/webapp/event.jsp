<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Event</title>
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

    <!-- Content -->

    <!-- Разделитель -->
    <div class="row">
        <h4 class="text-uppercase">
            <span style="padding-left: 60px">
                <c:if test="${!empty eventFilm}">
                    <strong>${eventFilm.name}</strong>
                </c:if>
            </span></h4>
    </div>
    <hr/>

    <!-- Информация по сеансу -->
    <div class="row">
        <c:if test="${!empty event}">
            <div class="col-sm-2">
                <img class="event-img" src="resourses/img/${eventFilm.coverPath}">
            </div>
            <div class="col-sm-10">
                <p><strong>Date:</strong> ${event.eventData}</p>
                <p><strong>Time:</strong> ${event.eventTime}</p>
                    <c:if test="${hallOrderDisable == true}">
                        <p>Event Has Been Finish</p>
                    </c:if>
                    <c:if test="${hallOrderDisable != true}">
                        <a class="btn btn-info btn-lg" href="${pageContext.servletContext.contextPath}/booking?id=${event.id}">
                            <small>Buy Ticket</small>
                        </a>
                    </c:if>
            </div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-12">
            <p><strong>Description</strong><span class="glyphicon glyphicon-list-alt"></span></p>
            <p id="filmDescription"><i>${eventFilm.description}</i></p>
            <p><strong>Director:</strong> ${eventAuthor.authorName}</p>
        </div>
        </c:if>
    </div>


    <!-- Footer -->
    <jsp:include page="footer.jsp"/>

</div>

</body>
</html>