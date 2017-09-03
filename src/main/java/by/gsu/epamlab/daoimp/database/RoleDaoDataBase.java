package by.gsu.epamlab.daoimp.database;

import by.gsu.epamlab.ConnectionManager;
import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.dao.IRoleDao;
import by.gsu.epamlab.exeptions.DataBaseExeption;
import by.gsu.epamlab.exeptions.ExeptionConstant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoDataBase implements IRoleDao {

    private static final String SELECT_BY_ID = "SELECT id, description, levelAccess FROM role WHERE id = ?";
    private static final String SELECT_ALL = "SELECT id, description, levelAccess FROM role ORDER BY levelAccess ASC";
    private static final String SELECT_BY_DESCRIPTION = "SELECT id, " +
            "description, " +
            "levelAccess FROM role WHERE description = ?";

    private static final String INSERT_QUERY = "INSERT INTO role (description, levelAccess) VALUE(?, ?)";
    private static final String UPDATE_QUERY = "UPDATE role SET description = ?, levelAccess = ? WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM role WHERE id = ?";

    public int insert(Role role) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(INSERT_QUERY);
            prst.setString(1, role.getDescription());
            prst.setInt(2, role.getLevelAccess());
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_ROLE_REQUEST);
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

    public int update(Role role) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(UPDATE_QUERY);
            prst.setString(1, role.getDescription());
            prst.setInt(2, role.getLevelAccess());
            prst.setInt(3, role.getId());
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_ROLE_REQUEST);
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

    public int delete(int id) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prst = null;
        try {
            prst = connection.prepareStatement(DELETE_QUERY);
            prst.setInt(1, id);
            prst.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_ROLE_REQUEST);
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

    public Role getById(int id) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prst = null;
        ResultSet rs = null;
        Role role = null;

        try {
            prst = connection.prepareStatement(SELECT_BY_ID);
            prst.setInt(1, id);
            rs = prst.executeQuery();
            if  (rs.next()) {
                int idRole = rs.getInt(1);
                String description = rs.getString(2);
                int levelAccess = rs.getInt(3);
                role = new Role(idRole, description, levelAccess);
            }
            else {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_ROLE_REQUEST);
            }
        } catch (SQLException e) {
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_ROLE_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(prst);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_CLOSE_CONNECTION);
            }
        }
        return role;
    }

    public Role getByDescription(String desc) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prst = null;
        ResultSet rs = null;
        Role role = null;

        try {
            prst = connection.prepareStatement(SELECT_BY_DESCRIPTION);
            prst.setString(1, desc);

            rs = prst.executeQuery();

            if  (rs.next()) {
                int idRole = rs.getInt(1);
                String description = rs.getString(2);
                int levelAccess = rs.getInt(3);
                role = new Role(idRole, description, levelAccess);
            }
            else {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_ROLE_REQUEST);
            }
        } catch (SQLException e) {
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_ROLE_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(prst);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_CLOSE_CONNECTION);
            }
        }
        return role;
    }

    public List<Role> getAll() {
        List<Role> list = new ArrayList<Role>();
        Connection connection = ConnectionManager.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(SELECT_ALL);
            while(rs.next()) {
                int id = rs.getInt(1);
                String description = rs.getString(2);
                int levelAccess = rs.getInt(3);
                list.add(new Role(id, description, levelAccess));
            }
        } catch (SQLException e) {
            throw new DataBaseExeption(ExeptionConstant.Message.ERROR_ROLE_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(st);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseExeption(ExeptionConstant.Message.ERROR_CLOSE_CONNECTION);
            }
        }
        return list;
    }
}
