package by.gsu.epamlab.model.modelUtils.admin;

import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.model.beans.Event;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EventsTable implements ITableOperation {
    public void addRecord(HttpServletRequest req) {
        String name = req.getParameter("name");
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(req.getParameter("date"));
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        int filmId = Integer.parseInt(req.getParameter("filmId"));

        Event event = new Event(1, name, date, filmId);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getEventDao().insert(event);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.EVENT_ADD_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.EVENTS);
    }

    public void editRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        Date date = null;
        try {
            date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(req.getParameter("date"));
        } catch (ParseException e) {
            throw new RuntimeException(e.getMessage());
        }
        int filmId = Integer.parseInt(req.getParameter("filmId"));

        Event event = new Event(id, name, date, filmId);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getEventDao().update(event);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.EVENT_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.EVENTS);
    }

    public void deleteRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getEventDao().delete(id);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.EVENT_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.EVENTS);
    }
}
