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
        <div class="col-sm-10">
            <h4 class="text-uppercase">
                <span style="padding-left: 60px">
                    <strong>Order Tickets In
                        <c:if test="${!empty eventFilm}">
                            <i>"${eventFilm.name}"</i>
                        </c:if>
                    </strong>
                </span>
            </h4>
        </div>
        <div class="col-sm-2">
            <c:if test="${!empty user}">
                <c:if test="${hallOrderDisable != true}">
                    <button id="buyBtn" class="btn btn-info col-sm-12">Buy Ticket</button>
                </c:if>
                <c:if test="${hallOrderDisable == true}">
                    <p>Event Has Been Finish</p>
                </c:if>
            </c:if>
        </div>
    </div>
    <hr/>

    <div class="hall-info-msg"></div>

    <!-- Информация по сеансу -->
    <div class="row text-center">
        <div id="holder">
            <div id="movie-screen"><strong>Movie Screen</strong></div>
            <ul id="place"></ul>
            <ul id="seatDescription">
                <li class="seatDesc"></li><p>Free Seat</p>
                <li class="seatBusyDesc"></li><p>Busy Seat</p>
                <li class="selectedSeatDesc"></li><p>In Booking Process</p>
                <li class="selectingSeatDesc"></li><p>Your Chose</p>
            </ul>

        </div>
    </div>

    <!-- Footer -->
    <jsp:include page="footer.jsp"/>

    <input id="idEvent" type="hidden" name="idEvent" value="${event.id}">
    <input id="userSession" type="hidden" value="${sessionScope.get("user")}">
    <script>
        var seat = ${seatBusy};
        var seatSelected = ${seatSelected};
    </script>
    <script src="resourses/js/javaScript.js"></script>
</div>

</body>
</html>