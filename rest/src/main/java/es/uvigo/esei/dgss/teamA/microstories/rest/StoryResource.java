package es.uvigo.esei.dgss.teamA.microstories.rest;

import es.uvigo.esei.dgss.teamA.microstories.entities.Genre;
import es.uvigo.esei.dgss.teamA.microstories.entities.Publicated;
import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.entities.Theme;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;

import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Resource that represents the stories in the application.
 */
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
            @DefaultValue("10") @QueryParam("maxItems") Integer maxItems) {
        //http://localhost:8080/microstories-2122-teamA/rest/api/microstory?contains=A
        List<Story> stories = new ArrayList<>();
        if ((page == null || page >= 0) && (maxItems == null || maxItems >= 0)) {
            stories = storyService.findStoriesByText(contains, page, maxItems);
        }

        return Response.ok(stories).build();

    }

    @Path("search")
    @GET
    public Response searchStories(
            @QueryParam("theme") Theme theme,
            @QueryParam("genre") Genre genre,
            @QueryParam("publicated") Publicated publicated,
            @DefaultValue("0") @QueryParam("page") Integer page,
            @DefaultValue("10") @QueryParam("maxItems") Integer maxItems) {
        //http://localhost:8080/microstories-2122-teamA/rest/api/microstory?genre=STORY&theme=ADVENTURE&publicated=TODAY
        List<Story> stories = new ArrayList<>();
        Date initDate;
        Date endDate;
        if (publicated != null) {
            initDate = getInitDate(publicated);
            endDate = new Date();
        } else {
            initDate = null;
            endDate = null;
        }

        if ((page == null || page >= 0) && (maxItems == null || maxItems >= 0)) {
            stories = storyService.findStories(genre, theme, initDate, endDate, page, maxItems);
        }

        if (stories.isEmpty()) {
            return Response.ok("No entries").build();
        }
        return Response.ok(stories).build();

    }

    @Path("user/{login}/microstory")
    @GET
    public Response getUserMicrostories(@NotNull @PathParam("login") String login,
                                        @DefaultValue("0") @QueryParam("page") Integer page,
                                        @DefaultValue("10") @QueryParam("maxItems") Integer maxItems) {
        //http://localhost:8080/microstories-2122-teamA/rest/api/microstory/user/Bruno/microstory

        List<Story> stories = new ArrayList<>();

        if ((page == null || page >= 0) && (maxItems == null || maxItems >= 0)) {
            stories = this.storyService.findStoriesByCurrentUser(login, page, maxItems);
        }

        if (stories.isEmpty()) {
            return Response.ok("No entries").build();
        }
        return Response.ok(stories).build();
    }

    public Date getInitDate(Publicated publicated) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        Date initDate = null;
        switch (publicated) {
            case THIS_YEAR:
                cal.set(Calendar.DAY_OF_YEAR, 1);
                initDate = cal.getTime();
                break;
            case THIS_WEEK:
                cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
                initDate = cal.getTime();
                break;
            case THIS_MONTH:
                cal.set(Calendar.DAY_OF_MONTH, 1);
                initDate = cal.getTime();
                break;
            case TODAY:
                initDate = cal.getTime();
                break;
            default:
                break;
        }
        return initDate;
    }


}
