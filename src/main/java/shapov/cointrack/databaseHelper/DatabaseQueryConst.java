package shapov.cointrack.databaseHelper;


/**

 Класс DatabaseQueryConst с константами для SQL-запросов к базе данных.

 <p>
 Данный класс содержит константы для различных типов SQL-запросов, таких как SELECT, INSERT, UPDATE и DELETE.

 Каждая константа представляет шаблон SQL-запроса с заполнителями для подстановки значений.

 <p>
 @author ShapovAA

 @version 1.0
 */
public class DatabaseQueryConst {
    /**
    Константа SELECT_ALL - SQL-запрос SELECT для выборки всех полей из указанной таблицы.
    Заполнитель '%s' используется для подстановки имени таблицы.
    */
    public final static String SELECT_ALL = "SELECT * FROM %s ";

    /**
     Константа SELECT_WHERE - SQL-запрос SELECT с условием WHERE.
     Заполнители '%s' используются для подстановки имени таблицы и условия WHERE.
     */
    public final static String SELECT_WHERE = SELECT_ALL + "WHERE %s ";

    /**
     Константа SELECT_NO_ALL - SQL-запрос SELECT для выборки определенных полей с условием WHERE.
     Заполнители '%s' используются для подстановки списка полей, имени таблицы и условия WHERE.
     */
    public final static String SELECT_NO_ALL = "SELECT %s FROM %s WHERE %s" ;

    /**
     Константа INSERT - SQL-запрос INSERT для вставки данных в указанную таблицу.
     Заполнители '%s' используются для подстановки имени таблицы и списка полей и значений.
     */
    public final static String INSERT = "INSERT INTO %s SET %s ";

    /**
     Константа UPDATE - SQL-запрос UPDATE для обновления данных в указанной таблице.
     Заполнители '%s' используются для подстановки имени таблицы, списка полей и значений для обновления,
     а также идентификатора записи для обновления.
     */
    public final static String UPDATE = "UPDATE %s SET %s WHERE id=%d";

    /**
     Константа DELETE_RELATION - SQL-запрос DELETE для удаления связанных записей из указанной таблицы.
     Заполнители '%s' используются для подстановки имени таблицы и условия WHERE.
     */
    public final static String DELETE_RELATION = "DELETE FROM %s WHERE %s";

    /**
     Константа DELETE - SQL-запрос DELETE для удаления записи с указанным идентификатором из таблицы.
     Заполнители '%s' и '%d' используются для подстановки имени таблицы и идентификатора записи.
     */
    public final static String DELETE = "DELETE FROM %s WHERE id=%d";
}
