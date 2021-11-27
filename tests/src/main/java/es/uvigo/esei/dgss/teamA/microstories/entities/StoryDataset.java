package es.uvigo.esei.dgss.teamA.microstories.entities;

import es.uvigo.esei.dgss.teamA.microstories.entities.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;

public class StoryDataset {
    public static final String EXISTENT_TITLE = "Aliquam ultrices iaculis odio.";
    public static final String NON_EXISTENT_TITLE = "TeamA";
    public static final int EXISTENT_ID = 1;
    public static final int NON_EXISTENT_ID = 1001;
    public static final String EXISTENT_CONTENT_FRAGMENT = "A";
    public static final String NON_EXISTENT_CONTENT_FRAGMENT = "Yeah perdonen kamekameha";
    public static final Theme EXISTENT_THEME = Theme.HORROR;
    public static final Genre EXISTENT_GENRE = Genre.POETRY;
    public static final String EXISTENT_THEME_STRING = "HORROR";
    public static final String EXISTENT_GENRE_STRING = "POETRY";
    public static final String EXISTENT_PUBLICATED_STRING = "THIS_YEAR";

    public static Story[] stories(String... logins) {
        final Set<String> loginSet = stream(logins).collect(toSet());

        return stream(stories()).filter(stories -> loginSet.contains(stories.getTitle())).toArray(Story[]::new);
    }

