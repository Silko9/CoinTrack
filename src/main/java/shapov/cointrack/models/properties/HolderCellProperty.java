package shapov.cointrack.models.properties;

import javafx.beans.property.*;
import shapov.cointrack.models.HolderCell;


/**

 Класс HolderCellProperty с полями id, coinId, available, pageId, column, line и title.

 <p>
 Данный класс представляет обертку для класса HolderCell, обеспечивающую использование

 свойств типа IntegerProperty, BooleanProperty и StringProperty для полей id, coinId, available,

 pageId, column, line и title соответственно.

 <p>
 Предоставляет конструктор для создания экземпляра HolderCellProperty на основе объекта HolderCell.

 Реализует геттеры, сеттеры и свойства для доступа к полям.

 <p>
 @author ShapovAA

 @version 1.0
 */
public class HolderCellProperty {
    /** Поле id типа IntegerProperty */
    private final IntegerProperty id;
    /** Поле coinId типа IntegerProperty */
    private final IntegerProperty coinId;
    /** Поле available типа BooleanProperty */
    private final BooleanProperty available;
    /** Поле pageId типа IntegerProperty */
    private final IntegerProperty pageId;
    /** Поле column типа IntegerProperty */
    private final IntegerProperty column;
    /** Поле line типа IntegerProperty */
    private final IntegerProperty line;
    /** Поле title типа StringProperty  */
    private final StringProperty title;

    /**
     Конструктор - создание нового экземпляра HolderCellProperty на основе объекта HolderCell.
     @param holderCell объект HolderCell
     */
    public HolderCellProperty(HolderCell holderCell) {
        this.id = new SimpleIntegerProperty(holderCell.getId());
        this.coinId = new SimpleIntegerProperty(holderCell.getCoinId());
        this.available = new SimpleBooleanProperty(holderCell.isAvailable());
        this.pageId = new SimpleIntegerProperty(holderCell.getPageId());
        this.column = new SimpleIntegerProperty(holderCell.getColumn());
        this.line = new SimpleIntegerProperty(holderCell.getLine());
        this.title = new SimpleStringProperty(holderCell.getTitle());
    }

    /**
     Функция получения значения поля id.
     @return возвращает значение id
     */
    public int getId() {
        return id.get();
    }

    /**
     Функция получения свойства idProperty.
     @return возвращает свойство idProperty
     */
    public IntegerProperty idProperty() {
        return id;
    }

    /**
     Функция установки значения поля id.
     @param id значение id
     */
    public void setId(int id) {
        this.id.set(id);
    }

    /**
     Функция получения значения поля coinId.
     @return возвращает значение coinId
     */
    public int getCoinId() {
        return coinId.get();
    }

    /**

     Функция получения свойства coinIdProperty.
     @return возвращает свойство coinIdProperty
     */
    public IntegerProperty coinIdProperty() {
        return coinId;
    }

    /**
     Функция установки значения поля coinId.
     @param coinId значение coinId
     */
    public void setCoinId(int coinId) {
        this.coinId.set(coinId);
    }

    /**
     Функция получения значения поля available.
     @return возвращает значение available
     */
    public boolean isAvailable() {
        return available.get();
    }

    /**
     Функция получения свойства availableProperty.
     @return возвращает свойство availableProperty
     */
    public BooleanProperty availableProperty() {
        return available;
    }

    /**
     Функция установки значения поля available.
     @param available значение available
     */
    public void setAvailable(boolean available) {
        this.available.set(available);
    }

    /**
     Функция получения значения поля pageId.
     @return возвращает значение pageId
     */
    public int getPageId() {
        return pageId.get();
    }

    /**
     Функция получения свойства pageIdProperty.
     @return возвращает свойство pageIdProperty
     */
    public IntegerProperty pageIdProperty() {
        return pageId;
    }

    /**
     Функция установки значения поля pageId.
     @param pageId значение pageId
     */
    public void setPageId(int pageId) {
        this.pageId.set(pageId);
    }

    /**
     Функция получения значения поля column.
     @return возвращает значение column
     */
    public int getColumn() {
        return column.get();
    }

    /**
     Функция получения свойства columnProperty.
     @return возвращает свойство columnProperty
     */
    public IntegerProperty columnProperty() {
        return column;
    }

    /**
     Функция установки значения поля column.
     @param column значение column
     */
    public void setColumn(int column) {
        this.column.set(column);
    }

    /**
     Функция получения значения поля line.
     @return возвращает значение line
     */
    public int getLine() {
        return line.get();
    }

    /**
     Функция получения свойства lineProperty.
     @return возвращает свойство lineProperty
     */
    public IntegerProperty lineProperty() {
        return line;
    }

    /**
     Функция установки значения поля line.
     @param line значение line
     */
    public void setLine(int line) {
        this.line.set(line);
    }

    /**
     Функция получения значения поля title.
     @return возвращает значение title
     */
    public String getTitle() {
        return title.get();
    }

    /**
     Функция получения свойства titleProperty.
     @return возвращает свойство titleProperty
     */
    public StringProperty titleProperty() {
        return title;
    }

    /**
     Функция установки значения поля title.
     @param title значение title
     */
    public void setTitle(String title) {
        this.title.set(title);
    }
}

