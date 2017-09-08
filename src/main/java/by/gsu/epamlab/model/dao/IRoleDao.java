package by.gsu.epamlab.model.dao;

import by.gsu.epamlab.model.beans.Role;

import java.util.List;

public interface IRoleDao {

    Role getById(int id);
    List<Role> getAll();
    Role getByDescription(String desc);
    int insert(Role role);
    int update(Role role);
    int delete(int id);

}
