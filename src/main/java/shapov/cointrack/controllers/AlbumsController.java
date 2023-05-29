package shapov.cointrack.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import shapov.cointrack.AlertHelper;
import shapov.cointrack.MainApplication;
import shapov.cointrack.controllers.editControllers.ActionType;
import shapov.cointrack.controllers.editControllers.AlbumEditorController;
import shapov.cointrack.models.Album;
import shapov.cointrack.models.Coin;
import shapov.cointrack.models.properties.AlbumProperty;
import shapov.cointrack.models.properties.CoinProperty;
import shapov.cointrack.models.properties.CountryProperty;
import shapov.cointrack.services.AlbumService;
import shapov.cointrack.services.implement.AlbumServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class AlbumsController implements Initializable {
    private final AlbumService albumService = new AlbumServiceImpl();
    private ObservableList<AlbumProperty> albums;
    private final static int BEGINNING_MIDL = 280;
    private final static int END_MIDL = 380;
    private final static int WIDTH_ONE_HOLDER = 143;
    private final static int HEIGHT_ONE_HOLDER = 135;
    @FXML
    private Label labelTitlePage;
    @FXML
    private ImageView imageAlbum;
    @FXML
    private Label labelNumberPage;
    @FXML
    private TableView<AlbumProperty> tableAlbum;
    @FXML
    private TableColumn<AlbumProperty, String> columnAlbum;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            albums = album2property(albumService.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        columnAlbum.setCellValueFactory(albumStringCellDataFeatures -> albumStringCellDataFeatures.getValue().titleProperty());
        tableAlbum.setItems(albums);
    }

    @FXML
    private void onClickedAlbum(MouseEvent mouseEvent) {
        final int x = (int) mouseEvent.getX();
        final int y = (int) mouseEvent.getY();

        HolderPosition holderPosition = getPositionHolder(x, y);
    }

    private HolderPosition getPositionHolder(final int x, final int y) {
        final int line, column;

        if (x < BEGINNING_MIDL)
            column = (x / WIDTH_ONE_HOLDER);
        else if (x > END_MIDL)
            column = (x - (END_MIDL - BEGINNING_MIDL)) / WIDTH_ONE_HOLDER;
        else return new HolderPosition(-1, -1, true);

        line = (y / HEIGHT_ONE_HOLDER);

        return new HolderPosition(line, column, false);
    }

    @FXML
    private void onClickedNextPage(MouseEvent mouseEvent) {
    }

    @FXML
    private void onClickedBackPage(MouseEvent mouseEvent) {
    }

    @FXML
    private void onClickedAddAlbum(MouseEvent mouseEvent) throws IOException, SQLException {
        String title = showEditorAlbum(ActionType.CREATE);
        if(title == null) return;
        albumService.create(title);
        updateAlbum();
    }

    @FXML
    private void onClickedEditAlbum(MouseEvent mouseEvent) throws IOException, SQLException {
        AlbumProperty albumProperty = tableAlbum.getSelectionModel().getSelectedItem();
        if(albumProperty == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, "Предупреждение", "Выберите альбом из списка.", "Чтобы изменить альбом, выберите один из них в списке всех альбомов.");
            return;
        }
        String title = showEditorAlbum(ActionType.EDIT);
        albumService.update(albumProperty.getId(), title);
    }

    @FXML
    private void onClickedDeleteAlbum(MouseEvent mouseEvent) {
    }

    @FXML
    private void onClickedAddCoin(MouseEvent mouseEvent) {
    }

    @FXML
    private void onClickedEditCoin(MouseEvent mouseEvent) {
    }

    @FXML
    private void onClickedDeleteCoin(MouseEvent mouseEvent) {
    }

    @FXML
    private void onClickedAddPage(MouseEvent mouseEvent) {
    }

    @FXML
    private void onClickedEditPage(MouseEvent mouseEvent) {
    }

    @FXML
    private void onClickedDeletePage(MouseEvent mouseEvent) {
    }

    private String showEditorAlbum(ActionType actionType) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource("album-editor-view.fxml"));
        Parent page = loader.load();
        Stage addStage = new Stage();
        addStage.setTitle(actionType.getValue());
        addStage.initModality(Modality.WINDOW_MODAL);
        addStage.initOwner(MainApplication.getPrimaryStage());
        Scene scene = new Scene(page);
        addStage.setScene(scene);
        AlbumEditorController controller = loader.getController();
        controller.setParams(actionType, addStage);
        addStage.showAndWait();
        return controller.getText();
    }

    private void updateAlbum() throws SQLException {
        albums.clear();
        albums.addAll(album2property(albumService.findAll()));
    }

    protected record HolderPosition(int line, int column, boolean isMidl) {}

    private ObservableList<AlbumProperty> album2property(List<Album> albums) throws SQLException {
        ObservableList<AlbumProperty> albumProperties = FXCollections.observableArrayList();
        for(Album album : albums)
            albumProperties.add(new AlbumProperty(album));
        return albumProperties;
    }

}
