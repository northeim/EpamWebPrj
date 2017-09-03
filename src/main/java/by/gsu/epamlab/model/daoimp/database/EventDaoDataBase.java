package by.gsu.epamlab.model.daoimp.database;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.model.beans.Event;
import by.gsu.epamlab.model.connection.ConnectionManager;
import by.gsu.epamlab.model.dao.IEventDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDaoDataBase implements IEventDao {

    private static final String SELECT_ALL = "SELECT * FROM events ORDER BY date ASC";
    private static final String SELECT_EVENT_BY_ID = "SELECT * FROM events " +
            "WHERE id = ?";
    private static final String INSERT_QUERY = "INSERT INTO events (name, date, filmId) VALUE(?,?,?)";
    private static final String UPDATE_QUERY = "UPDATE events SET name = ?, date = ?, filmId = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM events WHERE id = ?";

    public int insert(Event event) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(INSERT_QUERY);
            prstm.setString(1, event.getEventName());
            prstm.setTimestamp(2, new Timestamp(event.getEventDate().getTime()));
            prstm.setInt(3, event.getFilmId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw  new DataBaseException(ExceptionConstant.Message.ERROR_EVENT_REQUEST);
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
            throw  new DataBaseException(ExceptionConstant.Message.ERROR_EVENT_REQUEST);
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

    public int update(Event event) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(UPDATE_QUERY);
            prstm.setString(1, event.getEventName());
            prstm.setTimestamp(2, new Timestamp(event.getEventDate().getTime()));
            prstm.setInt(3, event.getFilmId());
            prstm.setInt(4, event.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw  new DataBaseException(ExceptionConstant.Message.ERROR_EVENT_REQUEST);
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


    public List<Event> getAll() {
        List<Event> events = new ArrayList<Event>();
        Connection connection = ConnectionManager.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SELECT_ALL);
            while(rs.next()) {
                int id = rs.getInt(1);
                String eventName = rs.getString(2);
                Date eventDate = new Date(rs.getTimestamp(3).getTime());
                int filmId = rs.getInt(4);

                Date curDate = new Date();

                if (curDate.before(eventDate)) {
                    Event event = new Event(id, eventName, eventDate, filmId);
                    events.add(event);
                    System.out.println(event);
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_EVENT_REQUEST);
        }
        finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(stm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return events;
    }

    public List<Event> getAllWithOld() {
        List<Event> events = new ArrayList<Event>();
        Connection connection = ConnectionManager.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SELECT_ALL);
            while(rs.next()) {
                int id = rs.getInt(1);
                String eventName = rs.getString(2);
                Date eventDate = new Date(rs.getTimestamp(3).getTime());
                int filmId = rs.getInt(4);

                Event event = new Event(id, eventName, eventDate, filmId);
                events.add(event);
            }
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_EVENT_REQUEST);
        }
        finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(stm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return events;
    }

    public Event getById(int id) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        Event event = null;
        try {
            prstm = connection.prepareStatement(SELECT_EVENT_BY_ID);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();
            if (rs.next()) {
                int idEvent = rs.getInt(1);
                String eventName = rs.getString(2);
                Date eventDate = new Date(rs.getTimestamp(3).getTime());
                int filmId = rs.getInt(4);
                event = new Event(idEvent, eventName, eventDate, filmId);
            } else {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_NO_EVENT_IN_DB);
            }
        } catch (SQLException e) {
            throw  new DataBaseException(ExceptionConstant.Message.ERROR_EVENT_REQUEST);
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
        return event;
    }
}
