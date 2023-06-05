package shapov.cointrack.services.implement;

import shapov.cointrack.models.Coin;
import shapov.cointrack.models.Country;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.repositories.CoinRepository;
import shapov.cointrack.repositories.HolderCellRepository;
import shapov.cointrack.repositories.implement.CoinRepositoryImpl;
import shapov.cointrack.repositories.implement.HolderCellRepositoryImpl;
import shapov.cointrack.services.CoinService;
import shapov.cointrack.services.HolderCellService;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 Класс HolderCellServiceImpl представляет реализацию интерфейса HolderCellService.

 Он предоставляет методы для выполнения операций с ячейками хранения, используя слои репозиториев.

 @author ShapovAA

 @version 1.0
 */
public class HolderCellServiceImpl implements HolderCellService {

    /**
     Репозиторий для работы с ячейками хранения.
     */
    private final HolderCellRepository holderCellRepository = new HolderCellRepositoryImpl("CoinTrack");

    /**
     Репозиторий для работы с монетами.
     */
    private final CoinRepository coinRepository = new CoinRepositoryImpl("CoinTrack");

    /**
     Получает список всех ячеек хранения.
     @return список всех ячеек хранения
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<HolderCell> findAll() throws SQLException {
        return holderCellRepository.findAll();
    }

    /**
     Находит ячейку хранения по указанному идентификатору.
     @param id идентификатор ячейки хранения
     @return ячейка хранения, если найдена, в противном случае пустое значение Optional
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public Optional<HolderCell> findOneById(int id) throws SQLException {
        return holderCellRepository.findOneById(id);
    }

    /**
     Находит ячейки хранения с указанным идентификатором страницы.
     @param pageId идентификатор страницы
     @return список ячеек хранения с указанным идентификатором страницы
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public List<HolderCell> findByPageId(int pageId) throws SQLException {
        return holderCellRepository.findByPageId(pageId);
    }

    /**
     Создает новую ячейку хранения с указанными параметрами.
     @param coinId идентификатор монеты
     @param pageId идентификатор страницы
     @param column номер столбца
     @param line номер строки
     @param title заголовок
     @return количество созданных ячеек хранения (обычно 1, если ячейка хранения успешо создалась)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int create(int coinId, int pageId, int column, int line, String title) throws SQLException {
        return holderCellRepository.create(new HolderCell(coinId, false, pageId, column, line, title));
    }

    /**
     Обновляет ячейку хранения с указанным идентификатором новыми параметрами.
     @param id идентификатор ячейки хранения, которую нужно обновить
     @param available доступность ячейки хранения
     @param coinId идентификатор монеты
     @param pageId идентификатор страницы
     @param column номер столбца
     @param line номер строки
     @param title заголовок
     @return количество обновленных ячеек хранения (обычно 1, если ячейка хранения с указанным идентификатором существует)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int update(int id, boolean available, int coinId, int pageId, int column, int line, String title) throws SQLException {
        return holderCellRepository.edit(new HolderCell(id, coinId, available, pageId, column, line, title));
    }

    /**
     Удаляет ячейку хранения с указанным идентификатором.
     @param id идентификатор ячейки хранения, которую нужно удалить
     @return количество удаленных ячеек хранения (обычно 1, если ячейка хранения с указанным идентификатором существует)
     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public int delete(int id) throws SQLException {
        return holderCellRepository.delete(id);
    }

    /**

     Включает информацию о монете в указанную ячейку хранения.

     @param holderCell ячейка хранения

     @return ячейка хранения с информацией о монете

     @throws SQLException если возникает ошибка при выполнении SQL-запроса
     */
    @Override
    public HolderCell include(HolderCell holderCell) throws SQLException {
        Optional<Coin> coin = coinRepository.findOneById(holderCell.getCoinId());

        if(coin.isEmpty()) return null;

        holderCell.setCoin(coin.get());

        return holderCell;
    }
}
