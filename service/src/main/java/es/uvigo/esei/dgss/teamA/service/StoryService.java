package es.uvigo.esei.dgss.teamA.service;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * EJB for the Owners. Only administrators have access to this class.
 *
 * @author Miguel Reboiro Jato
 */
@Stateless
public class StoryService {
    @PersistenceContext
    private EntityManager em;


    public List<Story> findLastStories() {
        return em.createQuery("SELECT s FROM Story s ORDER BY s.date DESC", Story.class).getResultList();
    }


}
