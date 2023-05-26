package shapov.cointrack.databaseHelper;

import java.sql.*;

public abstract class DatabaseHelper {
    private Connection connection;
    public ResultSet query(String sql) throws SQLException {
        Statement statement = connect();
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса");
        }
        return resultSet;
    }

    public int update(String sql) throws SQLException {
        Statement statement = connect();
        int result = 0;
        try {
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса");
        }
        connectionClose();
        return result;
    }
    private Statement connect() throws SQLException {
        connection = DriverManager.getConnection(DatabaseConnectConst.DB_URL, DatabaseConnectConst.LOGIN,
                DatabaseConnectConst.PASSWORD);
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Ошибка создания сесии.");
        }
        return statement;
    }
    public void connectionClose() throws SQLException {
        connection.close();
    }
}
