package by.gsu.epamlab.model.connection;

import by.gsu.epamlab.exeptions.DataBaseException;

import java.sql.*;

public class ConnectionManager {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/epamlabweb";
    private static final String DB_LOGIN = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection =  DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new DataBaseException(e.getMessage());
        } catch (SQLException e) {
            throw new DataBaseException(e.getMessage());
        }
        return connection;
    }

    public static void close(Connection connection) throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public static  void  close(PreparedStatement... preparedStatements)
            throws SQLException {
        for (PreparedStatement prstm: preparedStatements){
            if (prstm != null){
                prstm.close();
            }
        }
    }

    public static void close(Statement... statements) throws SQLException {
        for (Statement stm: statements) {
            if (stm != null) {
                stm.close();
            }
        }
    }

    public static void close(ResultSet... resultSets) throws SQLException {
        for (ResultSet rs: resultSets) {
            if (rs != null) {
                rs.close();
            }
        }
    }

}
