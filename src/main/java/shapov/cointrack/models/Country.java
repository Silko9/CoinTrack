package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 Класс Country с полями <b>id</b> и <b>name</b>.

 <p>
 Данный класс представляет страну с заданным идентификатором и названием.

 Предоставляет конструкторы для создания экземпляра страны с различными параметрами.

 Предоставляет геттеры и сеттеры для доступа к полям страны.

 Реализует методы hashCode(), equals() и toString() с учетом полей id и name.

 Импортирует и использует аннотацию @Data из проекта Lombok для генерации

 геттеров, сеттеров, методов hashCode(), equals() и toString().

 @author ShapovAA

 @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    /** Поле идентификатор */
    private int id;
    /** Поле название */
    private String name;

    /**
     Конструктор - создание нового экземпляра страны с заданным названием.
     @param name название страны
     */
    public Country(String name) {
        this.name = name;
    }
}
