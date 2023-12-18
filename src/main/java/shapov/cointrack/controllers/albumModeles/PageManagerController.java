package shapov.cointrack.controllers.albumModeles;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import shapov.cointrack.AlertHelper;
import shapov.cointrack.MainApplication;
import shapov.cointrack.controllers.AlbumInfo;
import shapov.cointrack.controllers.AlbumsController;
import shapov.cointrack.controllers.editControllers.ActionType;
import shapov.cointrack.controllers.editControllers.PageEditorController;
import shapov.cointrack.models.Page;
import shapov.cointrack.services.PageService;
import shapov.cointrack.services.implement.PageServiceImpl;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class PageManagerController implements Initializable {

    private final PageService pageService = new PageServiceImpl();

    private AlbumsController mainController;

    private static PageManagerController pageManagerController;

    public static PageManagerController getInstance(){
        return pageManagerController;
    }

    public void setMainController(AlbumsController mainController) {
        this.mainController = mainController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pageManagerController = this;
    }

    @FXML
    private void onClickedAddPage() throws SQLException, IOException {
        mainController.setActionNone();

        AlbumInfo albumInfo = mainController.getAlbumInfo();

        if(albumInfo.getCurrentAlbum() == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы добавить страницу в альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }

        int previousPageId = 0;
        if(albumInfo.getMaxNumberPage() != 0){
            previousPageId = albumInfo.getPages().get(albumInfo.getPages().size() - 1).getId();
        }


        Page newPage = showEditorPage(ActionType.CREATE,
                new Page(albumInfo.getCurrentAlbum().getId(),
                    previousPageId,
                    0,
                    ""));

        if(Objects.equals(newPage.getTitle(), "")) return;

        albumInfo.setPages(pageService.findByAlbumId(albumInfo.getCurrentAlbum().getId()));
        albumInfo.setMaxNumberPage(albumInfo.getMaxNumberPage() + 1);
        albumInfo.setCurrentPage(albumInfo.getPages().get(albumInfo.getMaxNumberPage() - 1));
        albumInfo.setCurrentNumberPage(albumInfo.getMaxNumberPage());

        mainController.showPage();
    }

    @FXML
    private void onClickedEditPage() throws IOException, SQLException {
        mainController.setActionNone();

        AlbumInfo albumInfo = mainController.getAlbumInfo();

        if(albumInfo.getCurrentAlbum() == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы изменить страницу в альбом, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }

        if(albumInfo.getMaxNumberPage() == 0){
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "В альбоме нету страниц.",
                    "Чтобы изменить страницу, добавте хотя бы одну страницу.",
                    false);
            return;
        }

        showEditorPage(ActionType.EDIT, albumInfo.getCurrentPage());

        mainController.showPage();
    }

    @FXML
    private void onClickedDeletePage() throws SQLException, IOException {
        mainController.setActionNone();

        AlbumInfo albumInfo = mainController.getAlbumInfo();

        if (albumInfo.getCurrentAlbum() == null) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "Выберите альбом из списка.",
                    "Чтобы удалить стриницу альбома, выберите один из них в списке всех альбомов.",
                    false);
            return;
        }

        if (albumInfo.getMaxNumberPage() == 0) {
            AlertHelper.showAlert(Alert.AlertType.WARNING,
                    "Предупреждение",
                    "В альбоме нету страниц.",
                    "Чтобы удалить страницу, добавте хотя бы одну страницу.",
                    false);
            return;
        }

        if (!AlertHelper.showAlert(Alert.AlertType.WARNING,
                "Предупреждение",
                "Точно хотите удалить текущую страницу?",
                "Это действие не обратимо, все содерживое страницы тоже удалиться.",
                true)) return;

        pageService.delete(albumInfo.getCurrentPage().getId());
        albumInfo.setMaxNumberPage(albumInfo.getMaxNumberPage() - 1);
        albumInfo.setCurrentNumberPage(albumInfo.getCurrentNumberPage() - 1);

        Optional<Page> previousPage = pageService.findOneById(albumInfo.getCurrentPage().getPreviousPageId());
        Optional<Page> nextPage = pageService.findOneById(albumInfo.getCurrentPage().getNextPageId());

        if (albumInfo.getMaxNumberPage() == 0)
            deletingSinglePage(albumInfo);
        else {
            if (albumInfo.getCurrentPage().getPreviousPageId() == 0) {
                if (nextPage.isEmpty()) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR,
                            "Ошибка",
                            "Ошибка запроса к базе данных.",
                            "",
                            false);
                    return;
                }
                deletingFirstPage(albumInfo, nextPage.get());
            } else {
                if (albumInfo.getCurrentPage().getNextPageId() == 0) {
                    if (previousPage.isEmpty()) {
                        AlertHelper.showAlert(Alert.AlertType.ERROR,
                                "Ошибка",
                                "Ошибка запроса к базе данных.",
                                "",
                                false);
                        return;
                    }
                    deleteLastPage(previousPage.get());
                } else {
                    if (nextPage.isEmpty() || previousPage.isEmpty()) {
                        AlertHelper.showAlert(Alert.AlertType.ERROR,
                                "Ошибка",
                                "Ошибка запроса к базе данных.",
                                "",
                                false);
                        return;
                    }
                    removingPageFromMiddle(previousPage.get(), nextPage.get());
                }
                albumInfo.setCurrentPage(previousPage.get());
            }
        }

        albumInfo.setPages(pageService.findByAlbumId(albumInfo.getCurrentAlbum().getId()));

        mainController.showPage();
    }

    private void deletingSinglePage(AlbumInfo albumInfo){
        albumInfo.setCurrentPage(null);
        albumInfo.setCurrentNumberPage(0);
        albumInfo.setPages(null);
    }

    private void deletingFirstPage(AlbumInfo albumInfo, Page nextPage) throws SQLException, IOException {
        nextPage.setPreviousPageId(0);
        pageService.update(nextPage.getId(),
                nextPage.getAlbumId(),
                0,
                nextPage.getNextPageId(),
                nextPage.getTitle());

        albumInfo.setCurrentNumberPage(albumInfo.getCurrentNumberPage() + 1);
        albumInfo.setCurrentPage(nextPage);
    }

    private void deleteLastPage(Page previousPage) throws SQLException, IOException {
        previousPage.setNextPageId(0);
        pageService.update(previousPage.getId(),
                previousPage.getAlbumId(),
                previousPage.getPreviousPageId(),
                0,
                previousPage.getTitle());
    }

    private void removingPageFromMiddle(Page previousPage, Page nextPage) throws SQLException, IOException {
        previousPage.setNextPageId(nextPage.getId());
        pageService.update(previousPage.getId(),
                previousPage.getAlbumId(),
                previousPage.getPreviousPageId(),
                previousPage.getNextPageId(),
                previousPage.getTitle());

        nextPage.setPreviousPageId(previousPage.getId());
        pageService.update(nextPage.getId(),
                nextPage.getAlbumId(),
                nextPage.getPreviousPageId(),
                nextPage.getNextPageId(),
                nextPage.getTitle());
    }

    private Page showEditorPage(ActionType actionType, Page page) throws IOException {
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
}
