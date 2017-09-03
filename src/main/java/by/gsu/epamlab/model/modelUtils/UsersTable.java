package by.gsu.epamlab.model.modelUtils;

import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.model.beans.Role;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;

import javax.servlet.http.HttpServletRequest;

public class UsersTable implements ITableOperation {

    public void addRecord(HttpServletRequest req) {
        String nickName = req.getParameter("nickName");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String password = new Integer(req.getParameter("password").hashCode()).toString();
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        Role roleObj = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getRoleDao().getById(roleId);
        User userObj = new User(1, nickName, firstName, secondName, email, password, roleObj);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getUserDao().insert(userObj);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.USER_ADD_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.USERS);
    }

    public void editRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        String nickName = req.getParameter("nickName");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        Role roleObj = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getRoleDao().getById(roleId);
        User userObj = new User( id, nickName, firstName, secondName, email, password, roleObj);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getUserDao().update(userObj);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.USER_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.USERS);
    }

    public void deleteRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getUserDao().delete(id);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.USER_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.USERS);
    }
}
