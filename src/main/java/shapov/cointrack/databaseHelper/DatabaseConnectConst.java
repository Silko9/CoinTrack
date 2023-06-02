package shapov.cointrack.databaseHelper;


/**

 Класс DatabaseConnectConst с константами для подключения к базе данных.

 <p>
 Данный класс содержит константы, определяющие параметры подключения к базе данных, такие как URL, логин и пароль.

 <p>
 @author ShapovAA

 @version 1.0
 */
public class DatabaseConnectConst {
    /**
    Константа DB_URL - URL для подключения к базе данных.
    Значение указывает на локальный сервер MySQL с портом 3306.
    */
    public final static String DB_URL = "jdbc:mysql://localhost:3306/";

    /**
     Константа LOGIN - логин для подключения к базе данных.
     Значение указывает на логин "root".
     */
    public final static String LOGIN = "root";

    /**
     Константа PASSWORD - пароль для подключения к базе данных.
     Значение указывает на пароль "root".
     */
    public final static String PASSWORD = "root";
}
