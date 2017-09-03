package by.gsu.epamlab.modelUtils;

import by.gsu.epamlab.beans.Film;
import by.gsu.epamlab.controllers.Constant;
import by.gsu.epamlab.daoimp.database.FilmDaoDataBase;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;

// todo- Сделать красивое решение по работе с файлами;

public class FilmsTable implements ITableOperation {
    public void addRecord(HttpServletRequest req) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String authorId = req.getParameter("authorId");
        Part coverPath = null;
        try {
            coverPath = req.getPart("coverPath");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        String fileName = "g:\\EPAM\\testWeb\\target\\epamLabWeb\\resourses\\img\\" + coverPath.getSubmittedFileName();
        System.out.println();
        FileOutputStream fileOutputStream = null;
        InputStream inputStream = null;
        try {
            fileOutputStream = new FileOutputStream(new File(fileName));
            inputStream = coverPath.getInputStream();
            byte [] bytes = new byte[1024*8];
            int read = 0;
            while((read = inputStream.read(bytes)) != -1) {
                fileOutputStream.write(bytes, 0, read);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null)
                    fileOutputStream.close();
                if (inputStream != null)
                    inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        Film film = new Film(1, name, description, Integer.parseInt(authorId), coverPath.getSubmittedFileName());
        new FilmDaoDataBase().insert(film);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.FILM_ADD_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.FILMS);
    }

    public void editRecord(HttpServletRequest req) {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String authorId = req.getParameter("authorId");
        String coverPathString = null;
        Part coverPath = null;
        try {
            coverPath = req.getPart("coverPath");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }

        if (!coverPath.getSubmittedFileName().equals("")) {
            String fileName = "g:\\EPAM\\testWeb\\target\\epamLabWeb\\resourses\\img\\" + coverPath.getSubmittedFileName();
            coverPathString = coverPath.getSubmittedFileName();
            System.out.println();
            FileOutputStream fileOutputStream = null;
            InputStream inputStream = null;
            try {
                fileOutputStream = new FileOutputStream(new File(fileName));
                inputStream = coverPath.getInputStream();
                byte [] bytes = new byte[1024*8];
                int read = 0;
                while((read = inputStream.read(bytes)) != -1) {
                    fileOutputStream.write(bytes, 0, read);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileOutputStream != null)
                        fileOutputStream.close();
                    if (inputStream != null)
                        inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            Film film = new FilmDaoDataBase().getById(Integer.parseInt(id));
            coverPathString = film.getCoverPath();
        }
        Film film = new Film(Integer.parseInt(id), name, description, Integer.parseInt(authorId), coverPathString);
        new FilmDaoDataBase().update(film);
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.FILM_EDIT_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.FILMS);
    }

    public void deleteRecord(HttpServletRequest req) {
        String id = req.getParameter("id");
        new FilmDaoDataBase().delete(Integer.parseInt(id));
        req.setAttribute(Constant.Fields.USER_TABLE_STATUS, Constant.Message.FILM_DELETE_SUCCEFULL);
        req.setAttribute(Constant.Fields.ADMIN_UL_ID, Constant.AdminLiId.FILMS);
    }



}
