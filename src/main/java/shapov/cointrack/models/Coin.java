package shapov.cointrack.models;

import java.time.LocalDate;
import java.util.Optional;

/**
 *
 *  Класс Coin представляет монету с различными свойствами.
 *
 *  <p>Данный класс содержит информацию о монете, такую как идентификатор, номинал, идентификатор валюты,
 *  идентификатор страны, идентификатор монетного двора, год выпуска, и путь к изображению монеты.
 *
 *  Также класс содержит конструкторы, геттеры и сеттеры для доступа к свойствам монеты.
 *
 * <p>Класс Coin также использует аннотацию {@code @Data}, чтобы автоматически сгенерировать
 * методы {@code equals()}, {@code hashCode()}, {@code toString()}, а также геттеры и сеттеры для всех свойств.
 *
 *  <p>Класс Coin также содержит следующие конструкторы:
 *  <ul>
 *  <li>Конструктор без параметров, создающий новый экземпляр монеты с инициализированными свойствами по умолчанию.</li>
 * <li>Конструктор, принимающий параметры номинала, идентификатора валюты, идентификатора страны,
 * идентификатора монетного двора, года выпуска и пути к изображению монеты.</li>
 *
 * <li>Конструктор, принимающий идентификатор, номинал, идентификатор валюты, идентификатор страны,
 * идентификатор монетного двора, год выпуска и путь к изображению монеты.</li>
 *
 *  </ul>
 * <p>Пример использования:
 * <pre>{@code
 * Coin coin = new Coin(1, 100, 1, 1, 1, 2023, "path/to/picture.jpg");
 *
 * int denomination = coin.getDenomination();
 *
 * coin.setYearMinting(2024);
 *
 * }</pre>
 *
 * @author ShapovAA
 *
 *  @version 1.0
 */

 public class Coin {
    /**
     * Идентификатор монеты
     */
    private int id;

    /**
     * Номинал монеты
     */
    private int denomination;

    /**
     * Идентификатор валюты монеты
     */
    private int currencyId;

    /**
     * Валюта монеты
     */
    private Currency currency;

    /**
     * Идентификатор страны монеты
     */
    private int countryId;

    /**
     * Страна монеты
     */
    private Country country;

    /**
     * Идентификатор монетного двора
     */
    private int mintId;

    /**
     * Монетный двор
     */
    private Mint mint;

    /**
     * Год выпуска монеты
     */
    private int yearMinting;

    /**
     * Путь к изображению монеты
     */
    private String picturePath;

    /**
     * Конструктор для создания нового экземпляра монеты с заданными параметрами.
     *
     * @param denomination номинал монеты
     * @param currencyId   идентификатор валюты монеты
     * @param countryId    идентификатор страны монеты
     * @param mintId       идентификатор монетного двора
     * @param yearMinting  год выпуска монеты
     * @param picturePath  путь к изображению монеты
     */
    public Coin(int denomination, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) {
        this(0, denomination, currencyId, countryId, mintId, yearMinting, picturePath);
    }

    /**
     * Конструктор для создания нового экземпляра монеты с заданным идентификатором и параметрами.
     *
     * @param id           идентификатор монеты
     * @param denomination номинал монеты
     * @param currencyId   идентификатор валюты монеты
     * @param countryId    идентификатор страны монеты
     * @param mintId       идентификатор монетного двора
     * @param yearMinting  год выпуска монеты
     * @param picturePath  путь к изображению монеты
     */
    public Coin(int id, int denomination, int currencyId, int countryId, int mintId, int yearMinting, String picturePath) {
        this.id = id;
        this.denomination = denomination;
        this.currencyId = currencyId;
        this.countryId = countryId;
        this.mintId = mintId;
        this.yearMinting = yearMinting;
        this.picturePath = picturePath;
    }

    public Coin(int id, int denomination, int currencyId, Currency currency, int countryId, Country country, int mintId, Mint mint, int yearMinting, String picturePath) {
        this.id = id;
        this.denomination = denomination;
        this.currencyId = currencyId;
        this.currency = currency;
        this.countryId = countryId;
        this.country = country;
        this.mintId = mintId;
        this.mint = mint;
        this.yearMinting = yearMinting;
        this.picturePath = picturePath;
    }

    public Coin() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public int getMintId() {
        return mintId;
    }

    public void setMintId(int mintId) {
        this.mintId = mintId;
    }

    public Mint getMint() {
        return mint;
    }

    public void setMint(Mint mint) {
        this.mint = mint;
    }

    public int getYearMinting() {
        return yearMinting;
    }

    public void setYearMinting(int yearMinting) {
        this.yearMinting = yearMinting;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}