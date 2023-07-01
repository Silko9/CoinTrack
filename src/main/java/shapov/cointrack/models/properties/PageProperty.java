package shapov.cointrack.models.properties;

import javafx.beans.property.*;
import shapov.cointrack.models.Page;

/**

 Класс PageProperty с полями id, albumId, previousPageId, nextPageId и title.

 <p>
 Данный класс представляет обертку для класса Page, обеспечивающую использование

 свойств типа IntegerProperty и StringProperty для полей id, albumId, previousPageId, nextPageId и title соответственно.

 <p>
 Предоставляет конструктор для создания экземпляра PageProperty на основе объекта Page.

 Реализует геттеры, сеттеры и свойства для доступа к полям.

 <p>
 @author ShapovAA

 @version 1.0
 */
public class PageProperty {

    /** Поле id типа IntegerProperty */
    private final IntegerProperty id;

    /** Поле albumId типа IntegerProperty */
    private final IntegerProperty albumId;

    /** Поле previousPageId типа IntegerProperty */
    private final IntegerProperty previousPageId;

    /** Поле nextPageId типа IntegerProperty */
    private final IntegerProperty nextPageId;

    /** Поле title типа StringProperty */
    private final StringProperty title;

    /**
     Конструктор - создание нового экземпляра PageProperty на основе объекта Page.
     @param page объект Page
     */
    public PageProperty(Page page) {
        this.id = new SimpleIntegerProperty(page.getId());
        this.albumId = new SimpleIntegerProperty(page.getAlbumId());
        this.previousPageId = new SimpleIntegerProperty(page.getPreviousPageId());
        this.nextPageId = new SimpleIntegerProperty(page.getNextPageId());
        this.title = new SimpleStringProperty(page.getTitle());
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
     Функция получения значения поля albumId.
     @return возвращает значение albumId
     */
    public int getAlbumId() {
        return albumId.get();
    }

    /**
     Функция получения свойства albumIdProperty.
     @return возвращает свойство albumIdProperty
     */
    public IntegerProperty albumIdProperty() {
        return albumId;
    }

    /**
     Функция установки значения поля albumId.
     @param albumId значение albumId
     */
    public void setAlbumId(int albumId) {
        this.albumId.set(albumId);
    }

    /**
     Функция получения значения поля previousPageId.
     @return возвращает значение previousPageId
     */
    public int getPreviousPageId() {
        return previousPageId.get();
    }

    /**
     Функция получения свойства previousPageIdProperty.
     @return возвращает свойство previousPageIdProperty
     */
    public IntegerProperty previousPageIdProperty() {
        return previousPageId;
    }

    /**
     Функция установки значения поля previousPageId.
     @param previousPageId значение previousPageId
     */
    public void setPreviousPageId(int previousPageId) {
        this.previousPageId.set(previousPageId);
    }

    /**
     Функция получения значения поля nextPageId.
     @return возвращает значение nextPageId
     */
    public int getNextPageId() {
        return nextPageId.get();
    }

    /**
     Функция получения свойства nextPageIdProperty.
     @return возвращает свойство nextPageIdProperty
     */
    public IntegerProperty nextPageIdProperty() {
        return nextPageId;
    }

    /**
     Функция установки значения поля nextPageId.
     @param nextPageId значение nextPageId
     */
    public void setNextPageId(int nextPageId) {
        this.nextPageId.set(nextPageId);
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

