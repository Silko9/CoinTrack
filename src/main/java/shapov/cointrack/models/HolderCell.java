package shapov.cointrack.models;
/**

 Класс HolderCell с полями <b>id</b>, <b>coinId</b>, <b>coin</b>, <b>available</b>,

 <b>pageId</b>, <b>column</b>, <b>line</b> и <b>title</b>.

 <p>
 Данный класс представляет ячейку хранителя с монетой, имеющую идентификатор,

 идентификатор монеты, саму монету, доступность, идентификатор страницы, столбец,

 строку и название.

 Предоставляет конструкторы для создания экземпляра ячейки хранителя с различными параметрами.

 Предоставляет геттеры и сеттеры для доступа к полям ячейки хранителя.

 Реализует методы hashCode(), equals() и toString() с учетом полей id, coinId,

 coin, available, pageId, column, line и title.

 Импортирует и использует аннотацию @Data из проекта Lombok для генерации

 геттеров, сеттеров, методов hashCode(), equals() и toString().

 @author ShapovAA

 @version 1.0
 */
public class HolderCell {
    /** Поле идентификатор */
    public int id;
    /** Поле идентификатор монеты */
    private int coinId;
    /** Поле монета */
    private Coin coin;
    /** Поле наличие монеты */
    private boolean available;
    /** Поле идентификатор страницы */
    private int pageId;
    /** Поле столбец */
    private int column;
    /** Поле строка */
    private int line;
    /** Поле название */
    private String title;

    /**

     Конструктор - создание нового экземпляра ячейки хранителя с заданными параметрами.
     @param coinId идентификатор монеты
     @param available доступность
     @param pageId идентификатор страницы
     @param column столбец
     @param line строка
     @param title название
     */
    public HolderCell(int coinId, boolean available, int pageId, int column, int line, String title) {
        this.coinId = coinId;
        this.available = available;
        this.pageId = pageId;
        this.column = column;
        this.line = line;
        this.title = title;
    }
    /**

     Конструктор - создание нового экземпляра ячейки хранителя с заданными параметрами.
     @param id идентификатор
     @param coinId идентификатор монеты
     @param available доступность
     @param pageId идентификатор страницы
     @param column столбец
     @param line строка
     @param title название
     */
    public HolderCell(int id, int coinId, boolean available, int pageId, int column, int line, String title) {
        this.id = id;
        this.coinId = coinId;
        this.available = available;
        this.pageId = pageId;
        this.column = column;
        this.line = line;
        this.title = title;
    }

    public HolderCell(int id, int coinId, Coin coin, boolean available, int pageId, int column, int line, String title) {
        this.id = id;
        this.coinId = coinId;
        this.coin = coin;
        this.available = available;
        this.pageId = pageId;
        this.column = column;
        this.line = line;
        this.title = title;
    }

    public HolderCell() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoinId() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId = coinId;
    }

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
