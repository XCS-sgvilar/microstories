package es.uvigo.esei.dgss.teamA.microstories.service;


import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.service.StoryService;
import es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.*;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.List;

import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.recentStories;
import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.containsStorysInOrder;

import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
@UsingDataSet("stories.xml")
@CleanupUsingScript({"cleanup.sql", "cleanup-autoincrement.sql"})
public class StoryServiceIntegrationTest {
    @Inject
    private StoryService facade;

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addClasses(StoryService.class, StoryDataset.class)
                .addPackage(es.uvigo.esei.dgss.teamA.microstories.service.util.security.RoleCaller.class.getPackage())
                .addPackage(Story.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jboss-web.xml")
                .addAsResource("arquillian.extension.persistence.properties")
                .addAsResource("arquillian.extension.persistence.dbunit.properties")
                .addAsWebInfResource("beans.xml", "beans.xml");
    }


    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testListLast() {
        final List<Story> recentStories = recentStories();
        final List<Story> dbStories = facade.findLastStories();

        assertThat(recentStories, containsStorysInOrder(dbStories));
    }
}
