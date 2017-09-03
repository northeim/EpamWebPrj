package by.gsu.epamlab.model.daoimp.database;

import by.gsu.epamlab.model.connection.ConnectionManager;
import by.gsu.epamlab.model.beans.Author;
import by.gsu.epamlab.model.dao.IAuthorDao;
import by.gsu.epamlab.exeptions.DataBaseException;
import by.gsu.epamlab.exeptions.ExceptionConstant;
import by.gsu.epamlab.exeptions.ValidationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDaoDataBase implements IAuthorDao {

    private static final String SELECT_BY_ID = "SELECT * FROM authors WHERE " +
            "id = ?";
    private static final String SELECT_ALL = "SELECT * FROM authors ORDER BY name ASC";

    private static final String INSERT_QUERY = "INSERT INTO authors (name, description) VALUE(?, ?)";

    private static final String DELETE_BY_ID = "DELETE FROM authors WHERE id = ?";

    private static final String UPDATE_QUERY = "UPDATE authors SET name = ?," +
            "description = ? WHERE id = ?";

    public Author getById(int id) {
        Author author = null;
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = connection.prepareStatement(SELECT_BY_ID);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();
            if (rs.next()) {
                int idAuthor = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                author = new Author(idAuthor, name, description);
            } else {
                throw new ValidationException(ExceptionConstant.Message.ERROR_NO_AUTHOR_IN_DB);
            }
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message
                    .ERROR_AUTHOR_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return author;
    }

    public List<Author> getAll() {
        List<Author> list = new ArrayList<Author>();
        Connection connection = ConnectionManager.getConnection();
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = connection.createStatement();
            rs = stm.executeQuery(SELECT_ALL);
            while(rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                Author author = new Author(id, name, description);
                list.add(author);
            }
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message
                    .ERROR_AUTHOR_REQUEST);
        } finally {
            try {
                ConnectionManager.close(rs);
                ConnectionManager.close(stm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return list;
    }

    public int insert(Author author) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(INSERT_QUERY);
            prstm.setString(1, author.getAuthorName());
            prstm.setString(2, author.getDescription());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message
                    .ERROR_AUTHOR_REQUEST);
        } finally {
            try {
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return 0;
    }

    public int delete(int id) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(DELETE_BY_ID);
            prstm.setInt(1, id);
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message
                    .ERROR_AUTHOR_REQUEST);
        } finally {
            try {
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return 0;
    }

    public int update(Author author) {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement prstm = null;
        try {
            prstm = connection.prepareStatement(UPDATE_QUERY);
            prstm.setString(1, author.getAuthorName());
            prstm.setString(2, author.getDescription());
            prstm.setInt(3, author.getId());
            prstm.executeUpdate();
        } catch (SQLException e) {
            throw new DataBaseException(ExceptionConstant.Message
                    .ERROR_AUTHOR_REQUEST);
        } finally {
            try {
                ConnectionManager.close(prstm);
                ConnectionManager.close(connection);
            } catch (SQLException e) {
                throw new DataBaseException(ExceptionConstant.Message
                        .ERROR_CLOSE_CONNECTION);
            }
        }
        return 0;
    }
}
