package anna.backend.repositories;

import anna.backend.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long>{
    Optional<Artist> getArtistsByArtistId(Long artistId);
}
