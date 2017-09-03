package by.gsu.epamlab.modelUtils;

import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.daoimp.database.RoleDaoDataBase;

import javax.servlet.http.HttpServletRequest;

public class RoleTable implements ITableOperation {
    public void addRecord(HttpServletRequest req) {
        String description = req.getParameter("description");
        int levelAccess = Integer.parseInt(req.getParameter("levelAccess"));
        Role role = new Role(1, description, levelAccess);
        new RoleDaoDataBase().insert(role);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ROLE_ADD_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ROLE);
    }

    public void editRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String description = req.getParameter("description");
        int levelAccess = Integer.parseInt(req.getParameter("levelAccess"));
        Role role = new Role(id, description, levelAccess);
        new RoleDaoDataBase().update(role);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ROLE_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ROLE);
    }

    public void deleteRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        new RoleDaoDataBase().delete(id);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ROLE_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ROLE);
    }
}
