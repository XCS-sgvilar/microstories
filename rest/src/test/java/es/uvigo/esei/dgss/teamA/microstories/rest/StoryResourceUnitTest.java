package es.uvigo.esei.dgss.teamA.microstories.rest;

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
import java.util.List;

import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.containsStorysInOrder;
import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.equalToStory;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.*;
import static es.uvigo.esei.dgss.teamA.microstories.http.util.HasHttpStatus.hasOkStatus;
import static java.util.Arrays.asList;
import static org.easymock.EasyMock.expect;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
    public void tearDown() throws Exception{
        verifyAll();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testGetLatestStories(){
        final List<Story> stories = asList(stories());

        expect(facade.findLastStories()).andReturn(stories);

        replayAll();

        final Response response = resource.getLastestStories();

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
        assertThat((List<Story>)response.getEntity(), containsStorysInOrder(stories.subList(0,6)));
    }

    @Test
    public void testGetLatestStoriesLessThanSix(){
        final List<Story> stories = asList(stories()).subList(0,3);
        //TODO: como limitar a menos de seis relatos en vez de cien??
        expect(facade.findLastStories()).andReturn(stories);

        replayAll();
        //??
        final Response response = resource.getLastestStories();

        assertThat(response, hasOkStatus());
        assertThat(response.getEntity(), is(instanceOf(List.class)));
        assertThat(((List<Story>)response.getEntity()).subList(0,3), containsStorysInOrder(stories));
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


}