package by.gsu.epamlab.modelUtils;

import by.gsu.epamlab.beans.Author;
import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.daoimp.AuthorDaoImp;

import javax.servlet.http.HttpServletRequest;

public class AuthorsTable implements ITableOperation {

    public void addRecord(HttpServletRequest req) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Author author = new Author(1, name, description);
        new AuthorDaoImp().insert(author);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.AUTHOR_ADD_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.AUTHORS);
    }

    public void editRecord(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Author author = new Author(Integer.parseInt(id), name, description);
        new AuthorDaoImp().update(author);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.AUTHOR_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.AUTHORS);
    }

    public void deleteRecord(HttpServletRequest req) {
        String id = req.getParameter("id");
        new AuthorDaoImp().delete(Integer.parseInt(id));
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.AUTHOR_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.AUTHORS);
    }

}
