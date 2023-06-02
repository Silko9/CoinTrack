package shapov.cointrack.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
 @Data
 @AllArgsConstructor
 @NoArgsConstructor
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
}