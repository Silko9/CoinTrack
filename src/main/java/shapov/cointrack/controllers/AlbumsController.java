package shapov.cointrack.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shapov.cointrack.AlertHelper;
import shapov.cointrack.MainApplication;
import shapov.cointrack.controllers.albumModeles.AlbumsListController;
import shapov.cointrack.controllers.albumModeles.CoinManagerController;
import shapov.cointrack.controllers.albumModeles.PageManagerController;
import shapov.cointrack.controllers.editControllers.ActionType;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.models.Page;
import shapov.cointrack.models.Coin;
import shapov.cointrack.services.CoinService;
import shapov.cointrack.services.HolderCellService;
import shapov.cointrack.services.PageService;
import shapov.cointrack.services.implement.CoinServiceImpl;
import shapov.cointrack.services.implement.HolderCellServiceImpl;
import shapov.cointrack.services.implement.PageServiceImpl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

public class AlbumsController implements Initializable {
    
    private final HolderCellService holderCellService = new HolderCellServiceImpl();
    
    private final CoinService coinService = new CoinServiceImpl();

    private final PageService pageService = new PageServiceImpl();
    
    @FXML
    private Label labelTitlePage;
    
    @FXML
    private Label labelNumberPage;
    
    @FXML
    private ImageView coin1;

    @FXML
    private ImageView coin10;

    @FXML
    private ImageView coin11;

    @FXML
    private ImageView coin12;

    @FXML
    private ImageView coin2;

    @FXML
    private ImageView coin3;

    @FXML
    private ImageView coin4;

    @FXML
    private ImageView coin5;

    @FXML
    private ImageView coin6;

    @FXML
    private ImageView coin7;

    @FXML
    private ImageView coin8;

    @FXML
    private ImageView coin9;

    @FXML
    private Label title1;

    @FXML
    private Label title10;

    @FXML
    private Label title11;

    @FXML
    private Label title12;

    @FXML
    private Label title2;

    @FXML
    private Label title3;

    @FXML
    private Label title4;

    @FXML
    private Label title5;

    @FXML
    private Label title6;

    @FXML
    private Label title7;

    @FXML
    private Label title8;

    @FXML
    private Label title9;
    
    @FXML
    private ImageView frame1;
    
    @FXML
    private ImageView frame10;

    @FXML
    private ImageView frame11;

    @FXML
    private ImageView frame12;

    @FXML
    private ImageView frame2;

    @FXML
    private ImageView frame3;

    @FXML
    private ImageView frame4;

    @FXML
    private ImageView frame5;

    @FXML
    private ImageView frame6;

    @FXML
    private ImageView frame7;

    @FXML
    private ImageView frame8;

    @FXML
    private ImageView frame9;
    

    private final List<ImageView> imageViews = new ArrayList<>();

    private final List<Label> labels = new ArrayList<>();

    private final List<ImageView> frames = new ArrayList<>();
    
    
    private int idCoin;
    
    private String titleHolder;
    
    private TypeCoinAction typeCoinAction = TypeCoinAction.NONE;
    
    private final int[] idHolders = new int[12];

    @Getter
    @Setter
    private AlbumInfo albumInfo;
    
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

        albumInfo = new AlbumInfo();

        imageViews.add(coin1);
        imageViews.add(coin2);
        imageViews.add(coin3);
        imageViews.add(coin4);
        imageViews.add(coin5);
        imageViews.add(coin6);
        imageViews.add(coin7);
        imageViews.add(coin8);
        imageViews.add(coin9);
        imageViews.add(coin10);
        imageViews.add(coin11);
        imageViews.add(coin12);

        labels.add(title1);
        labels.add(title2);
        labels.add(title3);
        labels.add(title4);
        labels.add(title5);
        labels.add(title6);
        labels.add(title7);
        labels.add(title8);
        labels.add(title9);
        labels.add(title10);
        labels.add(title11);
        labels.add(title12);

        frames.add(frame1);
        frames.add(frame2);
        frames.add(frame3);
        frames.add(frame4);
        frames.add(frame5);
        frames.add(frame6);
        frames.add(frame7);
        frames.add(frame8);
        frames.add(frame9);
        frames.add(frame10);
        frames.add(frame11);
        frames.add(frame12);

        for(ImageView imageView : frames) {
            imageView.setVisible(false);
            imageView.setPreserveRatio(false);
        }
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
        if (albumInfo.getCurrentPage() == null) {
            labelNumberPage.setText("0/0");
            labelTitlePage.setText("");
        } else {
            labelNumberPage.setText(albumInfo.getCurrentNumberPage() + "/" + albumInfo.getMaxNumberPage());
            labelTitlePage.setText(albumInfo.getCurrentPage().getTitle());
        }

        for(ImageView imageView : imageViews)
            imageView.setImage(null);
        for(Label label : labels)
            label.setText("");

