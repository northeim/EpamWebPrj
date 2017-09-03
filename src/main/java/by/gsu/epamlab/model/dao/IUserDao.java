package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.model.beans.User;

import java.util.List;

public interface IUserDao {

    User getUser(String login, String password);

    public int insert(User user);
    public int delete(int id);
    public int update(User user);
    public List<User> getAll();

}
