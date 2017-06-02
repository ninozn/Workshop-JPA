package nl.first8.hu.ticketsale.venue;

import nl.first8.hu.ticketsale.artistInfo.Artist;
import nl.first8.hu.ticketsale.artistInfo.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
            Optional<Artist> artist = Optional.of(entityManager.createQuery("SELECT a FROM Artist a WHERE a.name =:name", Artist.class)
                    .setParameter("name", name)
                    .getResultList().get(0));
           if(artist.isPresent()) {
               return entityManager
                       .createQuery("SELECT c FROM Concert c WHERE c.artist.id =:artistid", Concert.class)
                       .setParameter("artistid", artist.get().getId())
                       .getResultList();
           }
           else{
               return null;
           }
        } catch (NoResultException ex) {
            return null;
        }
    }

    List<Concert> findByGenre(String genre) {

        try {
            List<Long> artist = entityManager.createQuery("SELECT a.id FROM Artist a WHERE UPPER(a.genre) = :genre", Long.class)
                    .setParameter("genre", Genre.valueOf(genre.toUpperCase()))
                    .getResultList();

            if(artist.size() > 0) {
                return entityManager
                        .createQuery("SELECT c FROM Concert c WHERE c.artist.id IN :artistid", Concert.class)
                        .setParameter("artistid", artist)
                        .getResultList();
            }
            else{
                return null;
            }
        } catch (NoResultException ex) {
            return null;
        } catch (IllegalArgumentException iae){
            return null;
        }
    }

    List<Concert> findByLocation(String locationName) {

        try {
            Optional<Location> location = Optional.of(entityManager.createQuery("SELECT l FROM Location l WHERE UPPER(l.name) =:locationName", Location.class)
                    .setParameter("locationName", locationName.toUpperCase())
                    .getResultList().get(0));

            if(location.isPresent()) {
                return entityManager
                        .createQuery("SELECT c FROM Concert c WHERE c.location.id = :locationid", Concert.class)
                        .setParameter("locationid", location.get().getId())
                        .getResultList();
            }
            else{
                return null;
            }
        } catch (NoResultException ex) {
            return null;
        }
    }
}
