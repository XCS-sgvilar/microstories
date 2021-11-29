package es.uvigo.esei.dgss.teamA.microstories.entities;


import es.uvigo.esei.dgss.teamA.microstories.entities.StoryTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        StoryTest.class,
        UserTest.class
})
public class EntitiesTestSuite {}

