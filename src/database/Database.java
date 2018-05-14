package database;

import java.sql.*;

public class Database {
    private final static String DATABASE_URL = "jdbc:mysql://localhost:3306/assignment";
    public static final String name = "com.mysql.jdbc.Driver";
    private Connection connection;
    private Statement statement;

    /**
     * Connect database.
     */
    public Database(){
        try {
            Class.forName(name);
            connection = DriverManager.getConnection(DATABASE_URL,"root","");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute the operation to the database and return the resultSet.
     * @param sql The operation that user want
     * @return The resultSet of the operation
     */
    public ResultSet executeQuery(String sql) {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Execute the operation to the database only.
     * @param sql The operation that user want
     * @return The resultSet of the operation
     */
    public boolean execute(String sql) {
        try {
            return statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
