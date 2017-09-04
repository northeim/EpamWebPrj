package by.gsu.epamlab.controllers;

import by.gsu.epamlab.model.factory.AbstractDaoFactory;
import by.gsu.epamlab.model.factory.DataBaseDaoFactory;

public class Constant {

    public static final Class<? extends AbstractDaoFactory> FACTORY = DataBaseDaoFactory.class;
//    public static final Class<? extends AbstractDaoFactory> FACTORY = MemoryDaoFactory.class;

    public static class Param {
        public static final String IMG_SERVER_REPO = "ImgServerRepo";
    }

    public static class Page {
        public static final String ERROR_PAGE = "/error.jsp";
        public static final String LOGIN_PAGE = "/login.jsp";
        public static final String REGISTRATION_PAGE = "/registration.jsp";
        public static final String INDEX_PAGE = "/index.jsp";
        public static final String ADMIN_PAGE = "/admin.jsp";
        public static final String EVENT_PAGE = "/event.jsp";
        public static final String HALL_PAGE = "/hall.jsp";
        public static final String USER_PAGE = "/userInfo.jsp";

    }

    public static class Controller {
        public static final String ERROR_CONTROLLER = "/error";
        public static final String EVENTS_CONTROLLER = "/events";
    }

    public static  class Action {
        public static final String ACTION = "action";
        public static final String DELETE = "delete";
        public static final String EDIT = "edit";
        public static final String ADD = "add";
    }


    public static class Fields {
        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String USER = "user";
        public static final String USER_ACTUALY_ORDER = "userActualyOrder";
        public static final String USER_ARCHIVE_ORDER = "userArchiveOrder";
        public static final String USER_LIST = "userList";
        public static final String ROLE_LIST = "roleList";
        public static final String AUTHOR_LIST = "authorList";
        public static final String EVENT_LIST = "eventList";
        public static final String ORDER_LIST = "orderList";
        public static final String FILM_LIST = "filmList";
        public static final String EVENT = "event";
        public static final String EVENT_AUTHOR = "eventAuthor";
        public static final String EVENT_FILM = "eventFilm";
        public static final String PREV_PAGE = "prevPage";
        public static final String USER_TABLE_STATUS = "userTableStatus";
        public static final String ADMIN_UL_ID = "adminUlId";
        public static final String HALL_SEAT_BUSY = "seatBusy";
        public static final String HALL_SEAT_SELECTED = "seatSelected";
        public static final String HALL_ORDER_DISABLE = "hallOrderDisable";
        public static final String BOOK_PROC_METHOD = "bookProcMethod";
        public static final String TOTAL_USER_ON_SITE = "totalUserOnSite";

    }

    public static class Errors {
        public static final String ERROR_UNKNOWN = "Unknown error. Please " +
                "contact with developers.";
        public static final String ERROR_WRONG_LOGIN_OR_PASSWORD = "Invalid " +
                "login or password.";
        public static final String ERROR_NOT_ADMIN = "Not Admin Privilegies";
        public static final String ERROR_INVALID_PARAMETER = "Invalid " +
                "Parameter";
    }

    public static class Message {
        public static final String USER_ADD_SUCCEFULL = "User Add Succefull";
        public static final String USER_EDIT_SUCCEFULL = "User Edit Succefull";
        public static final String USER_DELETE_SUCCEFULL = "User Delete Succefull";

        public static final String AUTHOR_ADD_SUCCEFULL = "Author Add Succefull";
        public static final String AUTHOR_EDIT_SUCCEFULL = "Author Edit Succefull";
        public static final String AUTHOR_DELETE_SUCCEFULL = "Author Delete Succefull";

        public static final String FILM_ADD_SUCCEFULL = "Film Add Succefull";
        public static final String FILM_EDIT_SUCCEFULL = "Film Edit Succefull";
        public static final String FILM_DELETE_SUCCEFULL = "Film Delete Succefull";

        public static final String EVENT_ADD_SUCCEFULL = "Event Add Succefull";
        public static final String EVENT_EDIT_SUCCEFULL = "Event Edit Succefull";
        public static final String EVENT_DELETE_SUCCEFULL = "Event Delete Succefull";

        public static final String ORDER_ADD_SUCCEFULL = "Order Add Succefull";
        public static final String ORDER_EDIT_SUCCEFULL = "Order Edit Succefull";
        public static final String ORDER_DELETE_SUCCEFULL = "Order Delete Succefull";

        public static final String ROLE_ADD_SUCCEFULL = "Role Add Succefull";
        public static final String ROLE_EDIT_SUCCEFULL = "Role Edit Succefull";
        public static final String ROLE_DELETE_SUCCEFULL = "Role Delete Succefull";
    }

    public static class DBTables {
        public static final String TABLE = "table";
        public static final String USERS = "users";
        public static final String ROLE = "role";
        public static final String AUTHORS = "authors";
        public static final String EVENTS = "events";
        public static final String ORDERS = "orders";
        public static final String FILMS = "films";
    }

    public static class AdminLiId {
        public static final String USERS = "#liUsers";
        public static final String ROLE = "#liRole";
        public static final String ORDERS = "#liOrders";
        public static final String EVENTS = "#liEvents";
        public static final String AUTHORS = "#liAuthors";
        public static final String FILMS = "#liFilms";
    }


}
