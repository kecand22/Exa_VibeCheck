package anna.backend.db;

import anna.backend.dto.EventDto;
import anna.backend.entities.Artist;
import anna.backend.entities.Event;
import anna.backend.repositories.ArtistRepository;
import anna.backend.repositories.EventRepository;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class DbInit implements ApplicationRunner {
    private final ArtistRepository artistRepository;
    private final EventRepository eventRepository;

    public DbInit(ArtistRepository artistRepository, EventRepository eventRepository) {
        this.artistRepository = artistRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {


        try{
            log.info("---About to init database---");

            InputStream artistIs = getClass().getResourceAsStream("/vibes_artists.json");
            InputStream eventIs = getClass().getResourceAsStream("/vibes_events.json");

            ObjectMapper mapper = new ObjectMapper();

            mapper.registerModule(new JavaTimeModule());

            List<Artist> artists = mapper.readerForListOf(Artist.class).readValue(artistIs);

            this.artistRepository.saveAll(artists);
            List<EventDto> eventList = mapper.readerForListOf(EventDto.class).readValue(eventIs);

            List<Event> mappedEvents = eventList
                    .stream()
                    .map(e -> {
                        Event newEvent = new Event();
                        newEvent.setImageUrl(e.getImageUrl());
                        newEvent.setTitle(e.getTitle());
                        newEvent.setRatings(e.getRatings());
                        newEvent.setEventDate(e.getEventDate());
                        newEvent.setLocation(e.getLocation());


                        List<Artist> eventArtists = e.getArtists().stream()
                                .map(a -> {
                                    String[] tokens = a.split(" ");
                                    String firstname = tokens[0];
                                    String lastname = tokens[1];

                                    return this.artistRepository.getArtistsByFirstNameAndLastName(firstname, lastname);
                                })
                                .filter(Optional::isPresent)
                                .map(Optional::get)
                                .toList();


                        newEvent.setArtists(eventArtists);
                        e.getRatings().forEach(r -> r.setEvent(newEvent));

                        return newEvent;
                    })
                    .collect(Collectors.toList());

            this.eventRepository.saveAll(mappedEvents);


        }catch (IOException e){
            throw new RuntimeException(e);
        }

    }


}
