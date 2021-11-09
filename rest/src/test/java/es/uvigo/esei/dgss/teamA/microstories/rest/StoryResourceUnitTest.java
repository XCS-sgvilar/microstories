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

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.containsStorysInOrder;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.stories;
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
        assertThat((List<Story>)response.getEntity(), containsStorysInOrder(stories.subList(0,5)));
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
}