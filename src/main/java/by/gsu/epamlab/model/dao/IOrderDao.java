package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.model.beans.Order;

import java.util.List;

public interface IOrderDao {

    enum Status {
        OLD,
        NEW
    }

    String getAllBusyTicketByEventId(int eventId);
    String getAllSelectedTicketByUserId(int eventId, int userId);
    String getAllBusyTicketByUserId(int eventId, int userId);
    int insert(Order order);
    int update(Order order);
    int delete(int id);
    List<Order> getOrderByUserId(int userId, Status status);
    List<Order> getAll();

}
