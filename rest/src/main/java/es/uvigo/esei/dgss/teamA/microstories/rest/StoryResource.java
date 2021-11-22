package es.uvigo.esei.dgss.teamA.microstories.rest;

import es.uvigo.esei.dgss.teamA.microstories.entities.Genre;
import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.entities.Theme;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;


import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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

    @Path("{id}")
    @GET
    public Response get(@PathParam("id") int id) {
        final Story story = this.storyService.getById(id);

        if (story == null)
            throw new BadRequestException();
        else
            return Response.ok(story).build();

    }

    @GET
    public Response searchByText(
            @NotNull @QueryParam("contains") String contains,
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("maxItems") Integer maxItems){
        //http://localhost:8080/microstories-2122-teamA/rest/api/microstory?contains=A
        List<Story> stories = new ArrayList<>();
        if ((page == null || page >=0) && (maxItems == null || maxItems >=0)){
            stories = storyService.findStoriesByText(contains, page, maxItems); //  maxItems<=100 ??
        }

        if(stories.isEmpty()){
            return Response.ok("No entries").build();
        }
        return Response.ok(stories).build();

    }


}
