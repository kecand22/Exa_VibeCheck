package anna.backend.repositories;

import anna.backend.entities.Event;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("""
            SELECT DISTINCT e 
            FROM Event e 
            INNER JOIN e.artists a            
            WHERE a.artistId = :artistId      """)

    List<Event> findEventsByArtistId(Long artistId);
}
