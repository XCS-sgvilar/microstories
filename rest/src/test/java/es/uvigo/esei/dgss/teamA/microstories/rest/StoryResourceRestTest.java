package es.uvigo.esei.dgss.teamA.microstories.rest;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.rest.GenericTypes.ListStoryType;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.Cleanup;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.TestExecutionPhase;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.containsStorysInOrder;
import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.equalToStory;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.EXISTENT_CONTENT_FRAGMENT;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.EXISTENT_GENRE;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.EXISTENT_GENRE_STRING;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.EXISTENT_ID;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.EXISTENT_PUBLICATED_STRING;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.EXISTENT_THEME;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.EXISTENT_THEME_STRING;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.NON_EXISTENT_ID;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.recentStories;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.storiesOf;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.storiesSearch;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.storyWithId;
import static es.uvigo.esei.dgss.teamA.microstories.http.util.HasHttpStatus.hasBadRequestStatus;
import static es.uvigo.esei.dgss.teamA.microstories.http.util.HasHttpStatus.hasOkStatus;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class StoryResourceRestTest {
    private final static String BASE_PATH = "api/microstory/";
    private final static String BASE_PATH_ADD_PARAMS = "api/microstory?";
    private final static String BASE_PATH_ADD_PARAMS_SEARCH = "api/microstory/search?";
    private final static String CONTAINS_PARAM_PATH = "contains=";
    private final static String THEME_PARAM_PATH = "theme=";
    private final static String GENRE_PARAM_PATH = "genre=";
    private final static String PUBLICATED_PARAM_PATH = "publicated=";
    private final static String ADD_PARAM_CHAR = "&";
    private final static String PAGE_PARAM_PATH = "page=";
    private final static String MAX_ITEMS_PARAM_PATH = "maxItems=";

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(StoryResource.class)
                .addPackage(StoryService.class.getPackage())
                .addPackage(Story.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jboss-web.xml")
                .addAsWebInfResource("web.xml")
                .addAsResource("arquillian.extension.persistence.properties")
                .addAsResource("arquillian.extension.persistence.dbunit.properties")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    @InSequence(1)
    @UsingDataSet("stories.xml")
    @Cleanup(phase = TestExecutionPhase.NONE)
    public void beforeList() {
    }

    @Test
    @InSequence(2)
    @RunAsClient
    public void testList(
            @ArquillianResteasyResource(BASE_PATH + "recent")
                    ResteasyWebTarget webTarget
    ) throws Exception {
        final Response response = webTarget.request().get();

        assertThat(response, hasOkStatus());

        final List<Story> list = ListStoryType.readEntity(response);
        assertThat(list, containsStorysInOrder(recentStories().subList(0, 6)));
    }

    @Test
    @InSequence(3)
    @ShouldMatchDataSet("stories.xml")
    @CleanupUsingScript({
            "cleanup.sql", "cleanup-autoincrement.sql"
    })
    public void afterList() {
    }

    @Test
    @InSequence(4)
    @UsingDataSet("stories.xml")
    @Cleanup(phase = TestExecutionPhase.NONE)
    public void beforeGet() {
    }

    @Test
    @InSequence(5)
    @RunAsClient
    public void testGet(
            @ArquillianResteasyResource(BASE_PATH + EXISTENT_ID)
                    ResteasyWebTarget webTarget
    ) throws Exception {
        final Response response = webTarget.request().get();

        assertThat(response, hasOkStatus());

        final Story story = response.readEntity(Story.class);
        final Story expected = storyWithId(EXISTENT_ID);

        assertThat(story, is(equalToStory(expected)));
    }

    @Test
    @InSequence(6)
    @ShouldMatchDataSet("stories.xml")
    @CleanupUsingScript({
            "cleanup.sql", "cleanup-autoincrement.sql"
    })
    public void afterGet() {
    }

    @Test
    @InSequence(7)
    @UsingDataSet("stories.xml")
    @Cleanup(phase = TestExecutionPhase.NONE)
    public void beforeGetNonExistent() {
    }

    @Test
    @InSequence(8)
    @RunAsClient
    public void testGetNonExistent(
            @ArquillianResteasyResource(BASE_PATH + NON_EXISTENT_ID)
                    ResteasyWebTarget webTarget
    ) throws Exception {
        final Response response = webTarget.request().get();

        assertThat(response, hasBadRequestStatus());
    }

    @Test
    @InSequence(9)
    @ShouldMatchDataSet("stories.xml")
    @CleanupUsingScript({
            "cleanup.sql", "cleanup-autoincrement.sql"
    })
    public void afterGetNonExistent() {
    }

    @Test
    @InSequence(10)
    @UsingDataSet("stories.xml")
    @Cleanup(phase = TestExecutionPhase.NONE)
    public void beforeSearchByText() {
    }

    @Test
    @InSequence(11)
    @RunAsClient
    public void testSearchByText(
            @ArquillianResteasyResource(BASE_PATH_ADD_PARAMS + CONTAINS_PARAM_PATH + EXISTENT_CONTENT_FRAGMENT)
                    ResteasyWebTarget webTarget
    ) throws Exception {
        // Asumimos una ruta tipo /api/microstory?contains={text}
        // Test busqueda por contenido sin especificar parametros opcionales
        int start = 0;
        int end = 10;
        final Response response = webTarget.request().get();

        assertThat(response, hasOkStatus());

        final List<Story> list = ListStoryType.readEntity(response);

        assertThat(list, containsStorysInOrder(storiesOf(EXISTENT_CONTENT_FRAGMENT, start, end)));
    }

    @Test
    @InSequence(12)
    @ShouldMatchDataSet("stories.xml")
    @CleanupUsingScript({
            "cleanup.sql", "cleanup-autoincrement.sql"
    })
    public void afterSearchByText() {
    }

    @Test
    @InSequence(13)
    @UsingDataSet("stories.xml")
    @Cleanup(phase = TestExecutionPhase.NONE)
    public void beforeSearchStories() {
    }

    @Test
    @InSequence(14)
    @RunAsClient
    public void testSearchStories(
            @ArquillianResteasyResource(BASE_PATH_ADD_PARAMS_SEARCH + THEME_PARAM_PATH + EXISTENT_THEME_STRING + ADD_PARAM_CHAR + GENRE_PARAM_PATH + EXISTENT_GENRE_STRING + ADD_PARAM_CHAR + PUBLICATED_PARAM_PATH + EXISTENT_PUBLICATED_STRING)
                    ResteasyWebTarget webTarget
    ) throws Exception {
        int start = 0;
        int end = 10;
        Calendar calendar = Calendar.getInstance();
        calendar.set(calendar.get(Calendar.YEAR), Calendar.JANUARY, 1, 0, 0, 0);
        Date initDate = calendar.getTime();
        final Response response = webTarget.request().get();

        assertThat(response, hasOkStatus());

        final List<Story> list = ListStoryType.readEntity(response);

        assertThat(list, containsStorysInOrder(storiesSearch(EXISTENT_GENRE, EXISTENT_THEME, initDate, new Date(), start, end)));
    }

    @Test
    @InSequence(15)
    @ShouldMatchDataSet("stories.xml")
    @CleanupUsingScript({
            "cleanup.sql", "cleanup-autoincrement.sql"
    })
    public void afterSearchStories() {
    }


}
