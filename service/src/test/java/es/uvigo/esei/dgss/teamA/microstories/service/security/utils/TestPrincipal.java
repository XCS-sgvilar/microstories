package es.uvigo.esei.dgss.teamA.microstories.service.security.utils;

import java.security.Principal;
import javax.enterprise.inject.Alternative;
import javax.inject.Singleton;

@Alternative
@Singleton
public class TestPrincipal implements Principal {
    private String name;

    public TestPrincipal() {}

    public TestPrincipal(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}

