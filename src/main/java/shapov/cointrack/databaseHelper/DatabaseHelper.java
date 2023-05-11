package shapov.cointrack.databaseHelper;

public class DatabaseHelper {
    public static ArrayList<Student> query(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(Const.DB_URL, Const.LOGIN,
                Const.PASSWORD);
        ArrayList<Student> students = new ArrayList<>();
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Ошибка создания сесии.");
        }
        try {
            ResultSet resultSet = statement.executeQuery(sql);
            students = mapper(resultSet);
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса");
        }
        connection.close();
        return students;
    }

    public static int update(String sql) throws SQLException {
        Connection connection = DriverManager.getConnection(Const.DB_URL, Const.LOGIN,
                Const.PASSWORD);
        Statement statement = null;
        int result = 0;
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            System.out.println("Ошибка создания сесии.");
        }
        try {
            result = statement.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса");
        }
        connection.close();
        return result;
    }

    private static ArrayList<Student> mapper(ResultSet resultSet) throws
            SQLException {
        ArrayList<Student> students = new ArrayList<>();
        Student student;
        while (resultSet.next()) {
            String firstName = resultSet.getString("FirstName");
            String lastName = resultSet.getString("LastName");
            int age = resultSet.getInt("Age");
            long id = resultSet.getInt("Id");
            student = new Student((int) id, firstName, lastName, age);
            students.add(student);
        }
        return students;
    }
}
