package shapov.cointrack.controllers;

import shapov.cointrack.models.Page;
import shapov.cointrack.models.properties.AlbumProperty;

import java.util.ArrayList;
import java.util.List;

public class AlbumInfo{

    private AlbumProperty currentAlbum;

    private Page currentPage;

    private List<Page> pages;

    private int maxNumberPage;

    private int currentNumberPage;

    public AlbumInfo(AlbumProperty currentAlbum, List<Page> pages){
        this.currentAlbum = currentAlbum;
        this.pages = pages;
        if(!pages.isEmpty()) {
            this.currentPage = pages.stream().filter(page -> page.getPreviousPageId() == 0).findFirst().get();
            this.currentNumberPage = 1;
        }
        else this.currentNumberPage = 0;
        maxNumberPage = pages.size();
    }

    public AlbumInfo(AlbumProperty currentAlbum){
        this(currentAlbum, new ArrayList<>());
    }

    public AlbumInfo() {
    }

    public AlbumProperty getCurrentAlbum() {
        return currentAlbum;
    }

    public void setCurrentAlbum(AlbumProperty currentAlbum) {
        this.currentAlbum = currentAlbum;
    }

    public Page getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Page currentPage) {
        this.currentPage = currentPage;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public int getMaxNumberPage() {
        return maxNumberPage;
    }

    public void setMaxNumberPage(int maxNumberPage) {
        this.maxNumberPage = maxNumberPage;
    }

    public int getCurrentNumberPage() {
        return currentNumberPage;
    }

    public void setCurrentNumberPage(int currentNumberPage) {
        this.currentNumberPage = currentNumberPage;
    }
}