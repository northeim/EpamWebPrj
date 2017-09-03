package by.gsu.epamlab.daoimp.memory;

import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IUserDao;

import java.sql.SQLException;
import java.util.List;

public class UserDaoMemory implements IUserDao {
    public User getUser(String login, String password) throws SQLException {
        return null;
    }

    public int insert(User user) throws SQLException {
        return 0;
    }

    public int delete(int id) {
        return 0;
    }

    public int update(User user) {
        return 0;
    }

    public List<User> getAll() throws SQLException {
        return null;
    }
}
