package es.uvigo.esei.dgss.teamA.microstories.entities;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StoryTest {

	private String author;
	private Date date;
	private String title;
	private String content;
	private Genre genre;
	private Theme mainTheme;
	private Theme secondaryTheme;
	private Boolean publicated;
	private List<Date> visitDate;

	private Date newDate;
	private Date futureDate;
	private String newTitle;
	private String newContent;
	private Genre newGenre;
	private Theme newMainTheme;
	private Theme newSecondaryTheme;
	private Boolean newPublicated;
	private List<Date> newVisitDate;

	private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);

	@Before
	public void setUp() throws Exception {
		this.date = new Date();
		this.author ="Yudkowsky";
		this.title = "Story 1";
		this.content = "This is the content of story 1";
		this.genre = Genre.STORY;
		this.mainTheme = Theme.ADVENTURE;
		this.secondaryTheme = Theme.HISTORIC;
		this.publicated = true;
		this.visitDate = new ArrayList<>();

		final int oneDay = 24*60*60*1000;
		this.newDate = new Date(System.currentTimeMillis() - oneDay);
		this.futureDate = new Date(System.currentTimeMillis() + oneDay);
		this.newTitle = "New Story 1";
		this.newContent = "This is the new content of story 1";
		this.newGenre = Genre.NANOSTORY;
		this.newMainTheme = Theme.CHILD;
		this.newSecondaryTheme = Theme.SCIENCE_FICTION;
		this.newPublicated = false;
		this.newVisitDate = new ArrayList<>();
		this.newVisitDate.add(this.newDate);
		this.newVisitDate.add(this.futureDate);
	}

	@Test
	public void testStory() {
		final String[] titles = { title, "A", StringUtils.repeat("A", 80)};

		for (String title : titles) {
			final Story story = new Story(author, date, title, content, genre, mainTheme, secondaryTheme, publicated);

			assertThat(story.getDate(), is(equalTo(date)));
			assertThat(story.getTitle(), is(equalTo(title)));
			assertThat(story.getContent(), is(equalTo(content)));
			assertThat(story.getGenre(), is(equalTo(genre)));
			assertThat(story.getMainTheme(), is(equalTo(mainTheme)));
			assertThat(story.getPublicated(), is(equalTo(publicated)));
		}
	}


	@Test(expected = NullPointerException.class)
	public void testStoryTitleNull() {
		new Story(this.author, this.date, null, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryTitleTooLong() {
		new Story(this.author, this.date, repeat('A', 81), this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryTitleTooShort() {
		new Story(this.author, this.date, "", this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);
	}


	@Test(expected = NullPointerException.class)
	public void testStoryContentNull() {
		new Story(this.author, this.date, this.title, null, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryContentTooLong() {
		new Story(this.author, this.date, this.title , repeat('A', 1001), this.genre, this.mainTheme, this.secondaryTheme, this.publicated);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryContentTooShort() {
		new Story(this.author, this.date, this.title, "", this.genre, this.mainTheme, this.secondaryTheme, this.publicated);
	}

	@Test(expected = NullPointerException.class)
	public void testStoryDateNull() {
		new Story(this.author, null, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);
	}

	@Test(expected = NullPointerException.class)
	public void testStoryGenreNull() {
		new Story(this.author, this.date, this.title, this.content, null, this.mainTheme, this.secondaryTheme, this.publicated);
	}

	@Test(expected = NullPointerException.class)
	public void testStoryThemeNull() {
		new Story(this.author, this.date, this.title, this.content, this.genre, null, this.secondaryTheme, this.publicated);
	}

	@Test(expected = NullPointerException.class)
	public void testStoryPublicatedNull() {
		new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryDateAfterCurrent() {
		new Story(this.author, futureDate, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);
	}


	@Test
	public void testSetTitle() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setTitle(newTitle);

		assertThat(story.getTitle(), is(equalTo(newTitle)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetTitleNull() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setTitle(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetTitleTooShort() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setTitle("");
	}


	@Test(expected = IllegalArgumentException.class)
	public void testSetTitleLong() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setTitle(repeat('A', 81));
	}

	@Test
	public void testSetContent() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setContent(newContent);

		assertThat(story.getContent(), is(equalTo(newContent)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetContentNull() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setContent(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetContentTooShort() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setContent("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetContentLong() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme,  this.secondaryTheme,this.publicated);

		story.setContent(repeat('A', 1001));
	}

	@Test
	public void testSetGenre() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setGenre(newGenre);

		assertThat(story.getGenre(), is(equalTo(newGenre)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetGenreNull() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setGenre(null);
	}

	@Test
	public void testSetTheme() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setMainTheme(newMainTheme);

		assertThat(story.getMainTheme(), is(equalTo(newMainTheme)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetThemeNull() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setMainTheme(null);
	}

	@Test
	public void testSetSecondaryTheme() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.mainTheme, this.publicated);

		assertThat(story.getSecondaryTheme(), is(equalTo(null)));
	}

	@Test
	public void testSetDate() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setDate(newDate);

		assertThat(story.getDate(), is(equalTo(newDate)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetDateNull() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setDate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDateAfterCurrent() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme, this.secondaryTheme, this.publicated);

		story.setDate(futureDate);
	}

	@Test
	public void testSetPublicated() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme,this.secondaryTheme, this.publicated);

		story.setPublicated(newPublicated);

		assertThat(story.getPublicated(), is(equalTo(newPublicated)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetPublicatedNull() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme,this.secondaryTheme, this.publicated);

		story.setPublicated(null);
	}

	@Test
	public void testSetVisitDate() {
		final Story story = new Story(this.author, this.date, this.title, this.content, this.genre, this.mainTheme,this.secondaryTheme, this.publicated);

		story.setVisitDate(this.newVisitDate);

		assertThat(story.getVisitDate(), is(equalTo(this.newVisitDate)));
	}
}
