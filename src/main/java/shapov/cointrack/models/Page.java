package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 Класс Page с полями <b>id</b>, <b>albumId</b>, <b>number</b> и <b>title</b>.

 <p>
 Данный класс представляет страницу в альбоме, имеющую идентификатор, идентификатор альбома,

 номер страницы и заголовок.

 Предоставляет конструкторы для создания экземпляра страницы с различными параметрами.

 Предоставляет геттеры и сеттеры для доступа к полям страницы.

 Реализует методы hashCode(), equals() и toString() с учетом полей id, albumId, number и title.

 Импортирует и использует аннотацию @Data из проекта Lombok для генерации

 геттеров, сеттеров, методов hashCode(), equals() и toString().

 @author ShapovAA

 @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {
    /** Поле идентификатор */
    private int id;
    /** Поле идентификатор альбома */
    private int albumId;
    /** Поле номер страницы */
    private int number;
    /** Поле заголовок */
    private String title;

    /**
     Конструктор - создание нового экземпляра страницы с заданными параметрами.
     @param albumId идентификатор альбома
     @param number номер страницы
     @param title заголовок
     */
    public Page(int albumId, int number, String title) {
        this.albumId = albumId;
        this.number = number;
        this.title = title;
    }
}
