package es.uvigo.esei.dgss.teamA.microstories.rest;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("microstory")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StoryResource {

    @EJB
    private StoryService storyService;

    @Path("recent")
    @GET
    public Response getLastestStories() {
        List<Story> stories = storyService.findLastStories();
        List<Story> most_recent = new ArrayList<>();
        if (stories.size() >= 6) {
            most_recent = stories.subList(0, 6);
        } else {
            most_recent = stories;
        }

        return Response.ok(most_recent).build();
    }

}
