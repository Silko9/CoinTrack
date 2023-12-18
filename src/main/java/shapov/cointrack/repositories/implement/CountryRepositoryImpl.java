package shapov.cointrack.repositories.implement;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import shapov.cointrack.databaseHelper.DatabaseHelper;
import shapov.cointrack.databaseHelper.DatabaseQueryConst;
import shapov.cointrack.models.Country;
import shapov.cointrack.repositories.CountryRepository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 Класс CountryRepositoryImpl представляет реализацию интерфейса CountryRepository и расширяет класс DatabaseHelper.

 Он предоставляет методы для выполнения операций с базой данных, связанных с странами.

 Конструкторы реализованы с помощью lombok.
 Без аргументов, будет использовать базу данных по умолчанию: CoinTrackTest
 С одним аргументом, название базы данных.

 @author ShapovAA

 @version 1.0
 */
public class CountryRepositoryImpl implements CountryRepository {

    /** Поле константа название таблицы в базе данных */
    private final static String nameTable = "countries";

    public CountryRepositoryImpl() {
    }

    /**
     Извлекает все страны из базы данных.
     @return Список объектов Country.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public List<Country> findAll() throws IOException {
        return new ObjectMapper().readValue(DatabaseHelper.getAll("countries"), new TypeReference<>(){});
    }

    /**
     Извлекает страну из базы данных по ее ID.
     @param id ID страны для извлечения.
     @return Объект Optional, содержащий Country, если найдена, или пустой Optional, если не найдена.
     @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public Optional<Country> findOneById(int id) throws SQLException, IOException {
        Country country = new ObjectMapper().readValue(DatabaseHelper.getById(nameTable + "/get/" + id), Country.class);
        if(country == null)
            return Optional.empty();
        else
            return Optional.of(country);
    }

    /**
     * Создает новую страну в базе данных.
     *
     * @param country Объект Country для создания.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void create(Country country) throws SQLException, IOException {
        DatabaseHelper.create(nameTable, country);
    }

    /**
     * Редактирует существующую страну в базе данных.
     *
     * @param country Объект Country для редактирования.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void edit(Country country) throws SQLException, IOException {
        DatabaseHelper.edit(nameTable, country, country.getId());
    }

    /**
     * Удаляет страну из базы данных по ее ID.
     *
     * @param id ID страны для удаления.
     * @throws SQLException если произошла ошибка при выполнении запроса к базе данных.
     */
    @Override
    public void delete(int id) throws SQLException, IOException {
        DatabaseHelper.delete(nameTable, id);
    }
}
