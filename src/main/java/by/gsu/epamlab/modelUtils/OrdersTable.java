package by.gsu.epamlab.modelUtils;


import by.gsu.epamlab.beans.Order;
import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.daoimp.OrderDaoImp;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdersTable implements ITableOperation {
    public void addRecord(HttpServletRequest req) {
        int userId = Integer.parseInt(req.getParameter("userId"));
        int eventId = Integer.parseInt(req.getParameter("eventId"));
        String ticket = req.getParameter("ticket");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(req.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Order order = new Order(1, userId, eventId, date, ticket);
        new OrderDaoImp().insert(order);
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
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(req.getParameter("date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Order order = new Order(id, userId, eventId, date, ticket);
        new OrderDaoImp().update(order);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ORDER_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ORDERS);
    }

    public void deleteRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        new OrderDaoImp().delete(id);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ORDER_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ORDERS);
    }
}
