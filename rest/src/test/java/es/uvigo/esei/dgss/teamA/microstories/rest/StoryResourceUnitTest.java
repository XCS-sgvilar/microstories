package es.uvigo.esei.dgss.teamA.microstories.rest;

import es.uvigo.esei.dgss.teamA.microstories.entities.Publicated;
import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.containsStorysInOrder;
import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.equalToStory;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.existentContentFragment;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.existentGenre;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.existentId;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.existentTheme;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.nonExistentContentFragment;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.EXISTENT_ID;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.stories;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.storiesOf;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.storiesSearch;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.storyWithId;
import static es.uvigo.esei.dgss.teamA.microstories.http.util.HasHttpStatus.hasOkStatus;
import static java.util.Arrays.asList;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(EasyMockRunner.class)
public class StoryResourceUnitTest extends EasyMockSupport {
    @TestSubject
    StoryResource resource = new StoryResource();

    @Mock
    private StoryService facade;

    @Mock
    private UriInfo uriInfo;

    @Mock
    private UriBuilder uriBuilder;

    @After
    public void tearDown() throws Exception {
        verifyAll();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetLatestStories() {
        final List<Story> stories = asList(stories());

        expect(facade.findLastStories()).andReturn(stories);

        replayAll();

        final Response response = resource.getLastestStories();

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
        assertThat((List<Story>) response.getEntity(), containsStorysInOrder(stories.subList(0, 6)));
    }

    @Test
    public void testGetLatestStoriesLessThanSix() {
        final List<Story> stories = asList(stories()).subList(0, 3);
        expect(facade.findLastStories()).andReturn(stories);

        replayAll();

        final Response response = resource.getLastestStories();

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
        assertThat(((List<Story>) response.getEntity()).subList(0, 3), containsStorysInOrder(stories));
    }

    @Test
    public void testGet() {
        final Story story = storyWithId(existentId());

        expect(facade.getById(story.getId()))
                .andReturn(story);

        replayAll();

        final Response response = resource.get(story.getId());

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(Story.class)));
        assertThat((Story) response.getEntity(), is(equalToStory(story)));
    }

    @Test(expected = BadRequestException.class)
    public void testGetMissing() {
        final int id = existentId();

        expect(facade.getById(id))
                .andReturn(null);

        replayAll();

        resource.get(id);
    }

    @Test
    public void testSearchByText() {
//        La idea es obtener una lista con historias con dicho texto en el titulo o en el contenido
        final List<Story> stories = storiesOf(existentContentFragment(), 0, 10);

        expect(facade.findStoriesByText(existentContentFragment(), null, null))
                .andReturn(stories); //Revisar cuantas devuelve
        replayAll();

        final Response response = resource.searchByText(existentContentFragment(), null, null);

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
        assertThat((List<Story>) response.getEntity(), containsStorysInOrder(stories));
    }

    @Test
    public void testSearchByTextWithParams() {
        final List<Story> stories = storiesOf(existentContentFragment(), 1, 6);

        expect(facade.findStoriesByText(existentContentFragment(), 1, 6))
                .andReturn(stories); //Revisar cuantas devuelve
        replayAll();

        final Response response = resource.searchByText(existentContentFragment(), 1, 6);

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
        assertThat((List<Story>) response.getEntity(), containsStorysInOrder(stories));
    }

    @Test
    public void testSearchByTextNoResult() {

        expect(facade.findStoriesByText(nonExistentContentFragment(), null, null))
                .andReturn(Collections.EMPTY_LIST); //Revisar cuantas devuelve
        replayAll();

        final Response response = resource.searchByText(nonExistentContentFragment(), null, null);

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
    }

    @Test
    public void testSearchStoriesNoFilters() {

        final List<Story> stories = storiesSearch(null, null, null, null, 0, 10);

        expect(facade.findStories(null, null, null, null, 0, 10))
                .andReturn(stories);
        replayAll();

        final Response response = resource.searchStories(null, null, null, 0, 10);

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
        assertTrue(!stories.isEmpty());
        assertThat((List<Story>) response.getEntity(), containsStorysInOrder(stories));
    }

    @Test
    public void testSearchStoriesByGenre() {

        final List<Story> stories = storiesSearch(existentGenre(), null, null, null, 0, 10);

        expect(facade.findStories(existentGenre(), null, null, null, 0, 10))
                .andReturn(stories);
        replayAll();

        final Response response = resource.searchStories(null, existentGenre(), null, 0, 10);

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
        assertTrue(!stories.isEmpty());
        assertThat((List<Story>) response.getEntity(), containsStorysInOrder(stories));
    }

    @Test
    public void testSearchStoriesByTheme() {

        final List<Story> stories = storiesSearch(null, existentTheme(), null, null, 0, 10);

        expect(facade.findStories(null, existentTheme(), null, null, 0, 10))
                .andReturn(stories);
        replayAll();

        final Response response = resource.searchStories(existentTheme(), null, null, 0, 10);

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
        assertTrue(!stories.isEmpty());
        assertThat((List<Story>) response.getEntity(), containsStorysInOrder(stories));
    }
}