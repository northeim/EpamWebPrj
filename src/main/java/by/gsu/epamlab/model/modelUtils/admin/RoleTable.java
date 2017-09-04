package by.gsu.epamlab.model.modelUtils.admin;

import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;

import javax.servlet.http.HttpServletRequest;

public class RoleTable implements ITableOperation {
    public void addRecord(HttpServletRequest req) {
        String description = req.getParameter("description");
        int levelAccess = Integer.parseInt(req.getParameter("levelAccess"));
        Role role = new Role(1, description, levelAccess);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getRoleDao().insert(role);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ROLE_ADD_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ROLE);
    }

    public void editRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String description = req.getParameter("description");
        int levelAccess = Integer.parseInt(req.getParameter("levelAccess"));
        Role role = new Role(id, description, levelAccess);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getRoleDao().update(role);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ROLE_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ROLE);
    }

    public void deleteRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getRoleDao().delete(id);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.ROLE_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.ROLE);
    }
}
