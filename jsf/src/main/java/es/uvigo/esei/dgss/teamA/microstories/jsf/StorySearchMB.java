package es.uvigo.esei.dgss.teamA.microstories.jsf;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named(value = "storySearchMB")
public class StorySearchMB implements Serializable {

    private static final int PAGE = 0;
    private static final int MAX_ITEMS = 10;

    @EJB
    StoryService storyService;

    private List<Story> findStories;

    private String text;

    public StorySearchMB(){

    }

    @PostConstruct
    public void init() {

    }

    public String searchByText() {

        this.findStories = storyService.findStoriesByText(this.text, PAGE, MAX_ITEMS);

        return "WEB-INF/templates/search";

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
}
