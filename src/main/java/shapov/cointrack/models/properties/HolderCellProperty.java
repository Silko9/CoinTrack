package shapov.cointrack.models.properties;

import javafx.beans.property.*;
import shapov.cointrack.models.HolderCell;

public class HolderCellProperty {
    private final IntegerProperty id;
    private final IntegerProperty coinId;
    private final BooleanProperty available;
    private final IntegerProperty pageId;
    private final IntegerProperty column;
    private final IntegerProperty line;
    private final StringProperty title;

    public HolderCellProperty(HolderCell holderCell) {
        this.id = new SimpleIntegerProperty(holderCell.getId());
        this.coinId = new SimpleIntegerProperty(holderCell.getCoinId());
        this.available = new SimpleBooleanProperty(holderCell.isAvailable());
        this.pageId = new SimpleIntegerProperty(holderCell.getPageId());
        this.column = new SimpleIntegerProperty(holderCell.getColumn());
        this.line = new SimpleIntegerProperty(holderCell.getLine());
        this.title = new SimpleStringProperty(holderCell.getTitle());
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

    public int getCoinId() {
        return coinId.get();
    }

    public IntegerProperty coinIdProperty() {
        return coinId;
    }

    public void setCoinId(int coinId) {
        this.coinId.set(coinId);
    }

    public boolean isAvailable() {
        return available.get();
    }

    public BooleanProperty availableProperty() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available.set(available);
    }

    public int getPageId() {
        return pageId.get();
    }

    public IntegerProperty pageIdProperty() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId.set(pageId);
    }

    public int getColumn() {
        return column.get();
    }

    public IntegerProperty columnProperty() {
        return column;
    }

    public void setColumn(int column) {
        this.column.set(column);
    }

    public int getLine() {
        return line.get();
    }

    public IntegerProperty lineProperty() {
        return line;
    }

    public void setLine(int line) {
        this.line.set(line);
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

