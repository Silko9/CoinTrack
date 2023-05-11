package shapov.cointrack.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;

public class AlbumsController {
    private final static int BEGINNING_MIDL = 280;
    private final static int END_MIDL = 380;
    private final static int WIDTH_ONE_HOLDER = 143;
    private final static int HEIGHT_ONE_HOLDER = 135;

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

    protected record HolderPosition(int line, int column, boolean isMidl) {}
}
