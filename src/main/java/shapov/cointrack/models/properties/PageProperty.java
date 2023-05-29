package shapov.cointrack.models.properties;

import javafx.beans.property.*;
import shapov.cointrack.models.Page;

public class PageProperty {
    private final IntegerProperty id;
    private final IntegerProperty albumId;
    private final IntegerProperty number;
    private final StringProperty title;

    public PageProperty(Page page) {
        this.id = new SimpleIntegerProperty(page.getId());
        this.albumId = new SimpleIntegerProperty(page.getAlbumId());
        this.number = new SimpleIntegerProperty(page.getNumber());
        this.title = new SimpleStringProperty(page.getTitle());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getAlbumId() {
        return albumId.get();
    }

    public IntegerProperty albumIdProperty() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId.set(albumId);
    }

    public int getNumber() {
        return number.get();
    }

    public IntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }
}

