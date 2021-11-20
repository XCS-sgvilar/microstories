package es.uvigo.esei.dgss.teamA.microstories.service;


import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.*;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import java.util.List;

import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.equalToStory;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.*;
import static org.hamcrest.CoreMatchers.is;
import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.containsStorysInOrder;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@RunWith(Arquillian.class)
@UsingDataSet("stories.xml")
@CleanupUsingScript({"cleanup.sql", "cleanup-autoincrement.sql"})
public class StoryServiceIntegrationTest {
    public static final String TEXT = "Aliquam";
    public static final int SIZE = 10;
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

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testGetStory() {
        final Story existentStory = existentStory();

        final Story actualStory = facade.getById(existentStory.getId());

        assertThat(actualStory, is(equalToStory(existentStory)));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testGetStoryNonExistent() {
        final Story actualStory = facade.getById(nonExistentId());
        assertNull(actualStory);
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextSuccessfully() {

        final Integer page = 1;
        final Integer start = page * SIZE;
        final Integer end = SIZE * (page+1);
        final List<Story> recentStories = storiesOf(TEXT, start, end);

        List<Story> storyList = this.facade.findStoriesByText(TEXT, page, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE) );
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextPageNull(){
        List<Story> storyList = this.facade.findStoriesByText(TEXT, null, SIZE);

        final List<Story> recentStories = storiesOf(TEXT, 0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE) );
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextPageNegative(){
        List<Story> storyList = this.facade.findStoriesByText(TEXT, -1, SIZE);

        final List<Story> recentStories = storiesOf(TEXT, 0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE) );
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextSizeNull(){
        List<Story> storyList = this.facade.findStoriesByText(TEXT, 0, null);

        final List<Story> recentStories = storiesOf(TEXT, 0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE) );
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextSizeNegative(){
        List<Story> storyList = this.facade.findStoriesByText(TEXT, 0, -1);

        final List<Story> recentStories = storiesOf(TEXT, 0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE) );
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextEmpty(){
        List<Story> storyList = this.facade.findStoriesByText("", 0, SIZE);

        final List<Story> recentStories = recentStories().subList(0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE) );
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    public void testFindStoriesByTextNull() {
        List<Story> storyList = this.facade.findStoriesByText(null, 0, SIZE);
    }
}
