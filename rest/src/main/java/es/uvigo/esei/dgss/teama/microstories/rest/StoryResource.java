package es.uvigo.esei.dgss.teama.microstories.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("microstory")
public class StoryResource {

    @EJB
    private StoryService storyService;

    @Path("recent")
    @GET
    public Response getLastStories(){
        List<Story> stories = storyService.findLastStories();
        return Response.ok(stories).build();
    }
}
