package es.uvigo.esei.dgss.teamA.microstories.entities;

import org.hamcrest.Factory;
import org.hamcrest.Matcher;


public class IsEqualToStory extends IsEqualToEntity<Story> {

    public IsEqualToStory(Story story) {
        super(story);
    }

    @Factory
    public static IsEqualToStory equalToStory(Story story) {
        return new IsEqualToStory(story);
    }

    @Factory
    public static Matcher<Iterable<? extends Story>> containsStorysInAnyOrder(Story... stories) {
        return containsEntityInAnyOrder(IsEqualToStory::equalToStory, stories);
    }

    @Factory
    public static Matcher<Iterable<? extends Story>> containsStorysInAnyOrder(Iterable<Story> stories) {
        return containsEntityInAnyOrder(IsEqualToStory::equalToStory, stories);
    }

    @Factory
    public static Matcher<Iterable<? extends Story>> containsStorysInOrder(Story... stories) {
        return containsEntityInOrder(IsEqualToStory::equalToStory, stories);
    }

    @Factory
    public static Matcher<Iterable<? extends Story>> containsStorysInOrder(Iterable<Story> stories) {
        return containsEntityInOrder(IsEqualToStory::equalToStory, stories);
    }

    @Override
    protected boolean matchesSafely(Story actual) {
        this.clearDescribeTo();

        if (actual == null) {
            this.addTemplatedDescription("actual", expected.toString());
            return false;
        } else {
            return checkAttribute("title", Story::getTitle, actual)
                    && checkAttribute("content", Story::getContent, actual)
                    && checkAttribute("date", Story::getDate, actual)
                    && checkAttribute("genre", Story::getGenre, actual)
                    && checkAttribute("mainTheme", Story::getMainTheme, actual)
                    && checkAttribute("secondaryTheme", Story::getSecondaryTheme, actual)
                    && checkAttribute("publicated", Story::getPublicated, actual);
        }
    }

}
