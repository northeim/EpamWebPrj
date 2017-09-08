package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.model.beans.User;

import java.util.List;

public interface IUserDao {

    User getUser(String login, String password);
    List<User> getAll();
    int insert(User user);
    int update(User user);
    int delete(int id);

}
