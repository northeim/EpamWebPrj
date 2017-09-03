package by.gsu.epamlab.model.daoimp.memory;

import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.model.beans.Event;
import by.gsu.epamlab.model.beans.Order;
import by.gsu.epamlab.model.dao.IOrderDao;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;

import java.util.*;

public class OrderDaoMemory implements IOrderDao {

    private static final Map<Integer, Order> ORDERS = new HashMap<Integer, Order>();
    private static int id = 1;

    private static int generateId() {
        return id++;
    }

    public String getAllBusyTicketByEventId(int eventId) {
        String ticketString = "[";
        for (Order order: new ArrayList<Order>(ORDERS.values())) {
            if (order.getEventId() == eventId) {
                ticketString += order.getTicketString();
                ticketString += ",";
            }
        }
        ticketString += "]";
        ticketString = ticketString.replace(",]", "]");
        return ticketString;
    }

    public String getAllSelectedTicketByUserId(int eventId, int userId) {
        String ticketString = "[";
        for (Order order: new ArrayList<Order>(ORDERS.values())) {
            if (order.getEventId() == eventId && order.getUserId() == userId) {
                ticketString += order.getTicketString();
                ticketString += ",";
            }
        }
        ticketString += "]";
        ticketString = ticketString.replace(",]", "]");
        return ticketString;
    }

    public String getAllBusyTicketByUserId(int eventId, int userId) {
        String ticketString = "[";
        for (Order order: new ArrayList<Order>(ORDERS.values())) {
            if (order.getEventId() == eventId && order.getUserId() != userId) {
                ticketString += order.getTicketString();
                ticketString += ",";
            }
        }
        ticketString += "]";
        ticketString = ticketString.replace(",]", "]");
        return ticketString;
    }

    public int insert(Order order) {
        synchronized (ORDERS) {
            order.setId(id);
            ORDERS.put(id, order);
            generateId();
        }
        return 0;
    }

    public int update(Order order) {
        if (ORDERS.containsKey(order.getId())) {
            ORDERS.put(order.getId(), order);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_ORDER_IN_DB);
        }
        return 0;
    }

    public int delete(int id) {
        if (ORDERS.containsKey(id)) {
            ORDERS.remove(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_ORDER_IN_DB);
        }
        return 0;
    }

    public List<Order> getOrderByUserId(int userId, Status status) {
        List<Order> orders = new ArrayList<Order>();
        for (Order order: new ArrayList<Order>(ORDERS.values())) {
            if (order.getUserId() == userId) {
                Event event;
                switch (status) {
                    case NEW:
                        event = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getEventDao().
                                getById(order.getEventId());
                        if (new Date().before(event.getEventDate())) {
                            orders.add(order);
                        }
                        break;
                    case OLD:
                        event = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getEventDao().
                                getById(order.getEventId());
                        if (new Date().after(event.getEventDate())) {
                            orders.add(order);
                        }
                        break;
                }
            }
        }
        return orders;
    }

    public List<Order> getAll() {
        return new ArrayList<Order>(ORDERS.values());
    }
}
