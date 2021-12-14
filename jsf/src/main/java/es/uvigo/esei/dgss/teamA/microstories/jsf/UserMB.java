package es.uvigo.esei.dgss.teamA.microstories.jsf;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.security.Principal;
import java.util.List;

/**
 * Class UserMB that acts as controller of user profile.
 */
@SessionScoped
@Named(value = "userMB")
public class UserMB implements Serializable {

    private static final int PAGE = 0;
    private static final int MAX_ITEMS = 9;

    @EJB
    StoryService storyService;

    @Inject
    private Principal currentUser;

    private List<Story> myStories;

    private int page;


    public UserMB() {
    }

    @PostConstruct
    public void init() {

    }

    public String toProfile() {
        this.page = 0;

        this.myStories = storyService.findStoriesByCurrentUser(currentUser.getName(), PAGE, MAX_ITEMS);

        return "profile";
    }

    public String getPreviousStories() {
        this.page = this.page - 1;

        this.myStories = storyService.findStoriesByCurrentUser(currentUser.getName(), this.page, MAX_ITEMS);

        return "profile";
    }

    public String getNextStories() {
        this.page = this.page + 1;

        this.myStories = storyService.findStoriesByCurrentUser(currentUser.getName(), this.page, MAX_ITEMS);

        return "profile";
    }

    public Boolean isPreviousButtonDisabled() {
        return this.page <= 0;
    }

    public Boolean isNextButtonDisabled() {
        return storyService.findStoriesByCurrentUser(currentUser.getName(), this.page + 1, MAX_ITEMS).isEmpty();
    }

    public List<Story> getMyStories() {
        return myStories;
    }

    public void setMyStories(List<Story> myStories) {
        this.myStories = myStories;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getNameUser() {
        return currentUser.getName();
    }

}