    public static Story[] stories() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);

        try {

            Story[] stories = new Story[]{
                    new Story(1, "JK Rowling" , formatter.parse("2000-02-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(2, "JK Rowling" , formatter.parse("2000-02-01 01:01:01"), "Integer aliquam adipiscing lacus.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, true),
                    new Story(3, "JK Rowling" , formatter.parse("2000-02-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(4, "JK Rowling" , formatter.parse("2000-02-01 01:01:01"), "sed pede. Cum sociis", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, true),
                    new Story(5, "JK Rowling" , formatter.parse("2000-02-01 01:01:01"), "eleifend. Cras sed leo.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(6, "JK Rowling" , formatter.parse("2000-02-01 01:01:01"), "Aliquam ultrices iaculis odio.", "rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam a felis", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HORROR, true),
                    new Story(7, "JK Rowling" , formatter.parse("2000-02-01 01:01:01"), "In nec orci. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(8, "JK Rowling" , formatter.parse("2001-12-01 01:01:01"), "Aliquam ultrices iaculis odio.", "et ultrices posuere cubilia Curae Donec tincidunt. Donec vitae erat vel pede blandit congue.", Genre.POETRY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(9, "JK Rowling" , formatter.parse("2001-02-01 01:01:01"), "erat. Etiam vestibulum massa", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(10, "JK Rowling" , formatter.parse("2001-02-01 01:01:01"), "consectetuer mauris id sapien.", "lacus. Aliquam rutrum lorem ac risus. Morbi metus. Vivamus euismod urna. Nullam lobortis quam", Genre.STORY, Theme.HORROR, Theme.HORROR, true),
                    new Story(11, "JK Rowling" , formatter.parse("2001-02-01 01:01:01"), "accumsan laoreet ipsum. Curabitur", "sit amet ornare lectus justo eu arcu. Morbi sit amet massa. Quisque porttitor eros", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(12, "JK Rowling" , formatter.parse("2001-02-01 01:01:01"), "dapibus quam quis diam.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(13, "JK Rowling" , formatter.parse("2001-02-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SUSPENSE, Theme.HORROR, true),
                    new Story(14, "Scott Aaronson", formatter.parse("2001-02-01 01:01:01"), "In tincidunt congue turpis.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(15, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "senectus et netus et malesuada fames ac turpis egestas. Fusce aliquet magna a neque.", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(16, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "mattis. Cras eget nisi", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SUSPENSE, Theme.HORROR, true),
                    new Story(17, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "Aenean eget metus. In", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HORROR, true),
                    new Story(18, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "ligula consectetuer rhoncus. Nullam", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.CHILD, Theme.HORROR, true),
                    new Story(19, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "magna nec quam. Curabitur", "dolor vitae dolor. Donec fringilla. Donec feugiat metus sit amet ante. Vivamus non lorem", Genre.POETRY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(20, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "mauris a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque ut ipsum", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(21, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, true),
                    new Story(22, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "egestas. Duis ac arcu.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(23, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "ante dictum cursus. Nunc", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.CHILD, Theme.HORROR, true),
                    new Story(24, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, true),
                    new Story(25, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "neque et nunc. Quisque ornare tortor at risus. Nunc ac sem ut dolor dapibus", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(26, "Scott Aaronson", formatter.parse("2001-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, true),
                    new Story(27, "Scott Aaronson", formatter.parse("2021-03-01 01:01:01"), "sed pede. Cum sociis", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(28, "Scott Aaronson", formatter.parse("2021-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HORROR, Theme.HORROR, true),
                    new Story(29, "Scott Aaronson", formatter.parse("2021-03-01 01:01:01"), "Integer vitae nibh. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.CHILD, Theme.HORROR, true),
                    new Story(30, "Scott Aaronson", formatter.parse("2021-03-01 01:01:01"), "neque non quam. Pellentesque", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(31, "Scott Aaronson", formatter.parse("2021-03-01 01:01:01"), "Nam ac nulla. In", "ridiculus mus. Aenean eget magna. Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus.", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(32, "Victor", formatter.parse("2021-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.HORROR, Theme.HORROR, true),
                    new Story(33, "Victor", formatter.parse("2021-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.HORROR, Theme.HORROR, true),
                    new Story(34, "Victor", formatter.parse("2021-03-01 01:01:01"), "ultrices. Duis volutpat nunc", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(35, "Victor", formatter.parse("2021-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(36, "Victor", formatter.parse("2021-03-01 01:01:01"), "Aliquam ultrices iaculis odio.", "Phasellus ornare. Fusce mollis. Duis sit amet diam eu dolor egestas rhoncus. Proin nisl", Genre.STORY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(37, "Victor", formatter.parse("2021-01-01 01:01:01"), "Aliquam ultrices iaculis odio.", "erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac", Genre.POETRY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(38, "Victor", formatter.parse("2021-01-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(39, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "Aliquam ultrices iaculis odio.", "ligula. Aenean euismod mauris eu elit. Nulla facilisi. Sed neque. Sed eget lacus. Mauris", Genre.STORY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(40, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "Phasellus vitae mauris sit", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(41, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "justo eu arcu. Morbi", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SUSPENSE, Theme.HORROR, true),
                    new Story(42, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "egestas blandit. Nam nulla", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.HORROR, Theme.HORROR, true),
                    new Story(43, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "urna suscipit nonummy. Fusce", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.CHILD, Theme.HORROR, true),
                    new Story(44, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "sociis natoque penatibus et", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.HORROR, Theme.HORROR, true),
                    new Story(45, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(46, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "ornare sagittis felis. Donec", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(47, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.NANOSTORY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(48, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "Aliquam ultrices iaculis odio.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SUSPENSE, Theme.HORROR, true),
                    new Story(49, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "Lorem ipsum dolor sit", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(50, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "In tincidunt congue turpis.", "eu, odio. Phasellus at augue id ante dictum cursus. Nunc mauris elit, dictum eu,", Genre.STORY, Theme.SUSPENSE, Theme.HORROR, true),
                    new Story(51, "Brandon Sanderson", formatter.parse("2021-04-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(52, "Santiago", formatter.parse("2021-04-01 01:01:01"), "Praesent eu dui. Cum", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(53, "Santiago", formatter.parse("2021-04-01 01:01:01"), "fringilla euismod enim. Etiam", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.STORY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(54, "Santiago", formatter.parse("2021-04-01 01:01:01"), "pede nec ante blandit", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(55, "Santiago", formatter.parse("2021-05-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(56, "Santiago", formatter.parse("2021-05-01 01:01:01"), "et ultrices posuere cubilia", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(57, "Santiago", formatter.parse("2021-05-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HORROR, true),
                    new Story(58, "Santiago", formatter.parse("2021-05-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.STORY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(59, "Santiago", formatter.parse("2021-05-01 01:01:01"), "ac ipsum. Phasellus vitae", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.HISTORIC, Theme.HORROR, true),
                    new Story(60, "Santiago", formatter.parse("2021-05-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.STORY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(61, "Santiago", formatter.parse("2021-05-01 01:01:01"), "ut mi. Duis risus", "molestie. Sed id risus quis diam luctus lobortis. Class aptent taciti sociosqu ad litora", Genre.POETRY, Theme.HORROR, Theme.HORROR, true),
                    new Story(62, "Santiago", formatter.parse("2021-05-01 01:01:01"), "commodo ipsum. Suspendisse non", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(63, "Santiago", formatter.parse("2021-05-01 01:01:01"), "ac libero nec ligula", "Suspendisse tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed pede nec ante", Genre.POETRY, Theme.ROMANCE, Theme.HORROR, true),
                    new Story(64, "Santiago", formatter.parse("2021-05-01 01:01:01"), "Cum sociis natoque penatibus", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.STORY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(65, "Santiago", formatter.parse("2021-05-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.CHILD, Theme.HORROR, true),
                    new Story(66, "Ines",formatter.parse("2021-05-01 01:01:01"), "sem egestas blandit. Nam", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.SCIENCE_FICTION, Theme.HORROR, true),
                    new Story(67, "Ines",formatter.parse("2021-05-01 01:01:01"), "sapien imperdiet ornare. In", "erat vitae risus. Duis a mi fringilla mi lacinia mattis. Integer eu lacus. Quisque", Genre.POETRY, Theme.CHILD, Theme.HORROR, true),
                    new Story(68,"Ines", formatter.parse("2021-05-01 01:01:01"), "metus sit amet ante.", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HORROR, true),
                    new Story(69, "Ines",formatter.parse("2021-06-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.STORY, Theme.HORROR, Theme.HISTORIC, false),
                    new Story(70, "Ines",formatter.parse("2021-06-01 01:01:01"), "convallis ligula. Donec luctus", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.HISTORIC, Theme.HISTORIC, false),
                    new Story(71, "Ines",formatter.parse("2021-06-01 01:01:01"), "neque non quam. Pellentesque", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.HISTORIC, Theme.HISTORIC, false),
                    new Story(72, "Ines",formatter.parse("2021-06-01 01:01:01"), "Nulla semper tellus id", "a nunc. In at pede. Cras vulputate velit eu sem. Pellentesque ut ipsum ac", Genre.NANOSTORY, Theme.ROMANCE, Theme.HISTORIC, false),
                    new Story(73, "Ines",formatter.parse("2021-06-01 01:01:01"), "neque. Nullam ut nisi", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.ROMANCE, Theme.HISTORIC, false),
                    new Story(74, "Bruno", formatter.parse("2009-06-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.HISTORIC, Theme.HISTORIC, false),
                    new Story(75, "Bruno", formatter.parse("2009-06-01 01:01:01"), "dictum sapien. Aenean massa.", "netus et malesuada fames ac turpis egestas. Aliquam fringilla cursus purus. Nullam scelerisque neque", Genre.STORY, Theme.CHILD, Theme.HISTORIC, false),
                    new Story(76, "Bruno", formatter.parse("2009-06-01 01:01:01"), "fermentum fermentum arcu. Vestibulum", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.SUSPENSE, Theme.HISTORIC, false),
                    new Story(77, "Bruno", formatter.parse("2009-06-01 01:01:01"), "Nulla semper tellus id", "tristique neque venenatis lacus. Etiam bibendum fermentum metus. Aenean sed pede nec ante blandit", Genre.NANOSTORY, Theme.HORROR, Theme.HISTORIC, false),
                    new Story(78, "Bruno", formatter.parse("2009-06-01 01:01:01"), "enim commodo hendrerit. Donec", "orci. Ut sagittis lobortis mauris. Suspendisse aliquet molestie tellus. Aenean egestas hendrerit neque. In", Genre.NANOSTORY, Theme.HORROR, Theme.HISTORIC, false),
                    new Story(79, "Bruno", formatter.parse("2019-07-01 01:01:01"), "volutpat. Nulla facilisis. Suspendisse", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.HORROR, Theme.HISTORIC, false),
                    new Story(80, "Yudkowsky", formatter.parse("2019-07-01 01:01:01"), "Nulla semper tellus id", "Donec egestas. Duis ac arcu. Nunc mauris. Morbi non sapien molestie orci tincidunt adipiscing.", Genre.STORY, Theme.SUSPENSE, Theme.HISTORIC, false),
                    new Story(81, "Yudkowsky", formatter.parse("2019-07-01 01:01:01"), "Proin dolor. Nulla semper", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.HORROR, Theme.HISTORIC, false),
                    new Story(82, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.HORROR, Theme.HISTORIC, false),
                    new Story(83, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nam interdum enim non", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.STORY, Theme.SCIENCE_FICTION, Theme.HISTORIC, false),
                    new Story(84, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "Donec tincidunt. Donec vitae erat vel pede blandit congue. In scelerisque scelerisque dui. Suspendisse", Genre.STORY, Theme.SUSPENSE, Theme.HISTORIC, false),
                    new Story(85, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.HISTORIC, Theme.HISTORIC, false),
                    new Story(86, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "augue ut lacus. Nulla", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.STORY, Theme.SUSPENSE, Theme.HISTORIC, false),
                    new Story(87, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "luctus et ultrices posuere cubilia Curae Donec tincidunt. Donec vitae erat vel pede blandit", Genre.STORY, Theme.SUSPENSE, Theme.HISTORIC, false),
                    new Story(88, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "lobortis tellus justo sit", "tortor. Nunc commodo auctor velit. Aliquam nisl. Nulla eu neque pellentesque massa lobortis ultrices.", Genre.POETRY, Theme.ROMANCE, Theme.HISTORIC, false),
                    new Story(89, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "lorem semper auctor. Mauris", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.HISTORIC, Theme.HISTORIC, false),
                    new Story(90, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "Morbi quis urna. Nunc quis arcu vel quam dignissim pharetra. Nam ac nulla. In", Genre.STORY, Theme.ROMANCE, Theme.HISTORIC, false),
                    new Story(91, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Integer eu lacus. Quisque", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.STORY, Theme.HORROR, Theme.HISTORIC, false),
                    new Story(92, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "euismod est arcu ac", "fringilla est. Mauris eu turpis. Nulla aliquet. Proin velit. Sed malesuada augue ut lacus.", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HISTORIC, false),
                    new Story(93, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.SUSPENSE, Theme.HISTORIC, false),
                    new Story(94, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.STORY, Theme.HISTORIC, Theme.HISTORIC, false),
                    new Story(95, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "amet orci. Ut sagittis lobortis mauris. Suspendisse aliquet molestie tellus. Aenean egestas hendrerit neque.", Genre.NANOSTORY, Theme.CHILD, Theme.HISTORIC, false),
                    new Story(96, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "lorem semper auctor. Mauris", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.HISTORIC, Theme.HISTORIC, false),
                    new Story(97, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.HISTORIC, Theme.HISTORIC, false),
                    new Story(98, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "Nulla semper tellus id", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.CHILD, Theme.HISTORIC, false),
                    new Story(99, "Yudkowsky", formatter.parse("2014-07-01 01:01:01"), "orci. Ut sagittis lobortis", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.NANOSTORY, Theme.ADVENTURE, Theme.HISTORIC, false),
                    new Story(100, "Greg Egan",formatter.parse("2014-07-01 01:01:01"), "Curabitur vel lectus. Cum", "est arcu ac orci. Ut semper pretium neque. Morbi quis urna. Nunc quis arcu", Genre.POETRY, Theme.ADVENTURE, Theme.HISTORIC, false),
            };


            addVisitDateToStories(formatter, stories);

            return stories;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Story[] StoriesAnd(Story... additionalStories) {
        final Story[] stories = stories();
        final Story[] storiesWithNewStory = new Story[stories.length + additionalStories.length];

        System.arraycopy(stories, 0, storiesWithNewStory, 0, stories.length);
        System.arraycopy(additionalStories, 0, storiesWithNewStory, stories.length, additionalStories.length);

        return storiesWithNewStory;
    }

    public static List<Story> StoriesWithout(Story... removeStories) {
        return stream(stories()).filter(i ->
                        !stream(removeStories)
                                .map(Story::getTitle).collect(Collectors.toList())
                                .contains(i.getTitle()))
                .collect(Collectors.toList());
    }

    public static Story storyWithId(int id) {
        return stream(stories()).filter(story -> story.getId() == id)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static Story storyWithTitle(String title) {
        return stream(stories())
                .filter(story -> story.getTitle().equals(title)).
                findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public static List<Story> recentStories() {
        return stream(stories())
                .filter(Story::getPublicated)
                .sorted(Comparator.comparing(Story::getDate).reversed())
                .collect(Collectors.toList());
    }

    public static List<Story> allStoriesSortedByRecent() {
        return stream(stories())
                .sorted(Comparator.comparing(Story::getDate).reversed())
                .collect(Collectors.toList());
    }

    public static List<Story> storiesOf(String text, int start, int end) {
        final List<Story> stories = new ArrayList<>();

        for (Story story : stories()) {
            if (story.getPublicated() && ( story.getTitle().contains(text) || story.getContent().contains(text))) {
                stories.add(story);
            }
        }

        Collections.sort(stories, Comparator.comparing(Story::getDate).reversed());

        return stories.subList(Math.min(stories.size(), start), Math.min(stories.size(), end));
    }

    public static List<Story> storiesSearch(Genre genre, Theme theme, Date initDate, Date endDate, int start, int end) {
        final List<Story> stories = new ArrayList<>();

        for (Story story : stories()) {
            if ((genre == null || story.getGenre() == genre ) &&
                    (theme == null || story.getMainTheme() == theme || story.getSecondaryTheme() == theme) &&
                    (initDate == null || story.getDate().compareTo(initDate) >= 0) &&
                    (endDate == null || story.getDate().compareTo(endDate) <= 0) &&
                    (story.getPublicated() == true)) {
                stories.add(story);
            }
        }

        Collections.sort(stories, Comparator.comparing(Story::getDate).reversed());

        return stories.subList(Math.min(stories.size(), start), Math.min(stories.size(), end));
    }

    public static int existentId() {
        return EXISTENT_ID;
    }

    public static int nonExistentId() {
        return NON_EXISTENT_ID;
    }

    public static Story existentStory() {
        return storyWithId(existentId());
    }

    public static String existentContentFragment() {
        return EXISTENT_CONTENT_FRAGMENT;
    }

    public static String nonExistentContentFragment() {
        return NON_EXISTENT_CONTENT_FRAGMENT;
    }

    public static Theme existentTheme(){
        return EXISTENT_THEME;
    }

    public static Genre existentGenre() {
        return EXISTENT_GENRE;
    }

    private static void addVisitDateToStories(SimpleDateFormat formatter, Story[] stories) throws ParseException {
        List<Date> dateStory0 = new ArrayList<>();
        dateStory0.add(formatter.parse("2000-02-01 01:01:01"));
        dateStory0.add(formatter.parse("2000-02-03 01:01:01"));
        dateStory0.add(formatter.parse("2000-02-09 01:01:01"));
        stories[0].setVisitDate(dateStory0);

        List<Date> dateStory1 = new ArrayList<>();
        dateStory1.add(formatter.parse("2000-02-01 01:01:01"));
        stories[1].setVisitDate(dateStory1);

        List<Date> dateStory2 = new ArrayList<>();
        dateStory2.add(formatter.parse("2000-02-11 01:01:01"));
        dateStory2.add(formatter.parse("2000-02-13 01:01:01"));
        stories[2].setVisitDate(dateStory2);
        stories[3].setVisitDate(dateStory2);

        List<Date> dateStory3 = new ArrayList<>();
        dateStory3.add(formatter.parse("2000-02-16 01:01:01"));
        dateStory3.add(formatter.parse("2000-02-03 01:01:01"));
        stories[4].setVisitDate(dateStory3);

        stories[5].setVisitDate(dateStory1);

        List<Date> dateStory4 = new ArrayList<>();
        dateStory4.add(formatter.parse("2000-02-01 01:01:01"));
        dateStory4.add(formatter.parse("2000-02-18 01:01:01"));
        stories[6].setVisitDate(dateStory4);

        stories[7].setVisitDate(dateStory0);

        List<Date> dateStory5 = new ArrayList<>();
        dateStory5.add(formatter.parse("2000-02-19 01:01:01"));
        stories[8].setVisitDate(dateStory5);
        stories[9].setVisitDate(dateStory5);
    }

}