package anna.backend.services;

import anna.backend.dto.ArtistDto;
import anna.backend.entities.Artist;
import anna.backend.repositories.ArtistRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Autowired
    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public List<ArtistDto> loadAllArtists(){
        List<Artist> allArtists = this.artistRepository.findAll();
        List<ArtistDto> dtoArtist =  new ArrayList<>();
        allArtists.forEach(a->
                dtoArtist.add(new ArtistDto(
                        a.getArtistId(),
                        a.getFirstname(),
                        a.getLastname(),
                        a.getDescription(),
                        a.getImageUrl()
                )));

        return dtoArtist;
    }

    public ArtistDto getArtistById(long  id){
        Artist a = this.artistRepository.findById(id).orElse(null);

        if (a == null) {
            return null;
        }

        ArtistDto dto = new ArtistDto(
                a.getArtistId(),
                a.getFirstname(),
                a.getLastname(),
                a.getDescription(),
                a.getImageUrl()
        );

        return dto;
    }

    @Transactional //will be needed to save into repository
    public Page<Artist> getAllPageable(Pageable pageable){
        Page<Artist> artist = artistRepository.findAll(pageable);

        return artist;
    }
}
