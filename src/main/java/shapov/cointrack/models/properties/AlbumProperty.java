package shapov.cointrack.models.properties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.NoArgsConstructor;
import shapov.cointrack.models.Album;

@NoArgsConstructor
public class AlbumProperty {
    private IntegerProperty id;
    private StringProperty title;
    public AlbumProperty(Album album){
        this.id = new SimpleIntegerProperty(album.getId());
        this.title = new SimpleStringProperty(album.getTitle());
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
