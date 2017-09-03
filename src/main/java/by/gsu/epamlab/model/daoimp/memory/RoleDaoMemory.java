package by.gsu.epamlab.model.daoimp.memory;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.dao.IRoleDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleDaoMemory implements IRoleDao {

    private static final Map<Integer, Role> ROLES = new HashMap<Integer, Role>();
    private static int id = 1;

    static {
        Role role = new Role(id, "Admin", 10);
        ROLES.put(id, role);
        generateId();
        role = new Role(id, "User", 0);
        ROLES.put(id, role);
        generateId();
    }

    private static int generateId() {
        return id++;
    }

    public Role getById(int id) {
        Role role = null;
        if (ROLES.containsKey(id)) {
            role = ROLES.get(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_ROLE_IN_DB);
        }
        return role;
    }

    public List<Role> getAll() {
        return new ArrayList<Role>(ROLES.values());
    }

    public Role getByDescription(String desc) {
        Role role = null;
        for (Map.Entry<Integer, Role> entry: ROLES.entrySet()) {
            if (entry.getValue().getDescription().equals(desc)) {
                role = entry.getValue();
            }
        }
        if (role == null) {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_ROLE_IN_DB);
        }
        return role;
    }

    public int insert(Role role) {
        synchronized (ROLES) {
            role.setId(id);
            ROLES.put(id, role);
            generateId();
        }
        return 0;
    }

    public int update(Role role) {
        if (ROLES.containsKey(role.getId())) {
            ROLES.put(role.getId(), role);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_ROLE_IN_DB);
        }
        return 0;
    }

    public int delete(int id) {
        if (ROLES.containsKey(id)) {
            ROLES.remove(id);
        } else {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_NO_ROLE_IN_DB);
        }
        return 0;
    }
}
