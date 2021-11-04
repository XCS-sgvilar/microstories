package es.uvigo.esei.dgss.teama.microstories.entities;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;


import java.util.Date;

public class StoryTest {

	private Date date;
	private String title;
	private String content;
	private Genre genre;
	private Theme theme;
	private Boolean publicated;
	
	private Date newDate;
	private Date futureDate;
	private String newTitle;
	private String newContent;
	private Genre newGenre;
	private Theme newTheme;
	private Boolean newPublicated;
	
	

	@Before
	public void setUp() throws Exception {
		this.date = new Date();
		this.title = "Story 1";
		this.content = "This is the content of story 1";
		this.genre = Genre.STORY;
		this.theme = Theme.ADVENTURE;
		this.publicated = true;
		
		final int oneDay = 24*60*60*1000;
		this.newDate = new Date(System.currentTimeMillis() - oneDay);
		this.futureDate = new Date(System.currentTimeMillis() + oneDay);
		this.newTitle = "New Story 1";
		this.newContent = "This is the new content of story 1";
		this.newGenre = Genre.NANOSTORY;
		this.newTheme = Theme.CHILD;
		this.newPublicated = false;
	}

	@Test
	public void testStory() {
		final String[] titles = { title, "A", StringUtils.repeat("A", 80)};

		for (String title : titles) {
			final Story story = new Story(date, title, content, genre, theme, publicated);

			assertThat(story.getDate(), is(equalTo(date)));
			assertThat(story.getTitle(), is(equalTo(title)));
			assertThat(story.getContent(), is(equalTo(content)));
			assertThat(story.getGenre(), is(equalTo(genre)));
			assertThat(story.getTheme(), is(equalTo(theme)));
			assertThat(story.getPublicated(), is(equalTo(publicated)));
		}
	}


	@Test(expected = NullPointerException.class)
	public void testStoryTitleNull() {
		new Story(this.date, null, this.content, this.genre, this.theme, this.publicated);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryTitleTooLong() {
		new Story(this.date, repeat('A', 81), this.content, this.genre, this.theme, this.publicated);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryTitleTooShort() {
		new Story(this.date, "", this.content, this.genre, this.theme, this.publicated);
	}


	@Test(expected = NullPointerException.class)
	public void testStoryContentNull() {
		new Story(this.date, this.title, null, this.genre, this.theme, this.publicated);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryContentTooLong() {
		new Story(this.date, this.title , repeat('A', 1001), this.genre, this.theme, this.publicated);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryContentTooShort() {
		new Story(this.date, this.title, "", this.genre, this.theme, this.publicated);
	}

	@Test(expected = NullPointerException.class)
	public void testStoryDateNull() {
		new Story(null, this.title, this.content, this.genre, this.theme, this.publicated);
	}

	@Test(expected = NullPointerException.class)
	public void testStoryGenreNull() {
		new Story(this.date, this.title, this.content, null, this.theme, this.publicated);
	}

	@Test(expected = NullPointerException.class)
	public void testStoryThemeNull() {
		new Story(this.date, this.title, this.content, this.genre, null, this.publicated);
	}

	@Test(expected = NullPointerException.class)
	public void testStoryPublicatedNull() {
		new Story(this.date, this.title, this.content, this.genre, this.theme, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testStoryDateAfterCurrent() {
		new Story(futureDate, this.title, this.content, this.genre, this.theme, this.publicated);
	}

	@Test
	public void testSetTitle() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setTitle(newTitle);

		assertThat(story.getTitle(), is(equalTo(newTitle)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetTitleNull() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setTitle(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetTitleTooShort() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setTitle("");
	}


	@Test(expected = IllegalArgumentException.class)
	public void testSetTitleLong() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setTitle(repeat('A', 81));
	}

	@Test
	public void testSetContent() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setContent(newContent);

		assertThat(story.getContent(), is(equalTo(newContent)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetContentNull() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setContent(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetContentTooShort() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setContent("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetContentLong() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setContent(repeat('A', 1001));
	}

	@Test
	public void testSetGenre() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setGenre(newGenre);

		assertThat(story.getGenre(), is(equalTo(newGenre)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetGenreNull() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setGenre(null);
	}

	@Test
	public void testSetTheme() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setTheme(newTheme);

		assertThat(story.getTheme(), is(equalTo(newTheme)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetThemeNull() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setTheme(null);
	}

	@Test
	public void testSetDate() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setDate(newDate);

		assertThat(story.getDate(), is(equalTo(newDate)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetDateNull() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setDate(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDateAfterCurrent() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setDate(futureDate);
	}

	@Test
	public void testSetPublicated() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setPublicated(newPublicated);

		assertThat(story.getPublicated(), is(equalTo(newPublicated)));
	}

	@Test(expected = NullPointerException.class)
	public void testSetPublicatedNull() {
		final Story story = new Story(this.date, this.title, this.content, this.genre, this.theme, this.publicated);

		story.setPublicated(null);
	}





}
