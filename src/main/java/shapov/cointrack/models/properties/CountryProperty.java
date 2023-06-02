package shapov.cointrack.models.properties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import shapov.cointrack.models.Country;

/**

 Класс CountryProperty с полями <b>id</b> и <b>name</b>.

 <p>
 Данный класс представляет обертку для класса Country, обеспечивающую использование

 свойств типа IntegerProperty и StringProperty для полей id и name соответственно.

 Предоставляет конструктор для создания экземпляра CountryProperty на основе объекта Country.

 Реализует геттеры, сеттеры и свойства для доступа к полям.

 @author ShapovAA

 @version 1.0
 */
public class CountryProperty {
    /** Поле id типа IntegerProperty  */
    private final IntegerProperty id;
    /** Поле name типа StringProperty */
    private final StringProperty name;

    /**
     Конструктор - создание нового экземпляра CountryProperty на основе объекта Country.
     @param country объект Country
     */
    public CountryProperty(Country country){
        this.id = new SimpleIntegerProperty(country.getId());
        this.name = new SimpleStringProperty(country.getName());
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
     Функция получения значения поля name.
     @return возвращает значение name
     */
    public String getName() {
        return name.get();
    }

    /**
     Функция получения свойства nameProperty.
     @return возвращает свойство nameProperty
     */
    public StringProperty nameProperty() {
        return name;
    }

    /**
     Функция установки значения поля name.
     @param name значение name
     */
    public void setName(String name) {
        this.name.set(name);
    }
}
