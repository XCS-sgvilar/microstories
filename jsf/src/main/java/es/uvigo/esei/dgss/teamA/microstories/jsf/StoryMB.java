package es.uvigo.esei.dgss.teamA.microstories.jsf;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@SessionScoped
@Named(value = "storyMB")
public class StoryMB implements Serializable {

    @EJB
    StoryService storyService;

    List<Story> recentStories;


    public StoryMB() {
    }

    @PostConstruct
    public void init() {
        mostRecent();
    }

    public void mostRecent() {

        List<Story> queriedStories =storyService.findLastStories();
        this.recentStories = queriedStories
                .subList(0, Math.min(queriedStories.size(), 6))
                .stream()
                .map(this::truncateContent)
                .collect(Collectors.toList());
    }

    private Story truncateContent(Story story) {
        story.setContent(story.getContent().substring(0, 60));
        return story;
    }


    public List<Story> getRecentStories() {
        return recentStories;
    }

    public void setRecentStories(List<Story> recentStories) {
        this.recentStories = recentStories;
    }
}
