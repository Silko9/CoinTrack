package shapov.cointrack.controllers.albumModeles;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.Setter;
import shapov.cointrack.AlertHelper;
import shapov.cointrack.MainApplication;
import shapov.cointrack.controllers.AlbumInfo;
import shapov.cointrack.controllers.AlbumsController;
import shapov.cointrack.controllers.editControllers.ActionType;
import shapov.cointrack.controllers.editControllers.AlbumEditorController;
import shapov.cointrack.models.Album;
import shapov.cointrack.models.properties.AlbumProperty;
import shapov.cointrack.services.AlbumService;
import shapov.cointrack.services.PageService;
import shapov.cointrack.services.implement.AlbumServiceImpl;
import shapov.cointrack.services.implement.PageServiceImpl;

public class AlbumsListController implements Initializable {

    @Setter
    private AlbumsController mainController;
    
    private final AlbumService albumService = new AlbumServiceImpl();
    
    private final PageService pageService = new PageServiceImpl();
    
    @FXML
    private TableView<AlbumProperty> tableAlbum;
    
    @FXML
    private TableColumn<AlbumProperty, String> columnAlbum;
    
    private ObservableList<AlbumProperty> albumsProperty;

    private static AlbumsListController albumsListController;

    public static AlbumsListController getInstance(){
        return albumsListController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        albumsListController = this;

        try {
            albumsProperty = album2property(albumService.findAll());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        columnAlbum.setCellValueFactory(albumStringCellDataFeatures -> albumStringCellDataFeatures.getValue().titleProperty());
        tableAlbum.setItems(albumsProperty);
    }
    
    @FXML
    private void onClickedAddAlbum() throws Exception {
        mainController.setActionNone();

        AlbumProperty album = showEditorAlbum();

        if("".equals(album.getTitle())) return;

        albumsProperty.setAll(album2property(albumService.findAll()));

        mainController.setAlbumInfo(new AlbumInfo());
        mainController.showPage();
    }

    @FXML
    private void onClickedEditAlbum() throws IOException {
        mainController.setActionNone();

        AlbumInfo albumInfo = mainController.getAlbumInfo();

        if(albumInfo.getCurrentAlbum() == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы изменить альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }
        
       showEditorAlbum(albumInfo.getCurrentAlbum());
    }

    @FXML
    private void onClickedDeleteAlbum() throws SQLException {
        mainController.setActionNone();

        AlbumInfo albumInfo = mainController.getAlbumInfo();

        if (albumInfo.getCurrentAlbum() == null) {
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

        albumService.delete(albumInfo.getCurrentAlbum().getId());
        albumsProperty.remove(albumInfo.getCurrentAlbum());
        mainController.showPage();
    }
    
    @FXML
    private void onClickAlbum() throws SQLException {
        mainController.setActionNone();

        if(tableAlbum.getSelectionModel().isEmpty()) return;

        AlbumProperty albumProperty = tableAlbum.getSelectionModel().getSelectedItem();
        try {
            mainController.setAlbumInfo(new AlbumInfo(albumProperty, pageService.findByAlbumId(albumProperty.getId())));
        }
        catch (Exception e){
                AlertHelper.showAlert(Alert.AlertType.ERROR,
                        "Ошибка",
                        "Ошибка поиска в базе данных.",
                        "Не удалось найти первую страницу в альбоме.",
                        false);
                return;
        }

        mainController.showPage();
    }
    
    private AlbumProperty showEditorAlbum() throws IOException {
        return showEditorAlbum(ActionType.CREATE, null);
    }
    
    private AlbumProperty showEditorAlbum(AlbumProperty album) throws IOException {
        return showEditorAlbum(ActionType.EDIT, album);
    }

    private AlbumProperty showEditorAlbum(ActionType actionType, AlbumProperty album) throws IOException{
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
        switch(actionType){
            case CREATE -> controller.setParams(addStage);
            case EDIT -> controller.setParams(album, addStage);
        }
        addStage.showAndWait();
        return controller.getAlbum();
    }
    
    private ObservableList<AlbumProperty> album2property(List<Album> albums) throws SQLException {
        ObservableList<AlbumProperty> albumProperties = FXCollections.observableArrayList();
        for(Album album : albums)
            albumProperties.add(new AlbumProperty(album));
        return albumProperties;
    }
}
