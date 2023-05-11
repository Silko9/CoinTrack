package shapov.cointrack.controllers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import shapov.cointrack.models.HolderCell;


import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;
import static shapov.cointrack.controllers.AlbumsController.*;

class AlbumsControllerTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void onClickedAlbum() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        HolderPosition holderPosition1 = new HolderPosition(0, 0, false);
        HolderPosition holderPosition2 = new HolderPosition(2, 3, false);
        HolderPosition holderPosition3 = new HolderPosition(-1, -1, true);

        AlbumsController albumsController = new AlbumsController();
        Method method = AlbumsController.class.getDeclaredMethod("getPositionHolder", int.class, int.class);
        method.setAccessible(true);

        assertEquals(method.invoke(albumsController, 1, 1), holderPosition1);
        assertEquals(method.invoke(albumsController, 142, 1), holderPosition1);
        assertEquals(method.invoke(albumsController, 1, 134), holderPosition1);
        assertEquals(method.invoke(albumsController, 142, 134), holderPosition1);

        assertEquals(method.invoke(albumsController, 571, 270), holderPosition2);
        assertEquals(method.invoke(albumsController, 671, 270), holderPosition2);
        assertEquals(method.invoke(albumsController, 571, 404), holderPosition2);
        assertEquals(method.invoke(albumsController, 671, 404), holderPosition2);

        assertEquals(method.invoke(albumsController, 280, 1), holderPosition3);
        assertEquals(method.invoke(albumsController, 380, 1), holderPosition3);
        assertEquals(method.invoke(albumsController, 280, 404), holderPosition3);
        assertEquals(method.invoke(albumsController, 380, 404), holderPosition3);
    }
}