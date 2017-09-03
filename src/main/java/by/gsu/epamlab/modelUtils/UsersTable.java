package by.gsu.epamlab.modelUtils;

import by.gsu.epamlab.beans.Role;
import by.gsu.epamlab.beans.User;
import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.daoimp.RoleDaoImp;
import by.gsu.epamlab.daoimp.UserDaoImp;

import javax.servlet.http.HttpServletRequest;

public class UsersTable implements ITableOperation {

    public void addRecord(HttpServletRequest req) {
        String nickName = req.getParameter("nickName");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        int roleId = Integer.parseInt(req.getParameter("roleId"));
        Role roleObj = new RoleDaoImp().getById(roleId);
        User userObj = new User(1, nickName, firstName, secondName, email, password, roleObj);
        new UserDaoImp().insert(userObj);
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
        Role roleObj = new RoleDaoImp().getById(roleId);
        User userObj = new User( id, nickName, firstName, secondName, email, password, roleObj);
        new UserDaoImp().update(userObj);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.USER_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.USERS);
    }

    public void deleteRecord(HttpServletRequest req) {
        int id = Integer.parseInt(req.getParameter("id"));
        new UserDaoImp().delete(id);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.USER_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.USERS);
    }
}
