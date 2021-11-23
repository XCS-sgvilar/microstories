package es.uvigo.esei.dgss.teamA.microstories.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.Validate.inclusiveBetween;

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
    private Theme mainTheme;

    @Column(nullable = true, length = 15)
    @Enumerated(EnumType.STRING)
    private Theme secondaryTheme;

    @Column(nullable = false)
    private Boolean publicated;

    public Story() {

    }

    public Story(int id, Date date, String title, String content, Genre genre, Theme mainTheme, Theme secondaryTheme, Boolean publicated) {
        this.id = id;
        this.setDate(date);
        this.setTitle(title);
        this.setContent(content);
        this.setGenre(genre);
        this.setMainTheme(mainTheme);
        this.setSecondaryTheme(secondaryTheme);
        this.setPublicated(publicated);
    }

    public Story(Date date, String title, String content, Genre genre, Theme mainTheme, Theme secondaryTheme, Boolean publicated) {
        this.setDate(date);
        this.setTitle(title);
        this.setContent(content);
        this.setGenre(genre);
        this.setMainTheme(mainTheme);
        this.setSecondaryTheme(secondaryTheme);
        this.setPublicated(publicated);
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public void setDate(Date date) {
        requireNonNull(date, "date can't be null");
        inclusiveBetween(new Date(0), new Date(), date,
                "date must be previous to the current time"
        );

        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        requireNonNull(title, "title can't be null");
        inclusiveBetween(1, 80, title.length(), "title must have a length between 1 and 80");
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        requireNonNull(content, "content can't be null");
        inclusiveBetween(1, 1000, content.length(), "content must have a length between 1 and 1000");
        this.content = content;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        requireNonNull(genre, "genre can't be null");
        this.genre = genre;
    }

    public Theme getMainTheme() {
        return mainTheme;
    }

    public void setMainTheme(Theme mainTheme) {
        requireNonNull(mainTheme, "mainTheme can't be null");
        this.mainTheme = mainTheme;
    }

    public Theme getSecondaryTheme() {
        return secondaryTheme;
    }

    public void setSecondaryTheme(Theme secondaryTheme) {
        if (!this.getMainTheme().equals(secondaryTheme)) {
            this.secondaryTheme = secondaryTheme;
        }
    }

    public Boolean getPublicated() {
        return publicated;
    }

    public void setPublicated(Boolean publicated) {
        requireNonNull(publicated, "publicated can't be null");
        this.publicated = publicated;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", date=" + date +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", genre=" + genre +
                ", mainTheme=" + mainTheme +
                ", secondaryTheme=" + secondaryTheme +
                ", publicated=" + publicated +
                '}';
    }
}
