package shapov.cointrack.models.properties;

import javafx.beans.property.*;
import shapov.cointrack.models.Coin;


/**

 Класс CoinProperty с полями <b>id</b>, <b>denomination</b>, <b>currency</b>,
 <b>country</b>, <b>mint</b>, <b>yearMinting</b> и <b>picturePath</b>.
 <p>
 Данный класс представляет обертку для класса Coin, обеспечивающую использование
 свойств типа IntegerProperty и StringProperty для полей id, denomination, currency,
 country, mint, yearMinting и picturePath соответственно.
 Предоставляет конструктор для создания экземпляра CoinProperty на основе объекта Coin.
 Реализует геттеры, сеттеры и свойства для доступа к полям.

 @author ShapovAA

 @version 1.0
 */
public class CoinProperty {
    /** Поле id типа IntegerProperty */
    private final IntegerProperty id;
    /** Поле denomination типа IntegerProperty  */
    private final IntegerProperty denomination;
    /** Поле currency типа StringProperty */
    private final StringProperty currency;
    /** Поле country типа StringProperty */
    private final StringProperty country;
    /** Поле mint типа StringProperty */
    private final StringProperty mint;
    /** Поле yearMinting типа IntegerProperty */
    private final IntegerProperty yearMinting;
    /** Поле picturePath типа StringProperty */
    private final StringProperty picturePath;

    /**
     Конструктор - создание нового экземпляра CoinProperty на основе объекта Coin.
     @param coin объект Coin
     */
    public CoinProperty(Coin coin) {
        this.id = new SimpleIntegerProperty(coin.getId());
        this.denomination = new SimpleIntegerProperty(coin.getDenomination());
        this.currency = new SimpleStringProperty(coin.getCurrency().getName());
        this.country = new SimpleStringProperty(coin.getCountry().getName());
        this.mint = new SimpleStringProperty(coin.getMint().getName());
        this.yearMinting = new SimpleIntegerProperty(coin.getYearMinting());
        this.picturePath = new SimpleStringProperty(coin.getPicturePath());
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
     Функция получения значения поля denomination.
     @return возвращает значение denomination
     */
    public int getDenomination() {
        return denomination.get();
    }

    /**
     Функция получения свойства denominationProperty.
     @return возвращает свойство denominationProperty
     */
    public IntegerProperty denominationProperty() {
        return denomination;
    }

    /**
     Функция установки значения поля denomination.
     @param denomination значение denomination
     */
    public void setDenomination(int denomination) {
        this.denomination.set(denomination);
    }

    /**
     Функция получения значения поля currency.
     @return возвращает значение currency
     */
    public String getCurrency() {
        return currency.get();
    }

    /**
     Функция получения свойства currencyProperty.
     @return возвращает свойство currencyProperty
     */
    public StringProperty currencyProperty() {
        return currency;
    }

    /**
     Функция установки значения поля currency.
     @param currency значение currency
     */
    public void setCurrency(String currency) {
        this.currency.set(currency);
    }

    /**
     Функция получения значения поля country.
     @return возвращает значение country
     */
    public String getCountry() {
        return country.get();
    }

    /**
     Функция получения свойства countryProperty.
     @return возвращает свойство countryProperty
     */
    public StringProperty countryProperty() {
        return country;
    }

    /**
     Функция установки значения поля country.
     @param country значение country
     */
    public void setCountry(String country) {
        this.country.set(country);
    }

    /**
     Функция получения значения поля mint.
     @return возвращает значение mint
     */
    public String getMint() {
        return mint.get();
    }

    /**
     Функция получения свойства mintProperty.
     @return возвращает свойство mintProperty
     */
    public StringProperty mintProperty() {
        return mint;
    }

    /**
     Функция установки значения поля mint.
     @param mint значение mint
     */
    public void setMint(String mint) {
        this.mint.set(mint);
    }

    /**
     Функция получения значения поля yearMinting.
     @return возвращает значение yearMinting
     */
    public int getYearMinting() {
        return yearMinting.get();
    }

    /**
     Функция получения свойства yearMintingProperty.
     @return возвращает свойство yearMintingProperty
     */
    public IntegerProperty yearMintingProperty() {
        return yearMinting;
    }

    /**
     Функция установки значения поля yearMinting.
     @param yearMinting значение yearMinting
     */
    public void setYearMinting(int yearMinting) {
        this.yearMinting.set(yearMinting);
    }

    /**
     Функция получения значения поля picturePath.
     @return возвращает значение picturePath
     */
    public String getPicturePath() {
        return picturePath.get();
    }

    /**
     Функция получения свойства picturePathProperty.
     @return возвращает свойство picturePathProperty
     */
    public StringProperty picturePathProperty() {
        return picturePath;
    }

    /**
     Функция установки значения поля picturePath.
     @param picturePath значение picturePath
     */
    public void setPicturePath(String picturePath) {
        this.picturePath.set(picturePath);
    }
}