package es.uvigo.esei.dgss.teamA.microstories.service;


import es.uvigo.esei.dgss.teamA.microstories.entities.Genre;
import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset;
import es.uvigo.esei.dgss.teamA.microstories.entities.Theme;
import org.hamcrest.collection.IsEmptyCollection;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.CleanupUsingScript;
import org.jboss.arquillian.persistence.ShouldMatchDataSet;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJBTransactionRolledbackException;
import javax.inject.Inject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.containsStorysInOrder;
import static es.uvigo.esei.dgss.teamA.microstories.entities.IsEqualToStory.equalToStory;
import static es.uvigo.esei.dgss.teamA.microstories.entities.StoryDataset.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

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
        final Integer end = SIZE * (page + 1);
        final List<Story> recentStories = storiesOf(TEXT, start, end);

        List<Story> storyList = this.facade.findStoriesByText(TEXT, page, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE));
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextPageNull() {
        List<Story> storyList = this.facade.findStoriesByText(TEXT, null, SIZE);

        final List<Story> recentStories = storiesOf(TEXT, 0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE));
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextPageNegative() {
        List<Story> storyList = this.facade.findStoriesByText(TEXT, -1, SIZE);

        final List<Story> recentStories = storiesOf(TEXT, 0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE));
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextSizeNull() {
        List<Story> storyList = this.facade.findStoriesByText(TEXT, 0, null);

        final List<Story> recentStories = storiesOf(TEXT, 0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE));
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextSizeNegative() {
        List<Story> storyList = this.facade.findStoriesByText(TEXT, 0, -1);

        final List<Story> recentStories = storiesOf(TEXT, 0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE));
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesByTextEmpty() {
        List<Story> storyList = this.facade.findStoriesByText("", 0, SIZE);

        final List<Story> recentStories = allStoriesSortedByRecent().subList(0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE));
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test(expected = EJBTransactionRolledbackException.class)
    public void testFindStoriesByTextNull() {
        List<Story> storyList = this.facade.findStoriesByText(null, 0, SIZE);
    }


    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesWithoutFilters() {
        List<Story> storyList = this.facade.findStories(null, null, null, null, null, null);

        final List<Story> recentStories = allStoriesSortedByRecent().subList(0, SIZE);

        Assert.assertNotNull(storyList);
        Assert.assertThat(storyList, hasSize(SIZE));
        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesFilterByGenre() {
        List<Story> storyList = this.facade.findStories(Genre.STORY, null, null, null, null, null);

        final List<Story> recentStories = allStoriesSortedByRecent().stream()
                .filter(i -> i.getGenre().equals(Genre.STORY))
                .limit(SIZE)
                .collect(Collectors.toList());

        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesFilterByTheme() {
        List<Story> storyList = this.facade.findStories(null, Theme.ADVENTURE, null, null, null, null);

        final List<Story> recentStories = allStoriesSortedByRecent().stream()
                .filter(i -> i.getMainTheme().equals(Theme.ADVENTURE) ||
                        (i.getSecondaryTheme() != null && i.getSecondaryTheme().equals(Theme.ADVENTURE)))
                .limit(SIZE)
                .collect(Collectors.toList());

        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesFilterByInitDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.DECEMBER, 31, 12, 59, 59);
        Date initDate = calendar.getTime();

        List<Story> storyList = this.facade.findStories(null, null, initDate, null, null, null);

        final List<Story> recentStories = allStoriesSortedByRecent().stream()
                .filter(i -> i.getDate().after(initDate))
                .limit(SIZE)
                .collect(Collectors.toList());

        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesFilterByEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.DECEMBER, 31, 12, 59, 59);
        Date endDate = calendar.getTime();

        List<Story> storyList = this.facade.findStories(null, null, null, endDate, null, null);

        final List<Story> recentStories = allStoriesSortedByRecent().stream()
                .filter(i -> i.getDate().before(endDate))
                .limit(SIZE)
                .collect(Collectors.toList());

        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesFilterByDateBetween() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.DECEMBER, 31, 12, 59, 59);
        Date initDate = calendar.getTime();
        calendar.set(2021, Calendar.DECEMBER, 31, 12, 59, 59);
        Date endDate = calendar.getTime();

        List<Story> storyList = this.facade.findStories(null, null, initDate, endDate, null, null);

        final List<Story> recentStories = allStoriesSortedByRecent().stream()
                .filter(i -> i.getDate().after(initDate))
                .filter(i -> i.getDate().before(endDate))
                .limit(SIZE)
                .collect(Collectors.toList());

        assertThat(recentStories, containsStorysInOrder(storyList));
    }

    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesFilterByDateBetweenIncorrectRange() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, Calendar.DECEMBER, 31, 12, 59, 59);
        Date initDate = calendar.getTime();
        calendar.set(2015, Calendar.DECEMBER, 31, 12, 59, 59);
        Date endDate = calendar.getTime();

        List<Story> storyList = this.facade.findStories(null, null, initDate, endDate, null, null);

        assertThat(storyList, is(IsEmptyCollection.empty()));
    }


    @Test
    @ShouldMatchDataSet("stories.xml")
    public void testFindStoriesFilterMiltipleFields() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, Calendar.DECEMBER, 31, 12, 59, 59);
        Date initDate = calendar.getTime();
        calendar.set(2021, Calendar.DECEMBER, 31, 12, 59, 59);
        Date endDate = calendar.getTime();
        List<Story> storyList = this.facade.findStories(Genre.STORY, Theme.HORROR, initDate, endDate, null, null);

        final List<Story> recentStories = allStoriesSortedByRecent().stream()
                .filter(i -> i.getGenre().equals(Genre.STORY))
                .filter(i -> i.getMainTheme().equals(Theme.HORROR) ||
                        (i.getSecondaryTheme() != null && i.getSecondaryTheme().equals(Theme.HORROR)))
                .filter(i -> i.getDate().after(initDate))
                .filter(i -> i.getDate().before(endDate))
                .limit(SIZE)
                .collect(Collectors.toList());

        assertThat(recentStories, containsStorysInOrder(storyList));
    }
}
