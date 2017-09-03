<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="error.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Admin Panel</title>
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

        <div id="divNavRow" class="row">
            <div class="col-sm-offset-0 col-sm-12">
                <ul id="adminNavTable" class="nav navbar-nav">
                    <li id="liAuthors"><a href="#">authors</a> </li>
                    <li id="liFilms"><a href="#">films</a> </li>
                    <li id="liEvents"><a href="#">events</a> </li>
                    <li id="liOrders"><a href="#">orders</a> </li>
                    <li id="liRole"><a href="#">role</a> </li>
                    <li id="liUsers"><a href="#">users</a> </li>
                </ul>
            </div>
        </div>
        <hr>

        <div id="content">

            <div id="divAuthorsTable" class="row">
                <h2>Authors</h2>
                <c:if test="${!empty authorList}" >
                    <table class="table table-hover">
                        <tr>
                            <th class="col-sm-1">id</th>
                            <th class="col-sm-1">name</th>
                            <th class="col-sm-3">description</th>
                            <th class="col-sm-1"></th>
                        </tr>
                        <c:forEach var="author" items="${authorList}">
                            <tr>
                                <form name="authorForm${author.id}" method="post" action="admin">
                                    <input type="hidden" name="action"/>
                                    <input type="hidden" name="table" value="authors"/>
                                    <td>
                                        <input class="form-control" type="text" name="id" value="${author.id}" readonly/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" name="name" value="${author.authorName}"/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" name="description" value="${author.description}"/>
                                    </td>
                                    <td class="text-center">
                                        <a href="JavaScript:sendFormData('authorForm${author.id}', 'action', 'edit')">
                                            <span  class="glyphicon glyphicon-pencil"/>
                                        </a>
                                        <a href="JavaScript:sendFormData('authorForm${author.id}', 'action', 'delete')">
                                            <span  class="glyphicon glyphicon-trash"/>
                                        </a>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        <tr>
                            <form name="authorAddForm" method="post" action="admin">
                                <input type="hidden" name="action"/>
                                <input type="hidden" name="table" value="authors"/>
                                <td>
                                    <input class="form-control" type="text" name="id" placeholder="id" readonly/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="name" placeholder="Name"/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="description" placeholder="Description"/>
                                </td>
                                <td class="text-center">
                                    <a href="JavaScript:sendFormData('authorAddForm', 'action', 'add')">
                                        <span  class="glyphicon glyphicon-plus-sign"/>
                                    </a>
                                </td>
                            </form>
                        </tr>
                    </table>
                </c:if>
            </div>
            <div id="divFilmsTable" class="row">
                <h2>Films</h2>
                <table class="table table-hover">
                    <tr>
                        <th class="col-sm-1">id</th>
                        <th class="col-sm-2">name</th>
                        <th class="col-sm-4">description</th>
                        <th class="col-sm-2">authorId</th>
                        <th class="col-sm-2">CoverPath</th>
                        <th class="col-sm-1"></th>
                    </tr>
                <c:if test="${!empty filmList}" >
                    <c:forEach var="film" items="${filmList}">
                        <tr>
                            <form name="filmForm${film.id}" method="post" action="admin" enctype="multipart/form-data">
                                <input type="hidden" name="action"/>
                                <input type="hidden" name="table" value="films"/>
                                <td>
                                    <input class="form-control" type="text" name="id" value="${film.id}" readonly/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="name" value="${film.name}"/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="description" value="${film.description}"/>
                                </td>
                                <td>
                                    <select class="form-control" name="authorId">
                                        <c:forEach var="author" items="${authorList}">
                                            <c:if test="${film.authorId == author.id}">
                                                <option selected value="${author.id}">${author.authorName}</option>
                                            </c:if>
                                            <c:if test="${film.authorId != author.id}">
                                                <option value="${author.id}">${author.authorName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <img height="300px" h src="${pageContext.servletContext.contextPath}/resourses/img/${film.coverPath}">
                                    <input class="form-control" type="file" name="coverPath" placeholder="CoverPath"/>
                                </td>
                                <td class="text-center">
                                    <a href="JavaScript:sendFormData('filmForm${film.id}', 'action', 'edit')">
                                        <span  class="glyphicon glyphicon-pencil"/>
                                    </a>
                                    <a href="JavaScript:sendFormData('filmForm${film.id}', 'action', 'delete')">
                                        <span  class="glyphicon glyphicon-trash"/>
                                    </a>
                                </td>
                            </form>
                        </tr>
                    </c:forEach>
                </c:if>
                    <tr>
                        <form name="filmAddForm" method="post" action="admin" enctype="multipart/form-data">
                            <input type="hidden" name="action"/>
                            <input type="hidden" name="table" value="films"/>
                            <td>
                                <input class="form-control" type="text" name="id" placeholder="id" readonly/>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="name" placeholder="Name"/>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="description" placeholder="Description"/>
                            </td>
                            <td>
                                <select class="form-control" name="authorId">
                                    <c:forEach var="author" items="${authorList}">
                                        <option value="${author.id}">${author.authorName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input class="form-control" type="file" name="coverPath" placeholder="CoverPath"/>
                            </td>
                            <td class="text-center">
                                <a href="JavaScript:sendFormData('filmAddForm', 'action', 'add')">
                                    <span  class="glyphicon glyphicon-plus-sign"/>
                                </a>
                            </td>
                        </form>
                    </tr>
                </table>
            </div>
            <div id="divEventsTable" class="row">
                <h2>Events</h2>
                <c:if test="${!empty eventList}">
                    <table class="table table-hover">
                        <tr>
                            <th class="col-sm-1">id</th>
                            <th class="col-sm-3">name</th>
                            <th class="col-sm-2">date</th>
                            <th class="col-sm-3">filmId</th>
                            <th class="col-sm-1"></th>
                        </tr>
                        <c:forEach var="event" items="${eventList}">
                            <tr>
                                <form name="eventForm${event.id}" method="post" action="admin">
                                    <input type="hidden" name="action"/>
                                    <input type="hidden" name="table" value="events"/>
                                    <td>
                                        <input class="form-control" type="text" name="id" value="${event.id}" readonly/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" name="name" value="${event.eventName}"/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="datetime" name="date" value="${event.eventDate}"/>
                                    </td>
                                    <td>
                                        <select class="form-control" name="filmId">
                                            <c:forEach var="film" items="${filmList}">
                                                <c:if test="${event.filmId == film.id}">
                                                    <option selected value="${event.filmId}">${film.name}</option>
                                                </c:if>
                                                <c:if test="${event.filmId != film.id}">
                                                    <option value="${film.id}">${film.name}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="text-center">
                                        <a href="JavaScript:sendFormData('eventForm${event.id}', 'action', 'edit')">
                                            <span  class="glyphicon glyphicon-pencil"/>
                                        </a>
                                        <a href="JavaScript:sendFormData('eventForm${event.id}', 'action', 'delete')">
                                            <span  class="glyphicon glyphicon-trash"/>
                                        </a>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        <tr>
                            <form name="eventAddForm" method="post" action="admin">
                                <input type="hidden" name="action"/>
                                <input type="hidden" name="table" value="events"/>
                                <td>
                                    <input class="form-control" type="text" name="id" placeholder="id" readonly/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="name" placeholder="Event Name"/>
                                </td>
                                <td>
                                    <input class="form-control" type="datetime-local" name="date" placeholder="Date"/>
                                </td>
                                <td>
                                    <select class="form-control" name="filmId">
                                        <c:forEach var="film" items="${filmList}">
                                            <option value="${film.id}">${film.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td class="text-center">
                                    <a href="JavaScript:sendFormData('eventAddForm', 'action', 'add')">
                                        <span  class="glyphicon glyphicon-plus-sign"/>
                                    </a>
                                </td>
                            </form>
                        </tr>
                    </table>
                </c:if>
             </div>
            <div id="divOrdersTable" class="row">
                <h2>Orders</h2>
                <c:if test="${!empty orderList}">
                    <table class="table table-hover">
                        <tr>
                            <th class="col-sm-1">id</th>
                            <th class="col-sm-2">userId</th>
                            <th class="col-sm-3">eventId</th>
                            <th class="col-sm-3">ticket</th>
                            <th class="col-sm-2">date</th>
                            <th class="col-sm-1"></th>
                        </tr>
                        <c:forEach var="order" items="${orderList}">
                            <tr>
                                <form name="orderForm${order.id}" method="post" action="admin">
                                    <input type="hidden" name="action"/>
                                    <input type="hidden" name="table" value="orders"/>
                                    <td>
                                        <input class="form-control" type="text" name="id" value="${order.id}" readonly/>
                                    </td>
                                    <td>
                                        <select class="form-control" name="userId">
                                            <c:forEach var="user" items="${userList}">
                                                <c:if test="${user.id == order.userId}">
                                                    <option selected value="${order.userId}">${user.nickName}</option>
                                                </c:if>
                                                <c:if test="${user.id != order.userId}">
                                                    <option value="${user.id}">${user.nickName}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select class="form-control" name="eventId">
                                            <c:forEach var="event" items="${eventList}">
                                                <c:if test="${event.id == order.eventId}">
                                                    <option selected value="${order.eventId}">${event.eventName} at ${event.eventDate}</option>
                                                </c:if>
                                                <c:if test="${event.id != order.eventId}">
                                                    <option value="${event.id}">${event.eventName} at ${event.eventDate}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" name="ticket" value='${order.ticketString}'/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="datetime" name="date" value="${order.orderDate}"/>
                                    </td>
                                    <td class="text-center">
                                        <a href="JavaScript:sendFormData('orderForm${order.id}', 'action', 'edit')">
                                            <span  class="glyphicon glyphicon-pencil"/>
                                        </a>
                                        <a href="JavaScript:sendFormData('orderForm${order.id}', 'action', 'delete')">
                                            <span  class="glyphicon glyphicon-trash"/>
                                        </a>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        <tr>
                            <form name="orderAddForm" method="post" action="admin">
                                <input type="hidden" name="action"/>
                                <input type="hidden" name="table" value="orders"/>
                                <td>
                                    <input class="form-control" type="text" name="id" placeholder="id" readonly/>
                                </td>
                                <td>
                                    <select class="form-control" name="userId">
                                        <c:forEach var="user" items="${userList}">
                                            <c:if test="${user.id == order.userId}">
                                                <option selected value="${order.userId}">${user.nickName}</option>
                                            </c:if>
                                            <c:if test="${user.id != order.userId}">
                                                <option value="${user.id}">${user.nickName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <select class="form-control" name="eventId">
                                        <c:forEach var="event" items="${eventList}">
                                            <c:if test="${event.id == order.eventId}">
                                                <option selected value="${order.eventId}">${event.eventName} at ${event.eventDate}</option>
                                            </c:if>
                                            <c:if test="${event.id != order.eventId}">
                                                <option value="${event.id}">${event.eventName} at ${event.eventDate}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="ticket" placeholder="Ticket List"/>
                                </td>
                                <td>
                                    <input class="form-control" type="datetime-local" name="date">
                                </td>
                                <td class="text-center">
                                    <a href="JavaScript:sendFormData('orderAddForm', 'action', 'add')">
                                        <span  class="glyphicon glyphicon-plus-sign"/>
                                    </a>
                                </td>
                            </form>
                        </tr>
                    </table>
                </c:if>
            </div>
            <div id="divRoleTable" class="row">
                <h2>Role</h2>
                <c:if test="${!empty roleList}" >
                    <table class="table table-hover">
                        <tr>
                            <th class="col-sm-1">id</th>
                            <th class="col-sm-3">description</th>
                            <th class="col-sm-1">levelAccess</th>
                            <th class="col-sm-1"></th>
                        </tr>
                        <c:forEach var="roleEm" items="${roleList}">
                            <tr>
                                <form name="roleForm${roleEm.id}" method="post" action="admin">
                                    <input type="hidden" name="action"/>
                                    <input type="hidden" name="table" value="role"/>
                                    <td>
                                        <input class="form-control" type="text" name="id" value="${roleEm.id}"/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" name="description" value="${roleEm.description}"/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="number" name="levelAccess" min="0" max="10" value="${roleEm.levelAccess}"/>
                                    </td>
                                    <td class="text-center">
                                        <a href="JavaScript:sendFormData('roleForm${roleEm.id}', 'action', 'edit')">
                                            <span  class="glyphicon glyphicon-pencil"/>
                                        </a>
                                        <a href="JavaScript:sendFormData('roleForm${roleEm.id}', 'action', 'delete')">
                                            <span  class="glyphicon glyphicon-trash"/>
                                        </a>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        <tr>
                            <form name="roleAddForm" method="post" action="admin">
                                <input type="hidden" name="action"/>
                                <input type="hidden" name="table" value="role"/>
                                <td>
                                    <input class="form-control" type="text" name="id" placeholder="Id" readonly/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="description" placeholder="Description"/>
                                </td>
                                <td>
                                    <input class="form-control" type="number" name="levelAccess" placeholder="Level Access" min="0" max="10"/>
                                </td>
                                <td class="text-center">
                                    <a href="JavaScript:sendFormData('roleAddForm', 'action', 'add')">
                                        <span  class="glyphicon glyphicon-plus-sign"/>
                                    </a>
                                </td>
                            </form>
                        </tr>
                    </table>
                </c:if>
            </div>
            <div id="divUsersTable" class="row">
                <h2>Users</h2>
                <c:if test="${!empty userList}" >
                    <table class="table table-hover">
                        <tr>
                            <th class="col-sm-1">id</th>
                            <th class="col-sm-1">nickName</th>
                            <th class="col-sm-1">firstName</th>
                            <th class="col-sm-1">secondName</th>
                            <th class="col-sm-2">email</th>
                            <th class="col-sm-1">password</th>
                            <th class="col-sm-2">roleId</th>
                            <th class="col-sm-1"></th>
                        </tr>
                        <c:forEach var="user" items="${userList}">
                            <tr>
                                <form name="userForm${user.id}" method="post" action="admin">
                                    <input type="hidden" name="action"/>
                                    <input type="hidden" name="table" value="users"/>
                                    <td>
                                        <input class="form-control" type="text" name="id" value="${user.id}" readonly/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" name="nickName" value="${user.nickName}" required/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" name="firstName" value="${user.firstName}"/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" name="secondName" value="${user.secondName}"/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="email" name="email" value="${user.email}"/>
                                    </td>
                                    <td>
                                        <input class="form-control" type="text" name="password" value="${user.password}" readonly/>
                                    </td>
                                    <td>
                                        <select class="form-control" name="roleId">
                                            <c:forEach var="role" items="${roleList}">
                                                <c:if test="${user.role.id == role.id}">
                                                    <option selected value="${role.id}">${role.description}</option>
                                                </c:if>
                                                <c:if test="${user.role.id != role.id}">
                                                    <option value="${role.id}">${role.description}</option>
                                                </c:if>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td class="text-center">
                                        <a href="JavaScript:sendFormData('userForm${user.id}', 'action', 'edit')">
                                            <span  class="glyphicon glyphicon-pencil"/>
                                        </a>
                                        <a href="JavaScript:sendFormData('userForm${user.id}', 'action', 'delete')">
                                            <span  class="glyphicon glyphicon-trash"/>
                                        </a>
                                    </td>
                                </form>
                            </tr>
                        </c:forEach>
                        <tr>
                            <form name="userAddForm" method="post" action="admin">
                                <input type="hidden" name="action"/>
                                <input type="hidden" name="table" value="users"/>
                                <td>
                                    <input class="form-control" type="text" name="id" placeholder="id" readonly/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="nickName" placeholder="Login" required/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="firstName" placeholder="FirstName"/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="secondName" placeholder="SecondName"/>
                                </td>
                                <td>
                                    <input class="form-control" type="email" name="email" placeholder="E-mail"/>
                                </td>
                                <td>
                                    <input class="form-control" type="text" name="password" placeholder="Password" required/>
                                </td>
                                <td>
                                    <select class="form-control" name="roleId">
                                        <c:forEach var="role" items="${roleList}">
                                            <c:if test="${user.role.id == role.id}">
                                                <option selected value="${role.id}">${role.description}</option>
                                            </c:if>
                                            <c:if test="${user.role.id != role.id}">
                                                <option value="${role.id}">${role.description}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td class="text-center">
                                    <a href="JavaScript:sendFormData('userAddForm', 'action', 'add')">
                                        <span  class="glyphicon glyphicon-plus-sign"/>
                                    </a>
                                </td>
                            </form>
                        </tr>
                    </table>
                </c:if>

            </div>

            <c:if test="${!empty userTableStatus}">
                <div class="alert alert-success" role="alert"><h4>${userTableStatus}</h4></div>
            </c:if>


        </div>

        <!-- Footer -->
        <jsp:include page="footer.jsp"/>
        <script src="resourses/js/javaScript.js"></script>
        <c:if test="${!empty adminUlId}">
            <script>
                var liId = "${adminUlId}";
            </script>
        </c:if>
        <c:if test="${empty adminUlId}">
            <script>
                var liId = "";
            </script>
        </c:if>

    </div>
</body>
</html>
