package shapov.cointrack.controllers.editControllers;

import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import java.util.Objects;
import javafx.scene.control.Alert;
import shapov.cointrack.AlertHelper;
import shapov.cointrack.models.Album;
import shapov.cointrack.models.properties.AlbumProperty;
import shapov.cointrack.services.AlbumService;
import shapov.cointrack.services.implement.AlbumServiceImpl;

public class AlbumEditorController {
    
    @FXML
    private TextField textName;
    
    @FXML
    private Button btAccept;
    
    private Stage stage;

    @Getter
    private AlbumProperty album;
    
    private ActionType actionType;
    
    private final AlbumService albumService = new AlbumServiceImpl();

    public void setParams(AlbumProperty album, Stage stage) {
        this.album = album;
        textName.setText(album.getTitle());
        actionType = ActionType.EDIT;
        initialize(stage);
    }
    
    public void setParams(Stage stage){
        album = new AlbumProperty(new Album(""));
        actionType = ActionType.CREATE;
        initialize(stage);
    }

    @FXML
    private void onClickedAccept() throws SQLException {
        if(Objects.equals(textName.getText(), "")) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Заполните поле.",
                    "Чтобы подтвердить действие, заполните поле.",
                    false);
            return;
        }

        if(textName.getText().length() > 25) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Превышена максимальная длина названия альбома.",
                    "Длина названия альбома не должна превышать 25 символов.",
                    false);
            return;
        }
        
        album.setTitle(textName.getText());
        
        switch(actionType){
            case CREATE -> albumService.create(textName.getText());
            case EDIT -> albumService.update(album.getId(), textName.getText());
        }
        
        stage.close();
    }

    @FXML
    private void onClickedCancel() {
        stage.close();
    }
    
    private void initialize(Stage stage){
        this.stage = stage;
        btAccept.setText(actionType.getValue());
    }
}
