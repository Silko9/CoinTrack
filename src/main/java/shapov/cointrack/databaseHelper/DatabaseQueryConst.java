package shapov.cointrack.databaseHelper;

public class DatabaseQueryConst {
    public final static String SELECT_ALL = "SELECT * FROM %s ";
    public final static String SELECT_WHERE = SELECT_ALL + "WHERE %s ";
    public final static String SELECT_NO_ALL = "SELECT %s FROM %s WHERE %s" ;
    public final static String INSERT = "INSERT INTO %s SET %s ";
    public final static String UPDATE = "UPDATE %s SET %s WHERE id=%d";
    public final static String DELETE_RELATION = "DELETE FROM %s WHERE %s";
    public final static String DELETE = "DELETE FROM %s WHERE id=%d";
}