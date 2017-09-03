package by.gsu.epamlab.model.daoimp.memory;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.exeptions.ValidationException;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.dao.IUserDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoMemory implements IUserDao {

    private static final Map<Integer, User> USERS = new HashMap<Integer, User>();
    private static int id = 1;

    static {
        User user = new User(id, "admin", "Admin", "Admin", "Admin@gmail.com",
                "admin", new Role(1, "Admin", 10));
        USERS.put(id, user);
        generateId();
        user = new User(id, "User", "User", "User", "User@gmail.com",
                "User", new Role(2, "User", 0));
        USERS.put(id, user);
        generateId();
    }

    private static int generateId() {
        return id++;
    }

    public User getUser(String login, String password) {
        User user = null;
        for (Map.Entry<Integer, User> entry: USERS.entrySet()) {
            if (entry.getValue().getNickName().equals(login)) {
                if (entry.getValue().getPassword().equals(password)) {
                    user = entry.getValue();
                } else {
                    throw new ValidationException(ExceptionConstant.Message.ERROR_PASSWORD);
                }
            }
        }
        if (user == null) {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_USER_IN_DB);
        }
        return user;
    }

    public int insert(User user) {
        if (!USERS.containsValue(user)) {
            synchronized (USERS) {
                user.setId(id);
                USERS.put(id, user);
                generateId();
            }
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_DUPLICATE_LOGIN);
        }
        return 0;
    }

    public int delete(int id) {
        if (USERS.containsKey(id)) {
            USERS.remove(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_USER_IN_DB);
        }
        return 0;
    }

    public int update(User user) {
        if (USERS.containsKey(user.getId())) {
            USERS.put(user.getId(), user);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_USER_IN_DB);
        }
        return 0;
    }

    public List<User> getAll() {
        return new ArrayList<User>(USERS.values());
    }
}
