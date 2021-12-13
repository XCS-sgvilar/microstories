package es.uvigo.esei.dgss.teamA.microstories.jsf;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
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

    private List<Story> myStories;

    //FIXME CHANGE USERNAME LOGGED
    private String nameUser = "Alexender Schidmt";

    private int page;


    public UserMB() {
    }

    @PostConstruct
    public void init() {

    }

    public String toProfile(){
        this.page = 0;

        //FIXME Cambiar por el metodo desarrollado
        //this.myStories = storyService.findStoriesByText("est", PAGE, MAX_ITEMS);

        return "profile";
    }

    public String getPreviousStories() {
        this.page = this.page - 1;

        //FIXME Cambiar por el metodo desarrollado
        //this.myStories = storyService.findStoriesByText("est", this.page, MAX_ITEMS);

        return "profile";
    }

    public String getNextStories() {
        this.page = this.page + 1;

        //FIXME Cambiar por el metodo desarrollado
        //this.myStories = storyService.findStoriesByText("est", this.page, MAX_ITEMS);

        return "profile";
    }

    public Boolean isPreviousButtonDisabled(){
        return this.page <= 0;
    }

    public Boolean isNextButtonDisabled(){
        return false;
        //FIXME CAMBIAR POR EL METODO DESARROLLADO
        //return storyService.findStoriesByText("est", this.page + 1, MAX_ITEMS).isEmpty();
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
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
