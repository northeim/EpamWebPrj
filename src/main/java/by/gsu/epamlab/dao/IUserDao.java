package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {

    User getUser(String login, String password) throws SQLException;

    public int insert(User user) throws SQLException;
    public int delete(int id);
    public int update(User user);
    public List<User> getAll() throws SQLException;

}
