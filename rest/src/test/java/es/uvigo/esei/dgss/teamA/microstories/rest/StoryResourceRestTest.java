package es.uvigo.esei.dgss.teamA.microstories.rest;

import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.containsStorysInAnyOrder;
import static org.junit.Assert.assertThat;
import static es.uvigo.esei.dgss.teamA.microstories.http.util.HasHttpStatus.hasOkStatus;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.stories;


import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.service.StoryService;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.extension.rest.client.ArquillianResteasyResource;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.persistence.*;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import es.uvigo.esei.dgss.teamA.microstories.rest.GenericTypes.ListStoryType;

import javax.ws.rs.core.Response;
import java.util.List;

@RunWith(Arquillian.class)
public class StoryResourceRestTest {
    private final static String BASE_PATH = "api/story/";

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
    public void beforeList() {}

    @Test
    @InSequence(2)
    @RunAsClient
    public void testList(
            @ArquillianResteasyResource(BASE_PATH)
                    ResteasyWebTarget webTarget
    ) throws Exception {
        final Response response = webTarget.request().get();

        assertThat(response, hasOkStatus());

        final List<Story> list = ListStoryType.readEntity(response);
        assertThat(list, containsStorysInAnyOrder(stories()));
    }

    @Test
    @InSequence(3)
    @ShouldMatchDataSet("owners.xml")
    @CleanupUsingScript({
            "cleanup.sql", "cleanup-autoincrement.sql"
    })
    public void afterList() {}


}
