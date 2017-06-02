package nl.first8.hu.ticketsale.venue;

import nl.first8.hu.ticketsale.artistInfo.Artist;
import nl.first8.hu.ticketsale.artistInfo.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ConcertRepository {

    private final EntityManager entityManager;

    @Autowired
    public ConcertRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Concert> findAll() {
        return entityManager.createQuery("SELECT c FROM Concert c", Concert.class).getResultList();
    }

    List<Concert> findByName(String name) {
        try {
            return entityManager
                    .createQuery("SELECT c FROM Concert c WHERE c.artist.name =:artistName", Concert.class)
                    .setParameter("artistName", name)
                    .getResultList();

        } catch (NoResultException ex) {
            return Collections.emptyList();
        }
    }

    List<Concert> findByGenre(String genre) {
        try {
            return entityManager
                    .createQuery("SELECT c FROM Concert c WHERE c.artist.genre IN :artistGenre", Concert.class)
                    .setParameter("artistGenre", Genre.valueOf(genre.toUpperCase()))
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        } catch (IllegalArgumentException iae) {
            return null;
        }
    }

    List<Concert> findByLocation(String locationName) {
        try {
            return entityManager
                    .createQuery("SELECT c FROM Concert c WHERE UPPER(c.location.name) = :locationName", Concert.class)
                    .setParameter("locationName", locationName.toUpperCase())
                    .getResultList();

        } catch (NoResultException ex) {
            return null;
        }
    }
}
