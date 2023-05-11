package shapov.cointrack.databaseHelper.databeseQueryConst;

import shapov.cointrack.databaseHelper.DatabaseQueryConst;

import java.lang.annotation.Target;

public class DatabaseCoinQueryConst extends DatabaseQueryConst {
    private final static String TABLE_NAME = "coins";
    private final static String PARAMETERS = "denomination=%d, currencyId=%d, countryId=%d, mintId=%d, dateMinting='%s' ";
    public final static String SELECT_ALL_COINS = String.format(SELECT_ALL, TABLE_NAME);
    public final static String SELECT_WHERE_COINS = String.format(SELECT_WHERE, TABLE_NAME, "%s");
    public final static String QUERY_BY_ID_GET_COIN = String.format(SELECT_WHERE_COINS, "id=%d;");
    public final static String QUERY_BY_DENOMINATION_GET_COINS = String.format(SELECT_WHERE_COINS, "denomination=%d");
    public final static String QUERY_BY_CURRENCY_ID_GET_COINS = String.format(SELECT_WHERE_COINS, "currencyId=%d");
    public final static String QUERY_BY_COUNTRY_ID_GET_COINS = String.format(SELECT_WHERE_COINS, "countryId=%d");
    public final static String QUERY_BY_MINT_ID_GET_COINS = String.format(SELECT_WHERE_COINS, "mintId=%d");
    public final static String QUERY_BY_DATE_MINTING_GET_COINS = String.format(SELECT_WHERE_COINS, "dateMinting='%s'");
    public final static String INSERT_COIN = String.format(INSERT, TABLE_NAME, PARAMETERS);
    public final static String UPDATE_COIN = String.format(UPDATE, TABLE_NAME, PARAMETERS, "id=%d");
    public final static String DELETE_COIN = String.format(DELETE, TABLE_NAME, "id=%d");
}
