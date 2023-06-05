package shapov.cointrack.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import shapov.cointrack.controllers.editControllers.ActionType;
import shapov.cointrack.controllers.editControllers.AlbumEditorController;
import shapov.cointrack.controllers.editControllers.PageEditorController;
import shapov.cointrack.models.Album;
import shapov.cointrack.models.HolderCell;
import shapov.cointrack.models.Page;
import shapov.cointrack.models.Coin;
import shapov.cointrack.models.properties.AlbumProperty;
import shapov.cointrack.services.AlbumService;
import shapov.cointrack.services.CoinService;
import shapov.cointrack.services.HolderCellService;
import shapov.cointrack.services.PageService;
import shapov.cointrack.services.implement.AlbumServiceImpl;
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
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Optional;

public class AlbumsController implements Initializable {
    private final AlbumService albumService = new AlbumServiceImpl();
    private final PageService pageService = new PageServiceImpl();
    private final HolderCellService holderCellService = new HolderCellServiceImpl();
    private final CoinService coinService = new CoinServiceImpl();
    @FXML
    private Button btReset;
    @FXML
    private Label statusCoin;
    private ObservableList<AlbumProperty> albums;
    @FXML
    private Label labelTitlePage;
    @FXML
    private Label labelNumberPage;
    @FXML
    private TableView<AlbumProperty> tableAlbum;
    @FXML
    private TableColumn<AlbumProperty, String> columnAlbum;
    private List<Page> pages;
    private Page currentPage;
    private int maxPage;
    private AlbumProperty albumProperty;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            albums = album2property(albumService.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        columnAlbum.setCellValueFactory(albumStringCellDataFeatures -> albumStringCellDataFeatures.getValue().titleProperty());
        tableAlbum.setItems(albums);

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
        if(albumProperty == null || maxPage == 0 || currentPage.getNumber() == maxPage) return;
        currentPage = pages.get(currentPage.getNumber());
        showPage();
    }

    @FXML
    private void onClickedBackPage() throws SQLException {
        setActionNone();
        if(albumProperty == null || maxPage == 0 || currentPage.getNumber() == 1) return;
        currentPage = pages.get(currentPage.getNumber() - 2);
        showPage();
    }

    @FXML
    private void onClickedAddAlbum() throws IOException, SQLException {
        setActionNone();
        albumProperty = showEditorAlbum(ActionType.CREATE, new AlbumProperty(new Album("")));
        if(Objects.equals(albumProperty.getTitle(), "")) return;
        albumService.create(albumProperty.getTitle());
        updateAlbum();
    }

    @FXML
    private void onClickedEditAlbum() throws IOException, SQLException {
        setActionNone();
        if(albumProperty == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы изменить альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }
        albumProperty = showEditorAlbum(ActionType.EDIT, albumProperty);
        albumService.update(albumProperty.getId(), albumProperty.getTitle());
    }

    @FXML
    private void onClickedDeleteAlbum() throws SQLException {
        setActionNone();
        if (albumProperty == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы удалить альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }
        if (!AlertHelper.showAlert(Alert.AlertType.WARNING,
                "Предупреждение",
                "Точно хотите удалить альбом?",
                "Это действие не обратимо, все содерживое альбома тоже удалиться.",
                true)) return;

        albumService.delete(albumProperty.getId());
        albums.remove(albumProperty);
        albumProperty = null;
        currentPage = null;

        for(Page page : pages)
            removePage(page.getId());
        pages.clear();
        showPage();
    }

    @FXML
    private void onClickedAddCoin(MouseEvent mouseEvent) throws IOException {
        setActionNone();
        if (albumProperty == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы добавить монету в альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }

        if (maxPage == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "В альбоме нету страниц.",
                    "Чтобы добавить монету на страницу, добавте хотя бы одну страницу.",
                    false);
            return;
        }

