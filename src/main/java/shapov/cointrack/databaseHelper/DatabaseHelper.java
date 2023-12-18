package shapov.cointrack.databaseHelper;

import com.fasterxml.jackson.databind.ObjectMapper;
import shapov.cointrack.AppConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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

    public static String getAll(String table) throws IOException {
        URL url = new URL(AppConfig.API_BASE_URL + "/" + table + "/get-all");
        return get(url);
    }

    public static String getById(String table, int id) throws IOException {
        URL url = new URL(AppConfig.API_BASE_URL + "/" + table + "/get/" + id);
        return get(url);
    }

    public static String getById(String request) throws IOException {
        URL url = new URL(AppConfig.API_BASE_URL + "/" + request);
        return get(url);
    }

    public static void create(String table, Object model) throws IOException {
        URL url = new URL(AppConfig.API_BASE_URL + "/" + table + "/add");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(model);
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Объект создан: " + response);
        }
    }

    public static void edit(String table, Object model, int id) throws IOException {
        URL url = new URL(AppConfig.API_BASE_URL + "/" + table +"/edit/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(model);
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try(OutputStream os = con.getOutputStream()) {
            byte[] input = json.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println("Объект изменен: " + response);
        }
    }

    public static void delete(String table, int id) throws IOException {
        URL url = new URL(AppConfig.API_BASE_URL + "/" + table + "/delete/" + id);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");

        int responseCode = con.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_NO_CONTENT) {
            System.out.println("Объект успешно удален");
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            System.out.println("Объект не найден");
        } else {
            System.out.println("Ошибка при удалении объекта. Код ответа: " + responseCode);
        }
    }

    private static String get(URL url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");
        if(conn.getResponseCode() != 200){
            System.out.println("Ошибка при получение альбома. Код ответа: " + conn.getResponseCode());
            return null;
        }
        BufferedReader bufferedReader =
                new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder content = new StringBuilder();
        while((line = bufferedReader.readLine()) != null) {
            content.append(line + "\n");
        }
        bufferedReader.close();
        return content.toString();
    }
}
