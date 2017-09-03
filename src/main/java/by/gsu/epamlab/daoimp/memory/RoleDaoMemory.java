package by.gsu.epamlab.daoimp.memory;

import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.dao.IRoleDao;

import java.sql.SQLException;
import java.util.List;

public class RoleDaoMemory implements IRoleDao {
    public Role getById(int id) throws SQLException {
        return null;
    }

    public List<Role> getAll() throws SQLException {
        return null;
    }

    public Role getByDescription(String desc) {
        return null;
    }
}
