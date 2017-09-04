package by.gsu.epamlab.model.modelUtils.admin;


import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.model.beans.Order;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OrdersTable implements ITableOperation {
    public void addRecord(HttpServletRequest req) {
        int userId = Integer.parseInt(req.getParameter("userId"));
        int eventId = Integer.parseInt(req.getParameter("eventId"));
        String ticket = req.getParameter("ticket");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(req.getParameter("date"));
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        Order order = new Order(1, userId, eventId, date, ticket);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().insert(order);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ORDER_ADD_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ORDERS);
    }

    public void editRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        int userId = Integer.parseInt(req.getParameter("userId"));
        int eventId = Integer.parseInt(req.getParameter("eventId"));
        String ticket = req.getParameter("ticket");
        Date date = null;
        try {
            date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(req.getParameter("date"));
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }

        Order order = new Order(id, userId, eventId, date, ticket);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().update(order);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ORDER_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ORDERS);
    }

    public void deleteRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getOrderDao().delete(id);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ORDER_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ORDERS);
    }
}
