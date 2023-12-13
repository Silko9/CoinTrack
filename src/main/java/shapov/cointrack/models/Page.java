package shapov.cointrack.models;

/**

 Класс Page с полями <b>id</b>, <b>albumId</b>, <b>previousPageId</b>, <b>nextPageId</b>  и <b>title</b>.

 <p>
 Данный класс представляет страницу в альбоме, имеющую идентификатор, идентификатор альбома,

 id страниц предыдущей и следующней страницы, заголовок.

 Предоставляет конструкторы для создания экземпляра страницы с различными параметрами.

 Предоставляет геттеры и сеттеры для доступа к полям страницы.

 Реализует методы hashCode(), equals() и toString() с учетом полей id, albumId, previousPageId, nextPageId и title.

 Импортирует и использует аннотацию @Data из проекта Lombok для генерации

 геттеров, сеттеров, методов hashCode(), equals() и toString().

 @author ShapovAA

 @version 1.0
 */
public class Page {

    /** Поле идентификатор */
    private int id;

    /** Поле идентификатор альбома */
    private int albumId;

    /** Поле id предыдущей страницы */
    private int previousPageId;

    /** Поле id следующей страницы */
    private int nextPageId;

    /** Поле заголовок */
    private String title;

    /**
     Конструктор - создание нового экземпляра страницы с заданными параметрами.
     @param albumId идентификатор альбома
     @param previousPageId id предыдущей страницы
     @param nextPageId id следующей страницы
     @param title заголовок
     */
    public Page(int albumId, int previousPageId, int nextPageId, String title) {
        this.albumId = albumId;
        this.previousPageId = previousPageId;
        this.nextPageId = nextPageId;
        this.title = title;
    }

    public Page(int id, int albumId, int previousPageId, int nextPageId, String title) {
        this.id = id;
        this.albumId = albumId;
        this.previousPageId = previousPageId;
        this.nextPageId = nextPageId;
        this.title = title;
    }

    public Page() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getPreviousPageId() {
        return previousPageId;
    }

    public void setPreviousPageId(int previousPageId) {
        this.previousPageId = previousPageId;
    }

    public int getNextPageId() {
        return nextPageId;
    }

    public void setNextPageId(int nextPageId) {
        this.nextPageId = nextPageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
