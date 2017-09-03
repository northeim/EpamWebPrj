package by.gsu.epamlab.daoimp.database;

import by.gsu.epamlab.ConnectionManager;
import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.dao.IUserDao;
import by.gsu.epamlab.exeptions.DataBaseExeption;
import by.gsu.epamlab.exeptions.ExeptionConstant;
import by.gsu.epamlab.exeptions.ValidationExeption;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoDataBase implements IUserDao {

    private static final String INSERT_QUERY = "INSERT INTO users" +
            "(nickName, firstName, secondName," +
            "email, password, roleId) VALUE(?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_QUERY = "SELECT users.id, " +
            "users.nickName, users.firstName, users.secondName, users.email, " +
            "users.password, role.id, role.description, role.levelAccess FROM users, " +
            "role WHERE users.roleId = role.id ORDER BY nickName ASC";
    private static final String SELECT_BY_LOGIN = "SELECT id, nickName, " +
            "firstName, secondName, email, password, roleId FROM users WHERE nickName = ?";

    private static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";

    private static final String UPDATE_USER = "UPDATE users SET nickName = ?," +
            "firstName = ?, secondName = ?, email = ?, password = ?, roleId = ? WHERE id = ?";

    // Get User By Login;
    public User getUser(String login, String password) {

        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prst = null;
        ResultSet rs = null;
        User user = null;

        try {
            prst = connection.prepareStatement(SELECT_BY_LOGIN);
            prst.setString(1, login);
            rs = prst.executeQuery();
            if (rs.next()) {
                if (password.equals(rs.getString(6))) {
                    Role role = new RoleDaoDataBase().getById(rs.getInt(7));
                    user = new User(rs.getInt(1),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            role);
                }
                else {
                    // Invalid password;
                    throw new ValidationExeption(ExeptionConstant.Message.ERROR_PASSWORD);
                }
            }
            else {
                // No user in DB;
                throw new ValidationExeption(ExeptionConstant.Message.ERROR_NO_USER_IN_DB);
            }
        } catch (SQLException e) {
            // Error user request;
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_USER_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(prst);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_CLOSE_CONNECTION);
            }
        }
        return user;
    }

    // Get All Users From DataBase;
    public List<User> getAll() {
        List<User> list = new ArrayList<User>();
        Connection connection = ConnectionManager.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SELECT_ALL_QUERY);
            while (rs.next()) {
                Role role = new Role(rs.getInt(7),
                        rs.getString(8),
                        rs.getInt(9));
                User user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        role);
                list.add(user);
            }

        } catch (SQLException e) {
            // Error user request;
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_USER_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(stm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_CLOSE_CONNECTION);
            }
        }
        return list;
    }

    public int insert(User user) {

        Connection connection = ConnectionManager.getConnection();
        Statement stm = null;
        PreparedStatement prst = null;
        ResultSet rs = null;

        try {
            prst = connection.prepareStatement(SELECT_BY_LOGIN);
            prst.setString(1, user.getNickName());
            rs = prst.executeQuery();

            if (!rs.next()) {
                Role role = new RoleDaoDataBase().getByDescription(user.getRole()
                        .getDescription());
                prst = connection.prepareStatement(INSERT_QUERY);
                prst.setString(1, user.getNickName());
                prst.setString(2, user.getFirstName());
                prst.setString(3, user.getSecondName());
                prst.setString(4, user.getEmail());
                prst.setString(5, user.getPassword());
                prst.setInt(6, role.getId());
                prst.executeUpdate();
            }
            else {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_DUPLICATE_LOGIN);
            }
        } catch (SQLException e) {
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_USER_REQUEST);
        } finally {
            try {
                ConnectionManager.close(prst);
                ConnectionManager.close(rs);
                ConnectionManager.close(stm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_CLOSE_CONNECTION);
            }
        }
        return 0;
    }

    public int delete(int id) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(DELETE_BY_ID);
            prst.setInt(1, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_USER_DELETE);
        } finally {
            try {
                ConnectionManager.close(prst);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_CLOSE_CONNECTION);
            }
        }
        return 0;
    }

    public int update(User user) {
        Connection connection = ConnectionManager.getConnection();
        Statement stm = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = connection.prepareStatement(SELECT_BY_LOGIN);
            pstm.setString(1, user.getNickName());
            rs = pstm.executeQuery();
            if (!rs.next()) {
                    Role role = new RoleDaoDataBase().getByDescription(user.getRole()
                            .getDescription());
                    pstm = connection.prepareStatement(UPDATE_USER);
                    pstm.setString(1, user.getNickName());
                    pstm.setString(2, user.getFirstName());
                    pstm.setString(3, user.getSecondName());
                    pstm.setString(4, user.getEmail());
                    pstm.setString(5, user.getPassword());
                    pstm.setInt(6, role.getId());
                    pstm.setInt(7, user.getId());
                    pstm.executeUpdate();
            } else {
                int id = rs.getInt(1);
                if (id == user.getId()) {
                    Role role = new RoleDaoDataBase().getByDescription(user.getRole()
                            .getDescription());
                    pstm = connection.prepareStatement(UPDATE_USER);
                    pstm.setString(1, user.getNickName());
                    pstm.setString(2, user.getFirstName());
                    pstm.setString(3, user.getSecondName());
                    pstm.setString(4, user.getEmail());
                    pstm.setString(5, user.getPassword());
                    pstm.setInt(6, role.getId());
                    pstm.setInt(7, user.getId());
                    pstm.executeUpdate();
                } else {
                    throw new DataBaseExeption(ExeptionConstant.Message.ERROR_DUPLICATE_LOGIN);
                }
            }
        } catch (SQLException e) {
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_USER_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(pstm);
                ConnectionManager.close(stm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseExeption(ExeptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return 0;
    }


}
