package shapov.cointrack.models;

import shapov.cointrack.models.properties.AlbumProperty;

/**

 Класс Album с полями <b>id</b> и <b>title</b>.

 <p>
 Данный класс представляет альбом с заданным идентификатором и названием.

 Предоставляет конструкторы для создания экземпляра альбома с различными параметрами.

 Предоставляет геттеры и сеттеры для доступа к полям альбома.

 Реализует методы hashCode(), equals() и toString() с учетом полей id и title.

 Импортирует и использует аннотацию @Data из проекта Lombok для генерации

 геттеров, сеттеров, методов hashCode(), equals() и toString().

 @author ShapovAA

 @version 1.0
 */
public class Album {
    
    /** Поле идентификатор */
    private int id;
    
    /** Поле заголовок альбома */
    private String title;
    
    /**
     Конструктор - создание нового экземпляра альбома с указанным заголовком.
     @param title заголовок альбома
     */
    public Album(String title) {
        this.title = title;
    }

    public Album(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public Album() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
