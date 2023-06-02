package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HolderCell {
    /** Поле идентификатор */
    private int id;
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
}