        if(setAndCheckBorders(true)) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "На странице нет мест для новой монеты.",
                    "Чтобы добавить монету на страницу, освободите место на странице.",
                    false);
            return;
        }

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("add-coin-to-page.fxml"));
        Parent parentPage = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle("Выберите монету");
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(MainApplication.getPrimaryStage());
        Scene scene = new Scene(parentPage);
        addStage.setScene(scene);
        AddCoinToPageController controller = loader.getController();
        controller.setStage(addStage);
        addStage.showAndWait();

        idCoin = controller.getIdCoin();
        titleHolder = controller.getTitle();
        if(idCoin == 0) {
            setActionNone();
            return;
        }

        btReset.setDisable(false);
        statusCoin.setText("Выберите свободное место.");
        typeCoinAction = TypeCoinAction.ADD;
    }

    @FXML
    private void onClickedEditCoin() {
        setActionNone();
        if (albumProperty == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы изменить монету в альбоме, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }
        if (maxPage == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "В альбоме нету страниц.",
                    "Чтобы изменить монету на странице, добавте хотя бы одну страницу.",
                    false);
            return;
        }

        if(setAndCheckBorders(false)) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "На странице нет монет.",
                    "Чтобы изменить монету на странице, на ней должна быть хотя бы одна монета.",
                    false);
            return;
        }

        btReset.setDisable(false);
        statusCoin.setText("Выберите монету для изменения.");
        typeCoinAction = TypeCoinAction.EDIT;
    }

    @FXML
    private void onClickedDeleteCoin() {
        setActionNone();

        if(setAndCheckBorders(false)) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "На странице нет монет.",
                    "Чтобы удалить монету на странице, на ней должна быть хотя бы одна монета.",
                    false);
            return;
        }

        btReset.setDisable(false);
        statusCoin.setText("Выберите монету для удаления.");
        typeCoinAction = TypeCoinAction.DELETE;
    }


    @FXML
    private void onClickedAddPage(MouseEvent mouseEvent) throws SQLException, IOException {
        setActionNone();
        if(albumProperty == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы добавить страницу в альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }
        Page newPage = showEditorPage(ActionType.CREATE, new Page(albumProperty.getId(), pages.size() + 1, ""));
        if(Objects.equals(newPage.getTitle(), "")) return;
        pageService.create(newPage.getAlbumId(), newPage.getNumber(), newPage.getTitle());
        pages = pageService.findByAlbumId(albumProperty.getId());
        maxPage ++;
        currentPage = pages.get(maxPage - 1);
        showPage();
    }
    private enum TypeCoinAction{
        NONE,
        ADD,
        EDIT,
        DELETE
    }

    @FXML
    private void onClickedEditPage(MouseEvent mouseEvent) throws SQLException, IOException {
        setActionNone();
        if(albumProperty == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы изменить страницу в альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }
        if(maxPage == 0){
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "В альбоме нету страниц.",
                    "Чтобы изменить страницу, добавте хотя бы одну страницу.",
                    false);
            return;
        }
        currentPage = showEditorPage(ActionType.EDIT, currentPage);
        pageService.update(currentPage.getId(), currentPage.getAlbumId(), currentPage.getNumber(), currentPage.getTitle());
        showPage();
    }

    @FXML
    private void onClickedDeletePage(MouseEvent mouseEvent) throws SQLException {
        setActionNone();
        if (albumProperty == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы удалить стриницу альбома, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }
        if (maxPage == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "В альбоме нету страниц.",
                    "Чтобы удалить страницу, добавте хотя бы одну страницу.",
                    false);
            return;
        }
        if (!AlertHelper.showAlert(Alert.AlertType.WARNING,
                "Предупреждение",
                "Точно хотите удалть текущую страницу?",
                "Это действие не обратимо, все содерживое страницы тоже удалиться.",
                true)) return;

        pages.remove(currentPage);
        removePage(currentPage.getId());
        maxPage--;

        if(maxPage == 0) currentPage = null;
        else if(currentPage.getNumber() == 1) currentPage = pages.get(currentPage.getNumber());
        else if(maxPage == currentPage.getNumber() - 1) currentPage = pages.get(currentPage.getNumber() - 2);
        else {
            currentPage = pages.get(currentPage.getNumber() - 1);
            for (int i = 0; i < maxPage; i++) {
                Page page = pages.get(i);
                page.setNumber(i + 1);
                pageService.update(page.getId(), page.getAlbumId(), page.getNumber(), page.getTitle());
            }
        }

        showPage();
    }

    private AlbumProperty showEditorAlbum(ActionType actionType, AlbumProperty album) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("album-editor-view.fxml"));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle(actionType.getValue() + " альбом");
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(MainApplication.getPrimaryStage());
        Scene scene = new Scene(page);
        addStage.setScene(scene);
        AlbumEditorController controller = loader.getController();
        controller.setParams(actionType, addStage, album);
        addStage.showAndWait();
        return controller.getAlbum();
    }

    private Page showEditorPage(ActionType actionType, Page page) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("page-editor-view.fxml"));
        Parent parentPage = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle(actionType.getValue() + " страницу");
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(MainApplication.getPrimaryStage());
        Scene scene = new Scene(parentPage);
        addStage.setScene(scene);
        PageEditorController controller = loader.getController();
        controller.setParams(actionType, addStage, page);
        addStage.showAndWait();
        return controller.getPage();
    }

    private void updateAlbum() throws SQLException {
        albums.clear();
        albums.addAll(album2property(albumService.findAll()));
    }

    @FXML
    private void onClickAlbum() throws SQLException {
        setActionNone();
        albumProperty = tableAlbum.getSelectionModel().getSelectedItem();
        if(albumProperty == null) return;

        pages = pageService.findByAlbumId(albumProperty.getId());
        maxPage = pages.size();

        if(maxPage != 0) currentPage = pages.get(0);
        else currentPage = null;

        showPage();
    }

    private void showPage() throws SQLException {
        if (currentPage == null) {
            labelNumberPage.setText("0/0");
            labelTitlePage.setText("");
        } else {
            labelNumberPage.setText(currentPage.getNumber() + "/" + maxPage);
            labelTitlePage.setText(currentPage.getTitle());
        }

        for(ImageView imageView : imageViews)
            imageView.setImage(null);
        for(Label label : labels)
            label.setText("");

        if(currentPage == null) return;

        List<HolderCell> holderCells = holderCellService.findByPageId(currentPage.getId());
        for (HolderCell cell : holderCells)
            addImageAndTitleCoin(holderCellService.include(cell));
    }

    @FXML
    private void onResetCoin() {
        setActionNone();
    }

    private void setActionNone(){
        for(ImageView imageView : frames)
            imageView.setVisible(false);
        btReset.setDisable(true);
        idCoin = 0;
        statusCoin.setText("");
        typeCoinAction = TypeCoinAction.NONE;
    }

    private ObservableList<AlbumProperty> album2property(List<Album> albums) throws SQLException {
        ObservableList<AlbumProperty> albumProperties = FXCollections.observableArrayList();
        for(Album album : albums)
            albumProperties.add(new AlbumProperty(album));
        return albumProperties;
    }
    private void removePage(int id) throws SQLException {
        pageService.delete(id);
        for(HolderCell holderCell : holderCellService.findByPageId(id))
            holderCellService.delete(holderCell.getId());
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
        String idHolder = ((ImageView)mouseEvent.getSource()).getId();
        int line = 0;
        int column = 0;
        int idHolderInt = 0;
        switch (idHolder){
            case "frame1" -> {
                if(!frame1.isVisible()) return;
                line = 1;
                column = 1;
                idHolderInt = idHolders[0];
            }
            case "frame2" -> {
                if(!frame2.isVisible()) return;
                line = 1;
                column = 2;
                idHolderInt = idHolders[1];
            }
            case "frame3" -> {
                if(!frame3.isVisible()) return;
                line = 1;
                column = 3;
                idHolderInt = idHolders[2];
            }
            case "frame4" -> {
                if(!frame4.isVisible()) return;
                line = 1;
                column = 4;
                idHolderInt = idHolders[3];
            }
            case "frame5" -> {
                if(!frame5.isVisible()) return;
                line = 2;
                column = 1;
                idHolderInt = idHolders[4];
            }
            case "frame6" -> {
                if(!frame6.isVisible()) return;
                line = 2;
                column = 2;
                idHolderInt = idHolders[5];
            }
            case "frame7" -> {
                if(!frame7.isVisible()) return;
                line = 2;
                column = 3;
                idHolderInt = idHolders[6];
            }
            case "frame8" -> {
                if(!frame8.isVisible()) return;
                line = 2;
                column = 4;
                idHolderInt = idHolders[7];
            }
            case "frame9" -> {
                if(!frame9.isVisible()) return;
                line = 3;
                column = 1;
                idHolderInt = idHolders[8];
            }
            case "frame10" -> {
                if(!frame10.isVisible()) return;
                line = 3;
                column = 2;
                idHolderInt = idHolders[9];
            }
            case "frame11" -> {
                if(!frame11.isVisible()) return;
                line = 3;
                column = 3;
                idHolderInt = idHolders[10];
            }
            case "frame12" -> {
                if(!frame12.isVisible()) return;
                line = 3;
                column = 4;
                idHolderInt = idHolders[11];
            }
        }
        switch (typeCoinAction){
            case ADD -> {
                Optional<Coin> coin = coinService.findOneById(idCoin);
                if(coin.isEmpty()) return;
                holderCellService.create(idCoin, currentPage.getId(), column, line, titleHolder);
            }
            case EDIT -> {
                Optional<HolderCell> holderCellOptional = holderCellService.findOneById(idHolderInt);
                if(holderCellOptional.isEmpty()) return;

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(MainApplication.class.getResource("add-coin-to-page.fxml"));
                Parent parentPage = loader.load();
                Stage addStage = new Stage();
                addStage.setTitle("Изменение монеты");
                addStage.initModality(Modality.WINDOW_MODAL);
                addStage.initOwner(MainApplication.getPrimaryStage());
                Scene scene = new Scene(parentPage);
                addStage.setScene(scene);
                AddCoinToPageController controller = loader.getController();
                controller.setStage(addStage);
                controller.setCoinParams(holderCellOptional.get().getCoinId(), holderCellOptional.get().getTitle());
                controller.getBAdd().setText("Изменить");
                addStage.showAndWait();

                idCoin = controller.getIdCoin();
                titleHolder = controller.getTitle();
                if(idCoin == 0) break;

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
                        true)) return;

                holderCellService.delete(idHolderInt);
            }
        }

        setActionNone();
        showPage();
    }
    private boolean setAndCheckBorders(boolean forEmpty){
        boolean isVoid = true;
        for (int i = 0; i < imageViews.size(); i++)
            if((imageViews.get(i).getImage() == null) == forEmpty) {
                frames.get(i).setVisible(true);
                isVoid = false;
            }
        return isVoid;
    }
}
