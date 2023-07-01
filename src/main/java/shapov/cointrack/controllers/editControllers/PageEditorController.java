package shapov.cointrack.controllers.editControllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lombok.Getter;
import shapov.cointrack.AlertHelper;
import shapov.cointrack.models.Page;
import shapov.cointrack.services.PageService;
import shapov.cointrack.services.implement.PageServiceImpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PageEditorController {

    PageService pageService = new PageServiceImpl();

    @FXML
    private TextField textName;

    @FXML
    private Button btAccept;

    private Stage stage;

    @Getter
    private Page page;

    private ActionType actionType;

    public void setParams(ActionType actionType, Stage stage, Page page) {
        this.stage = stage;
        this.page = page;
        this.actionType = actionType;
        if (actionType == ActionType.EDIT)
            textName.setText(page.getTitle());
        btAccept.setText(actionType.getValue());
    }

    @FXML
    private void onClickedAccept(MouseEvent mouseEvent) throws SQLException {
        if(Objects.equals(textName.getText(), "")) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Заполните поле.",
                    "Чтобы подтвердить действие, заполните поле.",
                    false);
            return;
        }

        if(textName.getText().length() >= 20) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Превышена максимальная длина заголовка страницы.",
                    "Длина заголовка страницы не должна превышать 20 символов.",
                    false);
            return;
        }

        page.setTitle(textName.getText());

        switch(actionType){
            case CREATE -> {
                pageService.create(page.getAlbumId(), page.getPreviousPageId(), page.getNextPageId(), page.getTitle());
                List<Page> pages = pageService.findByAlbumId(page.getAlbumId());
                page = pageService.findOneById(pages.get(pages.size() - 1).getId()).get();
                Optional<Page> previousPage = pageService.findOneById(page.getPreviousPageId());
                if(previousPage.isEmpty()) break;
                pageService.update(page.getPreviousPageId(), page.getAlbumId(), previousPage.get().getPreviousPageId(), page.getId(), previousPage.get().getTitle());
            }
            case EDIT -> pageService.update(page.getId(), page.getAlbumId(), page.getPreviousPageId(), page.getNextPageId(), page.getTitle());
        }

        stage.close();
    }

    @FXML
    private void onClickedCancel(MouseEvent mouseEvent) {
        stage.close();
    }
}