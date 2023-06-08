package shapov.cointrack.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import shapov.cointrack.controllers.editControllers.ActionType;
import static shapov.cointrack.controllers.editControllers.ActionType.CREATE;
import static shapov.cointrack.controllers.editControllers.ActionType.EDIT;
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
    private void onClickedAddAlbum() throws IOException, SQLException {
        mainController.setActionNone();
        
        Optional<AlbumProperty> album = showEditorAlbum();

        if(album.isEmpty() || "".equals(album.get().getTitle())) return;

        albumsProperty.setAll(album2property(albumService.findAll()));

        mainController.setCurrent(album.get());
    }

    @FXML
    private void onClickedEditAlbum() throws IOException, SQLException {
        mainController.setActionNone();
        
        if(mainController.getCurrentAlbum() == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы изменить альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }
        
       showEditorAlbum(mainController.getCurrentAlbum());
    }

    @FXML
    private void onClickedDeleteAlbum() throws SQLException {
        mainController.setActionNone();
        
        if (mainController.getCurrentAlbum() == null) {
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

        albumService.delete(mainController.getCurrentAlbum().getId());
        albumsProperty.remove(mainController.getCurrentAlbum());
        
        mainController.setCurrent();
    }
    
    @FXML
    private void onClickAlbum() throws SQLException {
        mainController.setActionNone();
        AlbumProperty albumProperty = tableAlbum.getSelectionModel().getSelectedItem();
        mainController.setCurrent(albumProperty, pageService.findByAlbumId(albumProperty.getId()));
    }
    
    private Optional<AlbumProperty> showEditorAlbum() throws IOException, SQLException {
        return showEditorAlbum(ActionType.CREATE, null);
    }
    
    private Optional<AlbumProperty> showEditorAlbum(AlbumProperty album) throws IOException, SQLException {
        return showEditorAlbum(ActionType.EDIT, album);
    }

    private Optional<AlbumProperty> showEditorAlbum(ActionType actionType, AlbumProperty album) throws IOException{
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
