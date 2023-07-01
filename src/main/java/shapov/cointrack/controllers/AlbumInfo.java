package shapov.cointrack.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shapov.cointrack.models.Page;
import shapov.cointrack.models.properties.AlbumProperty;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class AlbumInfo{

    private AlbumProperty currentAlbum;

    private Page currentPage;

    private List<Page> pages;

    private int maxNumberPage;

    private int currentNumberPage;

    public AlbumInfo(AlbumProperty currentAlbum, List<Page> pages) throws Exception{
        this.currentAlbum = currentAlbum;
        this.pages = pages;
        if(!pages.isEmpty()) {
            this.currentPage = pages.stream().filter(page -> page.getPreviousPageId() == 0).findFirst().get();
            this.currentNumberPage = 1;
        }
        else this.currentNumberPage = 0;
        maxNumberPage = pages.size();
    }

    public AlbumInfo(AlbumProperty currentAlbum) throws Exception {
        this(currentAlbum, new ArrayList<>());
    }
}
