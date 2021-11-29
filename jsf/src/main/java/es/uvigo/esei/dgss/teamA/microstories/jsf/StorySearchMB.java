package es.uvigo.esei.dgss.teamA.microstories.jsf;

import es.uvigo.esei.dgss.teamA.microstories.entities.Genre;
import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.entities.Theme;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@SessionScoped
@Named(value = "storySearchMB")
public class StorySearchMB implements Serializable {

    private static final int PAGE = 0;
    private static final int MAX_ITEMS = 9;

    @EJB
    StoryService storyService;

    private List<Story> findStories;

    private String text;

    private int page;

    private Genre genre;
    private Theme theme;
    private String publicatedDate;
    private Date initialDate; //Initial D
    private Date endDate;

    public StorySearchMB(){

    }

    @PostConstruct
    public void init() {
    }

    public String searchByText() {

        this.findStories = storyService.findStoriesByText(this.text, PAGE, MAX_ITEMS);

        this.page = 0;

        return "search";

    }

    public String toAdvanceSearchView(){
        return "advanceSearch";
    }


    public String advancedSearch(){
        calculateInitialEndDate();
        //genre and/or theme is null if not specified
        //if no field is specified, all publicated stories will be shown
        this.findStories = storyService.findStories(this.genre, this.theme, this.initialDate, this.endDate, PAGE, MAX_ITEMS);
        this.page = 0;

        return "advanceSearch";
    }

    public String getPreviousStories() {
        this.page = this.page - 1;
        this.findStories = storyService.findStoriesByText(this.text, this.page, MAX_ITEMS);

        return "search";
    }

    public String getPreviousStoriesAdvanced() {
        this.page = this.page - 1;
        this.findStories = storyService.findStories(this.genre, this.theme,
                this.initialDate, this.endDate, this.page, MAX_ITEMS);

        return "advanceSearch";
    }

    public String getNextStories() {
        this.page = this.page + 1;
        this.findStories = storyService.findStoriesByText(this.text, this.page, MAX_ITEMS);

        return "search";
    }

    public String getNextStoriesAdvanced() {
        this.page = this.page + 1;
        this.findStories = storyService.findStories(this.genre, this.theme,
                this.initialDate, this.endDate, this.page, MAX_ITEMS);

        return "advancedSearch";
    }

    public Boolean isPreviousButtonDisabled(){
        return this.page <= 0;
    }

    public Boolean isNextButtonDisabled(){
        return storyService.findStoriesByText(this.text, this.page + 1, MAX_ITEMS).isEmpty();
    }

    public Boolean isNextButtonDisabledAdvanced(){
        return storyService.findStories(this.genre, this.theme,
                this.initialDate, this.endDate, this.page + 1, MAX_ITEMS).isEmpty();
    }

    private void calculateInitialEndDate(){
        Date today = new Date();
        LocalDateTime ldtToday = LocalDateTime.ofInstant(today.toInstant(), ZoneId.systemDefault());
        LocalDateTime ldtInit;

        this.endDate = today;
        if(this.publicatedDate == null){
            this.publicatedDate = "CUALQUIER MOMENTO";
        }
        //"Cualquier momento" is the default case
        switch(this.publicatedDate){
            case "HOY":
                ldtInit = ldtToday.minusDays(1);
                break;
            case "ESTA SEMANA":
                ldtInit = ldtToday.minusWeeks(1);
                break;
            case "ESTE MES":
                ldtInit = ldtToday.minusMonths(1);
                break;
            case "ESTE AÑO":
                ldtInit = ldtToday.minusYears(1);
                break;
            default:
                ldtInit = ldtToday.minusYears(40);
                break;
        }
        this.initialDate = Date.from(ldtInit.atZone(ZoneId.systemDefault()).toInstant());
    }

    private String getViewId() {
        return FacesContext.getCurrentInstance().getViewRoot().getViewId();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setGenre(String genre) {
        this.genre = Genre.valueOf(genre);
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public void setTheme(String theme) {
        this.theme = Theme.valueOf(theme);
    }

    public String getPublicated_date() {
        return publicatedDate;
    }

    public void setPublicated_date(String publicatedDate) {
        this.publicatedDate = publicatedDate;
    }

    public List<Story> getFindStories() {
        return findStories;
    }

    public void setFindStories(List<Story> findStories) {
        this.findStories = findStories;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
