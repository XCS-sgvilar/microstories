package es.uvigo.esei.dgss.teamA.microstories.jsf;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

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

    public String getPreviousStories() {
        this.page = this.page - 1;
        this.findStories = storyService.findStoriesByText(this.text, this.page, MAX_ITEMS);

        return "search";
    }

    public String getNextStories() {
        this.page = this.page + 1;
        this.findStories = storyService.findStoriesByText(this.text, this.page, MAX_ITEMS);

        return "search";
    }

    public Boolean isPreviousButtonDisabled(){
        return this.page <= 0;
    }

    public Boolean isNextButtonDisabled(){
        return storyService.findStoriesByText(this.text, this.page + 1, MAX_ITEMS).isEmpty();
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
