package by.gsu.epamlab.dao;

import by.gsu.epamlab.beans.Role;

import java.sql.SQLException;
import java.util.List;

public interface IRoleDao {

    Role getById(int id) throws SQLException;
    List<Role> getAll() throws SQLException;
    Role getByDescription(String desc);
}
