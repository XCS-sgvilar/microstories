package es.uvigo.esei.dgss.teamA.microstories.jsf;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;

@Named(value = "storyViewMB")
@SessionScoped
public class StoryViewMB implements Serializable {
	Story story;
	@EJB
	StoryService storyEJB;

	public void setStory(Story story) {
		this.story = story;
	}

	public String getStoryByid(int id) {
		story = storyEJB.getById(id);
		return "microstoryView";
	}

	public Story getStory() {
		return story;
	}

	


}
