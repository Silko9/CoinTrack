package shapov.cointrack.models.properties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shapov.cointrack.models.Album;


/**

 Класс AlbumProperty с полями <b>id</b> и <b>title</b>.

 <p>
 Данный класс представляет обертку для класса Album, обеспечивающую использование

 свойств типа IntegerProperty и StringProperty для полей id и title соответственно.

 Предоставляет конструктор для создания экземпляра AlbumProperty на основе объекта Album.

 Реализует геттеры, сеттеры и свойства для доступа к полям id и title.

 @author ShapovAA

 @version 1.0
 */
public class AlbumProperty {
    /** Поле id типа IntegerProperty */
    private IntegerProperty id;
    /** Поле title типа StringProperty */
    private StringProperty title;

    /**
     Конструктор - создание нового экземпляра AlbumProperty на основе объекта Album.
     @param album объект Album
     */
    public AlbumProperty(Album album){
        this.id = new SimpleIntegerProperty(album.getId());
        this.title = new SimpleStringProperty(album.getTitle());
    }

    public AlbumProperty() {
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
