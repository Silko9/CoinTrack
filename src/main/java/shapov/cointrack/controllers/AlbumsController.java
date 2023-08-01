package shapov.cointrack.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import shapov.cointrack.AlertHelper;
import shapov.cointrack.controllers.albumModeles.AlbumsListController;
import shapov.cointrack.controllers.albumModeles.CoinManagerController;
import shapov.cointrack.controllers.albumModeles.PageManagerController;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.models.Page;
import shapov.cointrack.models.Coin;
import shapov.cointrack.services.CoinService;
import shapov.cointrack.services.HolderCellService;
import shapov.cointrack.services.PageService;
import shapov.cointrack.services.implement.CoinServiceImpl;
import shapov.cointrack.services.implement.HolderCellServiceImpl;
import shapov.cointrack.services.implement.PageServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

public class AlbumsController implements Initializable {
    
    private final HolderCellService holderCellService = new HolderCellServiceImpl();
    
    private final CoinService coinService = new CoinServiceImpl();

    private final PageService pageService = new PageServiceImpl();

    @Getter
    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label labelTitlePage;
    
    @FXML
    private Label labelNumberPage;
    
    private int idCoin;
    
    private String titleHolder;

    @Getter
    private TypeCoinAction typeCoinAction = TypeCoinAction.NONE;

    @Getter
    @Setter
    private AlbumInfo albumInfo = new AlbumInfo();

    private HolderCells holderCells;
    
    private AlbumsListController albumsListController;

    private PageManagerController pageManagerController;

    private CoinManagerController coinManagerController;
                    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        albumsListController = AlbumsListController.getInstance();
        albumsListController.setMainController(this);

        pageManagerController = PageManagerController.getInstance();
        pageManagerController.setMainController(this);

        coinManagerController = CoinManagerController.getInstance();
        coinManagerController.setMainController(this);

        holderCells = new HolderCells(this);
    }

    @FXML
    private void onClickedNextPage() throws SQLException {
        setActionNone();
        
        if(albumInfo.getCurrentPage() == null || albumInfo.getCurrentPage().getNextPageId() == 0) return;

        Optional<Page> page = pageService.findOneById(albumInfo.getCurrentPage().getNextPageId());
        if (page.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR,
                    "Ошибка",
                    "Ошибка запроса к базе данных.",
                    "Следующая страница отсутствует.",
                    false);
            return;
        }

        albumInfo.setCurrentPage(page.get());
        albumInfo.setCurrentNumberPage(albumInfo.getCurrentNumberPage() + 1);

        showPage();
    }

    @FXML
    private void onClickedBackPage() throws SQLException {
        setActionNone();

        if(albumInfo.getCurrentPage() == null || albumInfo.getCurrentPage().getPreviousPageId() == 0) return;

        Optional<Page> page = pageService.findOneById(albumInfo.getCurrentPage().getPreviousPageId());
        if (page.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR,
                    "Ошибка",
                    "Ошибка запроса к базе данных.",
                    "Предыдущая страница отсутствует.",
                    false);
            return;
        }

        albumInfo.setCurrentPage(page.get());
        albumInfo.setCurrentNumberPage(albumInfo.getCurrentNumberPage() - 1);

        showPage();
    }

    public void showPage() throws SQLException {
        this.holderCells.clear();
        if (albumInfo.getCurrentPage() == null) {
            labelNumberPage.setText("0/0");
            labelTitlePage.setText("");
            return;
        } else {
            labelNumberPage.setText(albumInfo.getCurrentNumberPage() + "/" + albumInfo.getMaxNumberPage());
            labelTitlePage.setText(albumInfo.getCurrentPage().getTitle());
        }

        List<HolderCell> holderCells = holderCellService.findByPageId(albumInfo.getCurrentPage().getId());

        for(HolderCell holderCell : holderCells) {
            this.holderCells.setHolderCell(holderCellService.include(holderCell));
        }
    }

    public void setActionNone(){
        for(SampleHolderCellController holderCell : holderCells.getValue())
            holderCell.setVisibleFrame(false);
        coinManagerController.resetStatus();
        idCoin = 0;
        typeCoinAction = TypeCoinAction.NONE;
    }

    public boolean setActionAdd(int coinId, String title){
        idCoin = coinId;
        titleHolder = title;
        if(idCoin == 0) {
            setActionNone();
            return false;
        }
        typeCoinAction = TypeCoinAction.ADD;
        return true;
    }

    public void setActionEdit(){
        typeCoinAction = TypeCoinAction.EDIT;
    }

    public void setActionDelete(){
        typeCoinAction = TypeCoinAction.DELETE;
    }

    public boolean setAndCheckBorders(boolean forEmpty){
        boolean result = false;
        for(SampleHolderCellController holderCell : holderCells.getValue())
            if(holderCell.isEmpty() == forEmpty){
                result = true;
                holderCell.setVisibleFrame(true);
            }
        return result;
    }

    public void action(SampleHolderCellController controller) throws SQLException, IOException {
        switch (typeCoinAction) {
            case ADD -> {
                if(!controller.isEmpty()) return;
                Optional<Coin> coin = coinService.findOneById(idCoin);
                if (coin.isEmpty()) return;
                holderCellService.create(idCoin, albumInfo.getCurrentPage().getId(), controller.getColumn(), controller.getLine(), titleHolder);
            }
            case EDIT -> {
                Optional<HolderCell> holderCellOptional = holderCellService.findOneById(controller.getIdHolderCell());
                if (holderCellOptional.isEmpty()) return;

                AddCoinToPageController addCoinToPageController = coinManagerController.showChangeCoin(
                        holderCellOptional.get().getCoinId(),
                        holderCellOptional.get().getTitle());

                idCoin = addCoinToPageController.getIdCoin();
                titleHolder = addCoinToPageController.getTitle();
                if (idCoin == 0) break;

                holderCellService.update(holderCellOptional.get().getId(),
                        false,
                        idCoin,
                        holderCellOptional.get().getPageId(),
                        holderCellOptional.get().getColumn(),
                        holderCellOptional.get().getLine(),
                        titleHolder);
            }
            case DELETE -> {
                if(controller.isEmpty()) return;
                if (!AlertHelper.showAlert(Alert.AlertType.WARNING,
                        "Предупреждение",
                        "Точно хотите удалить монету?",
                        "Это действие не обратимо.",
                        true)) break;

                holderCellService.delete(controller.getIdHolderCell());
            }
        }
        setActionNone();
        showPage();
    }
}