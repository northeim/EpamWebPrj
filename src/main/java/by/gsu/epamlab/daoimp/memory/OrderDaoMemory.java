package by.gsu.epamlab.daoimp.memory;

import by.gsu.epamlab.beans.Order;
import by.gsu.epamlab.dao.IOrderDao;

import java.util.List;

public class OrderDaoMemory implements IOrderDao {
    public String getAllBusyTicketByEventId(int eventId) {
        return null;
    }

    public String getAllSelectedTicketByUserId(int eventId, int userId) {
        return null;
    }

    public String getAllBusyTicketByUserId(int eventId, int userId) {
        return null;
    }

    public int insert(Order order) {
        return 0;
    }

    public int update(Order order) {
        return 0;
    }

    public int delete(int id) {
        return 0;
    }

    public List<Order> getOrderByUserId(int userId, Status status) {
        return null;
    }

    public List<Order> getAll() {
        return null;
    }
}
