package shapov.cointrack.models;


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

    public Mint(int id, String name, int countryId) {
        this.id = id;
        this.name = name;
        this.countryId = countryId;
    }

    public Mint() {
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
