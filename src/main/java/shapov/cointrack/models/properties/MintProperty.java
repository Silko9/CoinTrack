package shapov.cointrack.models.properties;

import javafx.beans.property.*;
import shapov.cointrack.models.Mint;


/**

 Класс MintProperty с полями id, name и countryId.

 <p>
 Данный класс представляет обертку для класса Mint, обеспечивающую использование

 свойств типа IntegerProperty и StringProperty для полей id, name и countryId соответственно.

 <p>
 Предоставляет конструктор для создания экземпляра MintProperty на основе объекта Mint.

 Реализует геттеры, сеттеры и свойства для доступа к полям.

 <p>
 @author ShapovAA

 @version 1.0
 */
public class MintProperty {
    /** Поле id типа IntegerProperty */
    private final IntegerProperty id;
    /** Поле name типа StringProperty */
    private final StringProperty name;
    /** Поле countryId типа IntegerProperty */
    private final IntegerProperty countryId;

    /**
     Конструктор - создание нового экземпляра MintProperty на основе объекта Mint.
     @param mint объект Mint
     */
    public MintProperty(Mint mint) {
        this.id = new SimpleIntegerProperty(mint.getId());
        this.name = new SimpleStringProperty(mint.getName());
        this.countryId = new SimpleIntegerProperty(mint.getCountryId());
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

    /**
     Функция получения значения поля countryId.
     @return возвращает значение countryId
     */
    public int getCountryId() {
        return countryId.get();
    }

    /**
     Функция получения свойства countryIdProperty.
     @return возвращает свойство countryIdProperty
     */
    public IntegerProperty countryIdProperty() {
        return countryId;
    }

    /**
     Функция установки значения поля countryId.
     @param countryId значение countryId
     */
    public void setCountryId(int countryId) {
        this.countryId.set(countryId);
    }
}

