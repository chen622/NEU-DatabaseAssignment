package data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class DVDTableProperty {
    private final SimpleIntegerProperty dvdId = new SimpleIntegerProperty();
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty releaseYear = new SimpleStringProperty();
    private final SimpleStringProperty director = new SimpleStringProperty();
    private final SimpleStringProperty genre = new SimpleStringProperty();
    private final SimpleStringProperty cast = new SimpleStringProperty();
    private final SimpleStringProperty library = new SimpleStringProperty();

    public DVDTableProperty(Integer dvdId,String title,String releaseYear,String director,String genre,String cast,String library) {
        setCast(cast);
        setDirector(director);
        setDvdId(dvdId);
        setGenre(genre);
        setLibrary(library);
        setTitle(title);
        setReleaseYear(releaseYear);
    }

    public int getDvdId() {
        return dvdId.get();
    }

    public SimpleIntegerProperty dvdIdProperty() {
        return dvdId;
    }

    public void setDvdId(int dvdId) {
        this.dvdId.set(dvdId);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getReleaseYear() {
        return releaseYear.get();
    }

    public SimpleStringProperty releaseYearProperty() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear.set(releaseYear);
    }

    public String getDirector() {
        return director.get();
    }

    public SimpleStringProperty directorProperty() {
        return director;
    }

    public void setDirector(String director) {
        this.director.set(director);
    }

    public String getGenre() {
        return genre.get();
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public String getCast() {
        return cast.get();
    }

    public SimpleStringProperty castProperty() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast.set(cast);
    }

    public String getLibrary() {
        return library.get();
    }

    public SimpleStringProperty libraryProperty() {
        return library;
    }

    public void setLibrary(String library) {
        this.library.set(library);
    }
}
