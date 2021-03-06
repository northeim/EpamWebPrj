package by.gsu.epamlab.model.daoimp.database;

import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.model.beans.Film;
import by.gsu.epamlab.model.connection.ConnectionManager;
import by.gsu.epamlab.model.dao.IFilmDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDaoDataBase implements IFilmDao {

    private static final String SELECT_ALL = "SELECT * FROM films ORDER BY name ASC";
    private static final String INSERT_QUERY = "INSERT INTO films (name, description, authorId, coverPath) VALUE(?, ?, ?, ?)";
    private static final String DELETE_BY_ID = "DELETE FROM films WHERE id = ?";
    private static final String UPDATE_QUERY = "UPDATE films SET name = ?, description = ?, authorId = ?, coverPath = ? WHERE id = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM films WHERE id = ?";

    public List<Film> getAll() {
        List<Film> filmList = new ArrayList<Film>();
        try(Connection connection = ConnectionManager.getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(SELECT_ALL)) {
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                int authorId = rs.getInt(4);
                String coverPath = rs.getString(5);
                Film film = new Film(id, name, description, authorId, coverPath);
                filmList.add(film);
            }
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.
                    Message.ERROR_FILM_REQUEST);
        }
        return filmList;
    }

    public int insert(Film film) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement prstm = connection.prepareStatement(INSERT_QUERY)) {
            prstm.setString(1, film.getName());
            prstm.setString(2, film.getDescription());
            prstm.setInt(3, film.getAuthorId());
            prstm.setString(4, film.getCoverPath());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.
                    Message.ERROR_FILM_REQUEST);
        }
        return 0;
    }

    public int delete(int id) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement prstm = connection.prepareStatement(DELETE_BY_ID)) {
            prstm.setInt(1, id);
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.
                    Message.ERROR_FILM_REQUEST);
        }
        return 0;
    }

    public int update(Film film) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement prstm = connection.prepareStatement(UPDATE_QUERY)) {
            prstm.setString(1, film.getName());
            prstm.setString(2, film.getDescription());
            prstm.setInt(3, film.getAuthorId());
            prstm.setString(4, film.getCoverPath());
            prstm.setInt(5, film.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.
                    Message.ERROR_FILM_REQUEST);
        }
        return 0;
    }

    public Film getById(int id) {
        Film film = null;
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement prstm = connection.prepareStatement(SELECT_BY_ID)) {
            prstm.setInt(1, id);
            try (ResultSet rs = prstm.executeQuery()){
                if (rs.next()) {
                    int idx = rs.getInt(1);
                    String name = rs.getString(2);
                    String description = rs.getString(3);
                    int authorId = rs.getInt(4);
                    String coverPath = rs.getString(5);
                    film = new Film(idx, name, description, authorId, coverPath);
                } else {
                    throw new DataBaseException(ExceptionConstant.Message.ERROR_FILM_REQUEST);
                }
            }
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message.ERROR_FILM_REQUEST);
        }
        return film;
    }

}
