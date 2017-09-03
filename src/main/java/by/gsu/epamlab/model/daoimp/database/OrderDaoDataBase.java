package by.gsu.epamlab.model.daoimp.database;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.model.beans.Order;
import by.gsu.epamlab.model.connection.ConnectionManager;
import by.gsu.epamlab.model.dao.IOrderDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDaoDataBase implements IOrderDao {

    private static final String SELECT_BUSY_TICKET_BY_ID = "SELECT id, userId, eventId, ticket, date FROM orders " +
            "WHERE eventId = ?";
    private static final String SELECT_TICKET_BY_USER_ID = "SELECT id, userId, eventId, ticket, date FROM orders " +
            "WHERE eventId = ? AND userId = ?";
    private static final String SELECT_TICKET_BY_ID_WO_USER = "SELECT id, userId, eventId, ticket, date FROM orders " +
            "WHERE eventId = ? AND userId != ?";
    private static final String SELECT_ALL_ACTUALY_ARDER_BY_USER_ID = "SELECT orders.id, orders.userId, " +
            "orders.eventId, orders.ticket, orders.date, events.name, events.date FROM orders, events WHERE orders.userId = ? AND" +
            " orders.eventId = events.id";
    private static final String SELECT_QUERY = "SELECT * FROM orders ORDER BY date ASC";

    private static final String INSERT_QUERY = "INSERT INTO orders (userId, eventId, ticket, date) VALUE (?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE orders SET userId = ?, eventId = ?, ticket = ?, date = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM orders WHERE id = ?";

    public int update(Order order) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(UPDATE_QUERY);
            prstm.setInt(1, order.getUserId());
            prstm.setInt(2, order.getEventId());
            prstm.setString(3, order.getTicketString());
            prstm.setTimestamp(4, new Timestamp(order.getOrderDate().getTime()));
            prstm.setInt(5, order.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_ORDERS_REQUEST);
        } finally {
            try {
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message.ERROR_CLOSE_CONNECTION);
            }
        }
        return 0;
    }

    public int delete(int id) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(DELETE_QUERY);
            prstm.setInt(1, id);
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_ORDERS_REQUEST);
        } finally {
            try {
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message.ERROR_CLOSE_CONNECTION);
            }
        }
        return 0;
    }

    public int insert(Order order) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(INSERT_QUERY);
            prstm.setInt(1, order.getUserId());
            prstm.setInt(2, order.getEventId());
            prstm.setString(3, order.getTicketString());
            //System.out.println(new Timestamp(order.getOrderDate().getTime()));
            prstm.setTimestamp(4, new Timestamp(order.getOrderDate().getTime()));
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message
                    .ERROR_ORDERS_ADD);
        } finally {
            try {
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return 0;
    }

    public List<Order> getOrderByUserId(int userId, Status status) {
        List<Order> list = new ArrayList<Order>();
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = connection.prepareStatement(SELECT_ALL_ACTUALY_ARDER_BY_USER_ID);
            prstm.setInt(1, userId);
            rs = prstm.executeQuery();
            while(rs.next()) {
                int orderId = rs.getInt(1);
                int orderUserId = rs.getInt(2);
                int orderEventId = rs.getInt(3);
                String ticket = rs.getString(4);
                Date orderDate = new Date(rs.getTimestamp(5).getTime());
                String eventName = rs.getString(6);
                Date eventDate = new Date(rs.getTimestamp(7).getTime());
                Order order = null;
                switch (status) {
                    case OLD:
                        if (new Date().after(eventDate)) {
                            order = new Order(orderId, orderUserId, orderEventId, orderDate, ticket);
                            list.add(order);
                        }
                        break;
                    case NEW:
                        if (new Date().before(eventDate)) {
                            order = new Order(orderId, orderUserId, orderEventId, orderDate, ticket);
                            list.add(order);
                        }
                        break;
                }
            }

        } catch (SQLException e) {
            throw  new DataBaseException(ExceptionConstant.Message.ERROR_ORDERS_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return list;
    }

    public List<Order> getAll() {
            List<Order> list = new ArrayList<Order>();
            Connection connection = ConnectionManager.getConnection();
            Statement stm = null;
            ResultSet rs = null;
            try {
                stm = connection.createStatement();
                rs = stm.executeQuery(SELECT_QUERY);
                while(rs.next()) {
                    int orderId = rs.getInt(1);
                    int orderUserId = rs.getInt(2);
                    int orderEventId = rs.getInt(3);
                    String ticket = rs.getString(4);
                    Date orderDate = new Date(rs.getTimestamp(5).getTime());
                    Order order = new Order(orderId, orderUserId, orderEventId, orderDate, ticket);
                    System.out.println(order);
                    list.add(order);
                }
            } catch (SQLException e) {
                throw  new DataBaseException(ExceptionConstant.Message.ERROR_ORDERS_REQUEST);
            } finally {
                try {
                    ConnectionManager.close(rs);
                    ConnectionManager.close(stm);
                    ConnectionManager.close(connection);
                } catch (SQLException e) {
                    throw new DataBaseException(ExceptionConstant.Message
                            .ERROR_CLOSE_CONNECTION);
                }
            }
            return list;
    }

    public String getAllBusyTicketByEventId(int eventId) {
        String ticketString = "[";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = connection.prepareStatement(SELECT_BUSY_TICKET_BY_ID);
            prstm.setInt(1, eventId);
            rs = prstm.executeQuery();
            while(rs.next()) {
                ticketString += rs.getString(4);
                ticketString += ",";
            }
        } catch (SQLException e) {
            throw  new DataBaseException(ExceptionConstant.Message.ERROR_ORDERS_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        ticketString += "]";
        ticketString = ticketString.replace(",]", "]");
        return ticketString;
    }

    public String getAllSelectedTicketByUserId(int eventId, int userId) {
        String ticketString = "[";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = connection.prepareStatement(SELECT_TICKET_BY_USER_ID);
            prstm.setInt(1, eventId);
            prstm.setInt(2, userId);
            rs = prstm.executeQuery();
            while(rs.next()) {
                ticketString += rs.getString(4);
                ticketString += ",";
            }
        } catch (SQLException e) {
            throw  new DataBaseException(ExceptionConstant.Message.ERROR_ORDERS_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        ticketString += "]";
        ticketString = ticketString.replace(",]", "]");
        return ticketString;
    }

    public String getAllBusyTicketByUserId(int eventId, int userId) {
        String ticketString = "[";
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = connection.prepareStatement(SELECT_TICKET_BY_ID_WO_USER);
            prstm.setInt(1, eventId);
            prstm.setInt(2, userId);
            rs = prstm.executeQuery();
            while(rs.next()) {
                ticketString += rs.getString(4);
                ticketString += ",";
            }
        } catch (SQLException e) {
            throw  new DataBaseException(ExceptionConstant.Message.ERROR_ORDERS_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        ticketString += "]";
        ticketString = ticketString.replace(",]", "]");
        return ticketString;
    }

}
