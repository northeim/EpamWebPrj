package by.gsu.epamlab.model.modelUtils;

import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.model.beans.Film;
import by.gsu.epamlab.model.factory.AbstractDaoFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

public class FilmsTable implements ITableOperation {

    public void addRecord(HttpServletRequest req) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String authorId = req.getParameter("authorId");
        String coverPathString = UploadImage(req, Integer.parseInt("1"));

        Film film = new Film(1, name, description, Integer.parseInt(authorId), coverPathString);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getFilmDao().insert(film);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.FILM_ADD_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.FILMS);
    }

    public void editRecord(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String authorId = req.getParameter("authorId");
        String coverPathString = UploadImage(req, Integer.parseInt(id));
        Film film = new Film(Integer.parseInt(id), name, description, Integer.parseInt(authorId), coverPathString);
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getFilmDao().update(film);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.FILM_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.FILMS);
    }

    public void deleteRecord(HttpServletRequest req) {
        String id = req.getParameter("id");
        AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getFilmDao().delete(Integer.parseInt(id));
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.FILM_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.FILMS);
    }

    private String UploadImage(HttpServletRequest req, int id) {
        Part coverPath = null;
        String pathImg = "";
        try {
            coverPath = req.getPart("coverPath");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        if (!"".equals(coverPath.getSubmittedFileName())) {
            String imgServerRepo = (String)req.getServletContext().getAttribute(Constant.Param.IMG_SERVER_REPO);
            String fileName = imgServerRepo + coverPath.getSubmittedFileName();
            try(
                FileOutputStream fileOutputStream = new FileOutputStream(new File(fileName));
                InputStream inputStream = coverPath.getInputStream()
            ) {
                byte [] bytes = new byte[1024*8];
                int read = 0;
                while((read = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, read);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
            pathImg = coverPath.getSubmittedFileName();
        } else {
            if (!"".equals(req.getParameter("id"))) {
                pathImg = AbstractDaoFactory.getDaoFactory(Constant.FACTORY).getFilmDao().getById(id).getCoverPath();
            }
        }
        return pathImg;
    }



}
