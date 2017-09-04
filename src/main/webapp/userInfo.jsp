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
            <ul id="userNavBar"  class="nav navbar-nav">
                <li id="liUserInfo"><a href="#">User Info</a></li>
                <li id="liActualOrders"><a href="#">Actual Orders</a></li>
                <li id="liArchiveOrders"><a href="#">Archive Orders</a></li>
            </ul>
        </div>
        <hr>

        <div id="content">
            <div id="divUserInfo" class="row">
                <p><strong>NickName:</strong> ${user.nickName}</p>
                <p><strong>First Name:</strong> ${user.firstName}</p>
                <p><strong>Second Name:</strong> ${user.secondName}</p>
                <p><strong>E-mail:</strong> ${user.email}</p>
                <p><strong>Level Access:</strong> ${user.role.levelAccess} - ${user.role.description}</p>
            </div>
            <div id="divActualOrders" class="row">
                <table class="table table-striped">
                    <th>Order Number</th>
                    <th>Movie</th>
                    <th>Seat Place</th>
                    <th>Date</th>
                    <c:forEach var="order" items="${userActualyOrder}">
                    <tr>
                        <td>
                            <small>${order.id}</small>
                        </td>
                        <td>
                            <small>
                                <c:forEach var="event" items="${eventList}">
                                    <c:if test="${event.id == order.eventId}">
                                        <c:forEach var="film" items="${filmList}">
                                            <c:if test="${film.id == event.filmId}">
                                                <a href="${pageContext.servletContext.contextPath}/event?id=${order.eventId}">
                                                    ${film.name} ${event.eventData} in ${event.eventTime}
                                                </a>
                                            </c:if>
                                        </c:forEach>
                                    </c:if>
                                </c:forEach>
                            </small>
                        </td>
                        <td>
                            <small>
                                <a href="${pageContext.servletContext.contextPath}/booking?id=${order.eventId}">${order.ticketString}</a>
                            </small>
                        </td>
                        <td>
                            <small>
                                ${order.orderData} in ${order.orderTime}
                            </small>
                        </td>
                    </tr>
                </c:forEach>
                </table>
            </div>
            <div id="divArchiveOrders" class="row">
                <table class="table table-striped">
                    <th>Order Number</th>
                    <th>Movie</th>
                    <th>Seat Place</th>
                    <th>Date</th>
                    <c:forEach var="order" items="${userArchiveOrder}">
                        <tr>
                            <td>
                                <small>${order.id}</small>
                            </td>
                            <td>
                                <small>
                                    <c:forEach var="event" items="${eventList}">
                                        <c:if test="${event.id == order.eventId}">
                                            <c:forEach var="film" items="${filmList}">
                                                <c:if test="${film.id == event.filmId}">
                                                    <a href="${pageContext.servletContext.contextPath}/event?id=${order.eventId}">
                                                            ${film.name} ${event.eventData} in ${event.eventTime}
                                                    </a>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </small>
                            </td>
                            <td>
                                <small>
                                    <a href="${pageContext.servletContext.contextPath}/booking?id=${order.eventId}">${order.ticketString}</a>
                                </small>
                            </td>
                            <td>
                                <small>
                                        ${order.orderData} in ${order.orderTime}
                                </small>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>


        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
        <script src="resourses/js/javaScript.js"></script>

    </div>
</body>
</html>
