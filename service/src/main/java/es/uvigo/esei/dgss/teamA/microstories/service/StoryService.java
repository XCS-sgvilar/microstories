package es.uvigo.esei.dgss.teamA.microstories.service;

import es.uvigo.esei.dgss.teamA.microstories.entities.Story;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * EJB for the Owners. Only administrators have access to this class.
 *
 * @author Miguel Reboiro Jato
 */
@Stateless
public class StoryService {
    private static final Integer PAGE = 0;
    private static final Integer SIZE = 10;
    @PersistenceContext
    private EntityManager em;



    @PermitAll
    public List<Story> findLastStories() {
        return em.createQuery("SELECT s FROM Story s ORDER BY s.date DESC", Story.class).getResultList();
    }

    @PermitAll
    public Story getById(int id) {
        return em.find(Story.class, id);
    }

    @PermitAll
    public List<Story> findStoriesByText(final String text, Integer page, Integer size) throws IllegalArgumentException {
        if(text == null){
            throw new IllegalArgumentException();
        }
        final TypedQuery<Story> query = em.createQuery("SELECT s FROM Story s WHERE s.title LIKE concat('%', ?1,'%') or s.content LIKE concat('%', ?1,'%') ORDER BY s.date DESC, s.id", Story.class).setFirstResult(getStartPagination(page, size)).setMaxResults(checkSize(size));
        query.setParameter(1, text);
        return query.getResultList();
    }

    private Integer getStartPagination(Integer page, Integer size){
        page = checkPage(page);
        size = checkSize(size);
        return (page == PAGE) ? PAGE : (page * size) - size;
    }

    private Integer checkSize(Integer size) {
        return (size == null || size < PAGE) ? SIZE : size;
    }

    private Integer checkPage(Integer page) {
        return (page == null || page < PAGE) ? PAGE : page;
    }


}
