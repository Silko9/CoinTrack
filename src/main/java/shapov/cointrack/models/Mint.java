package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**

 Класс Mint с полями <b>id</b>, <b>name</b> и <b>countryId</b>.

 <p>
 Данный класс представляет монетный двор, имеющий идентификатор, название и

 идентификатор страны.

 Предоставляет конструкторы для создания экземпляра монетного двора с различными параметрами.

 Предоставляет геттеры и сеттеры для доступа к полям монетного двора.

 Реализует методы hashCode(), equals() и toString() с учетом полей id, name и countryId.

 Импортирует и использует аннотацию @Data из проекта Lombok для генерации

 геттеров, сеттеров, методов hashCode(), equals() и toString().

 @author ShapovAA

 @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mint {
    /** Поле идентификатор */
    private int id;
    /** Поле название */
    private String name;
    /** Поле идентификатор страны */
    private int countryId;

    /**

     Конструктор - создание нового экземпляра монетного двора с заданными параметрами.
     @param name название
     @param countryId идентификатор страны
     */
    public Mint(String name, int countryId) {
        this.name = name;
        this.countryId = countryId;
    }
}
