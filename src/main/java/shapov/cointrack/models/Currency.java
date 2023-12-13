package shapov.cointrack.models;

import java.util.List;


/**

 Класс Currency с полями <b>id</b> и <b>name</b>.

 <p>
 Данный класс представляет валюту с заданным идентификатором и названием.

 Предоставляет конструкторы для создания экземпляра валюты с различными параметрами.

 Предоставляет геттеры и сеттеры для доступа к полям валюты.

 Реализует методы hashCode(), equals() и toString() с учетом полей id и name.

 Импортирует и использует аннотацию @Data из проекта Lombok для генерации

 геттеров, сеттеров, методов hashCode(), equals() и toString().

 @author ShapovAA

 @version 1.0
 */
public class Currency {
    /** Поле идентификатор */
    private int id;
    /** Поле название */
    private String name;

    /**
     Конструктор - создание нового экземпляра валюты с заданным названием.
     @param name название валюты
     */
    public Currency(String name) {
        this.name = name;
    }

    public Currency(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Currency() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
