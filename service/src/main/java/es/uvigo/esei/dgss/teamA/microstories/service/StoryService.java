package es.uvigo.esei.dgss.teamA.microstories.service;

import es.uvigo.esei.dgss.teamA.microstories.entities.Genre;
import es.uvigo.esei.dgss.teamA.microstories.entities.Story;
import es.uvigo.esei.dgss.teamA.microstories.entities.Theme;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * EJB for the Stories.
 */
@Stateless
public class StoryService {
    private static final Integer PAGE = 0;
    private static final Integer SIZE = 10;
    private static final Integer MAX_SIZE = 100;
    @PersistenceContext
    private EntityManager em;
    @Inject
    private Principal currentUser;


    @PermitAll
    public List<Story> findLastStories() {
        return em.createQuery("SELECT s FROM Story s  where s.publicated = TRUE ORDER BY s.date DESC", Story.class).getResultList();
    }

    @PermitAll
    public Story getById(int id) {
        Story story = em.find(Story.class, id);
        if (story != null) {
            story.getVisitDate().add(new Date());
            em.persist(story);
        }

        return story;
    }

    @PermitAll
    public List<Story> findStoriesByText(final String text, Integer page, Integer size) throws IllegalArgumentException {
        if (text == null) {
            throw new IllegalArgumentException();
        }
        final TypedQuery<Story> query = em.createQuery(
                        "SELECT s FROM Story s " +
                                "WHERE s.publicated = TRUE " +
                                "AND (s.title LIKE :text or s.content LIKE :text) " +
                                "ORDER BY s.date DESC, s.id", Story.class)
                .setFirstResult(getStartPagination(page, size)).setMaxResults(checkSize(size));
        query.setParameter("text", "%" + text + "%");
        return query.getResultList();
    }

    @PermitAll
    public List<Story> findStories(final Genre genre, Theme theme, Date initDate, Date endDate, Integer page, Integer size) throws IllegalArgumentException {
        final TypedQuery<Story> query = em.createQuery("SELECT s FROM Story s " +
                        "WHERE s.publicated = TRUE " +
                        "and (:genre is null OR s.genre = :genre) " +
                        "and (:theme is null or s.mainTheme = :theme or s.secondaryTheme = :theme) " +
                        "and (:initDate is null or s.date >= :initDate)" +
                        "and (:endDate is null or s.date <= :endDate) " +
                        "ORDER BY s.date DESC, s.id", Story.class)
                .setFirstResult(getStartPagination(page, size))
                .setMaxResults(checkSize(size));
        query.setParameter("genre", genre);
        query.setParameter("theme", theme);
        query.setParameter("initDate", initDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    @PermitAll
    public List<Story> findStoriesByCurrentUser(final String login, final Integer page, final Integer size) {
        String currentUsername = currentUser.getName();

        if (login == null || "".equals(login) || !currentUsername.equals(login)) {
            throw new IllegalArgumentException();
        }

        final TypedQuery<Story> query = em.createQuery("SELECT s FROM Story s " +
                        "WHERE s.author.login = :username ORDER BY s.date DESC, s.id", Story.class)
                .setFirstResult(getStartPagination(page, size))
                .setMaxResults(checkSize(size));

        query.setParameter("username", currentUsername);
        return query.getResultList();
    }

    private Integer getStartPagination(Integer page, Integer size) {
        page = checkPage(page);
        size = checkSize(size);
        return page * size;
    }

    private Integer checkSize(Integer size) {
        return (size == null || size < PAGE) ? SIZE : Math.min(size, MAX_SIZE);
    }

    private Integer checkPage(Integer page) {
        return (page == null || page < PAGE) ? PAGE : page;
    }

    @PermitAll
    public List<Story> findHottestStories(Genre genre, Date initDate, Date endDate, Integer page, Integer size) {
        return em.createQuery("SELECT s FROM Story s " +
                        "join s.visitDate vd " +
                        "WHERE s.publicated = TRUE " +
                        "AND s.genre = :genre " +
                        "AND vd > :initDate AND vd < :endDate " +
                        "GROUP BY s " +
                        "ORDER BY count(vd) DESC, s.id", Story.class)
                .setFirstResult(getStartPagination(page, size))
                .setMaxResults(checkSize(size))
                .setParameter("genre", genre)
                .setParameter("initDate", initDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}