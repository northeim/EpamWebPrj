package by.gsu.epamlab.model.modelUtils;

import by.gsu.epamlab.model.beans.Author;
import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;

import javax.servlet.http.HttpServletRequest;

public class AuthorsTable implements ITableOperation {

    public void addRecord(HttpServletRequest req) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Author author = new Author(1, name, description);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getAuthorDao().insert(author);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.AUTHOR_ADD_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.AUTHORS);
    }

    public void editRecord(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Author author = new Author(Integer.parseInt(id), name, description);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getAuthorDao().update(author);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.AUTHOR_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.AUTHORS);
    }

    public void deleteRecord(HttpServletRequest req) {
        String id = req.getParameter("id");
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getAuthorDao().delete(Integer.parseInt(id));
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.AUTHOR_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.AUTHORS);
    }

}