        if(albumInfo.getCurrentPage() == null) return;

        List<HolderCell> holderCells = holderCellService.findByPageId(albumInfo.getCurrentPage().getId());
        for (HolderCell cell : holderCells)
            addImageAndTitleCoin(holderCellService.include(cell));
    }

    public void setActionNone(){
        for(ImageView imageView : frames)
            imageView.setVisible(false);
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
        boolean isVoid = true;
        for (int i = 0; i < imageViews.size(); i++)
            if((imageViews.get(i).getImage() == null) == forEmpty) {
                frames.get(i).setVisible(true);
                isVoid = false;
            }
        return isVoid;
    }

    private void addImageAndTitleCoin(HolderCell holderCell){
        Image image;
        try {
            String path = File.separator + "shapov" + File.separator + "cointrack" + File.separator + "pictures" + File.separator + holderCell.getCoin().getPicturePath();
            InputStream inputStream = getClass().getResourceAsStream(path);
            assert inputStream != null;
            image = new Image(inputStream);
        }
        catch (Exception e){
            String path = File.separator + "shapov" + File.separator + "cointrack" + File.separator + "pictures" + File.separator + "coin_0.png";
            InputStream inputStream = getClass().getResourceAsStream(path);
            assert inputStream != null;
            image = new Image(inputStream);
        }
        imageViews.get((holderCell.getLine() * 4 - 4) + (holderCell.getColumn() -1)).setImage(image);
        labels.get((holderCell.getLine() * 4 - 4) + (holderCell.getColumn() -1)).setText(holderCell.getTitle());
        idHolders[(holderCell.getLine() * 4 - 4) + (holderCell.getColumn() -1)] = holderCell.getId();
    }

    @FXML
    private void onClickedHolder(MouseEvent mouseEvent) throws SQLException, IOException {
        String idHolder = ((ImageView) mouseEvent.getSource()).getId();
        int line = 0;
        int column = 0;
        int idHolderInt = 0;
        switch (idHolder) {
            case "frame1" -> {
                if (!frame1.isVisible()) return;
                line = 1;
                column = 1;
                idHolderInt = idHolders[0];
            }
            case "frame2" -> {
                if (!frame2.isVisible()) return;
                line = 1;
                column = 2;
                idHolderInt = idHolders[1];
            }
            case "frame3" -> {
                if (!frame3.isVisible()) return;
                line = 1;
                column = 3;
                idHolderInt = idHolders[2];
            }
            case "frame4" -> {
                if (!frame4.isVisible()) return;
                line = 1;
                column = 4;
                idHolderInt = idHolders[3];
            }
            case "frame5" -> {
                if (!frame5.isVisible()) return;
                line = 2;
                column = 1;
                idHolderInt = idHolders[4];
            }
            case "frame6" -> {
                if (!frame6.isVisible()) return;
                line = 2;
                column = 2;
                idHolderInt = idHolders[5];
            }
            case "frame7" -> {
                if (!frame7.isVisible()) return;
                line = 2;
                column = 3;
                idHolderInt = idHolders[6];
            }
            case "frame8" -> {
                if (!frame8.isVisible()) return;
                line = 2;
                column = 4;
                idHolderInt = idHolders[7];
            }
            case "frame9" -> {
                if (!frame9.isVisible()) return;
                line = 3;
                column = 1;
                idHolderInt = idHolders[8];
            }
            case "frame10" -> {
                if (!frame10.isVisible()) return;
                line = 3;
                column = 2;
                idHolderInt = idHolders[9];
            }
            case "frame11" -> {
                if (!frame11.isVisible()) return;
                line = 3;
                column = 3;
                idHolderInt = idHolders[10];
            }
            case "frame12" -> {
                if (!frame12.isVisible()) return;
                line = 3;
                column = 4;
                idHolderInt = idHolders[11];
            }
        }
        switch (typeCoinAction) {
            case ADD -> {
                Optional<Coin> coin = coinService.findOneById(idCoin);
                if (coin.isEmpty()) return;
                holderCellService.create(idCoin, albumInfo.getCurrentPage().getId(), column, line, titleHolder);
            }
            case EDIT -> {
                Optional<HolderCell> holderCellOptional = holderCellService.findOneById(idHolderInt);
                if (holderCellOptional.isEmpty()) return;

                AddCoinToPageController controller = coinManagerController.showChangeCoin(
                        holderCellOptional.get().getCoinId(),
                        holderCellOptional.get().getTitle());

                idCoin = controller.getIdCoin();
                titleHolder = controller.getTitle();
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
                if (!AlertHelper.showAlert(Alert.AlertType.WARNING,
                        "Предупреждение",
                        "Точно хотите удалить монету?",
                        "Это действие не обратимо.",
                        true)) break;

                holderCellService.delete(idHolderInt);
            }
        }

        setActionNone();
        showPage();
    }
}
