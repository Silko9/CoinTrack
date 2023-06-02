package shapov.cointrack.databaseHelper;

import java.sql.*;

/**

 Абстрактный класс DatabaseHelper.

 <p>
 Данный класс предоставляет методы для выполнения запросов к базе данных.

 Реализует соединение с базой данных, выполнение SQL-запросов и закрытие соединения.

 <p>
 Все методы выбрасывают SQLException в случае возникновения ошибок.

 <p>
 @author ShapovAA

 @version 1.0
 */
public abstract class DatabaseHelper {
    private Connection connection;

    /**
     Выполняет SQL-запрос и возвращает результат в виде ResultSet.
     @param sql SQL-запрос
     @return результат запроса в виде ResultSet
     @throws SQLException если произошла ошибка при выполнении запроса
     */
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

    /**
     Выполняет SQL-запрос на обновление данных и возвращает количество измененных строк.
     @param sql SQL-запрос на обновление данных
     @return количество измененных строк
     @throws SQLException если произошла ошибка при выполнении запроса
     */
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

    /**
     Устанавливает соединение с базой данных.
     @return объект Statement для выполнения SQL-запросов
     @throws SQLException если произошла ошибка при установлении соединения
     */
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

    /**
     Закрывает соединение с базой данных.
     @throws SQLException если произошла ошибка при закрытии соединения
     */
    public void connectionClose() throws SQLException {
        connection.close();
    }
}
