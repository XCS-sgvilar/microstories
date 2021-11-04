package es.uvigo.esei.dgss.teama.microstories.entities;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity(name = "Story")
public class Story implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(length = 80, nullable = false)
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    @Column(nullable = false, length = 9)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false, length = 15)
    @Enumerated(EnumType.STRING)
    private Theme theme;

    @Column(nullable = false)
    private Boolean publicated;

    public Story(){

    }

    public Story(int id, Date date, String title, String content, Genre genre, Theme theme, Boolean publicated) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.content = content;
        this.genre = genre;
        this.theme = theme;
        this.publicated = publicated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Boolean getPublicated() {
        return publicated;
    }

    public void setPublicated(Boolean publicated) {
        this.publicated = publicated;
    }
}
