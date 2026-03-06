package anna.backend.services;

import anna.backend.dto.EventDto;
import anna.backend.entities.Artist;
import anna.backend.entities.Event;

import anna.backend.repositories.ArtistRepository;
import anna.backend.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final ArtistRepository artistRepository;

    @Autowired
    public EventService(EventRepository eventRepository, ArtistRepository artistRepository) {
        this.eventRepository = eventRepository;
        this.artistRepository = artistRepository;
    }

    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();


        return events.stream()
                .map(e -> new EventDto(
                        e.getTitle(),
                        e.getLocation(),
                        e.getEventDate(),
                        e.getImageUrl(),
                        new ArrayList<>(),
                        e.getRatings()
                ))
                .toList();
    }

    public EventDto getEventById(long id){
        Event e = this.eventRepository.findById(id).orElse(null);

        if (e == null) {
            return null;
        }

        EventDto dto = new EventDto(
                e.getTitle(),
                e.getLocation(),
                e.getEventDate(),
                e.getImageUrl(),
                new ArrayList<>(),
                e.getRatings()
        );

        return dto;
    }

    public List<EventDto> getEventsByArtistId(long artistId) {
        Artist artist = this.artistRepository.findById(artistId).orElse(null);

        if (artist == null) {
            return new ArrayList<>();
        }

        List<Event> events = this.eventRepository.findAllByArtistsContaining(artist);

        return events.stream()
                .map(e -> new EventDto(
                        e.getTitle(),
                        e.getLocation(),
                        e.getEventDate(),
                        e.getImageUrl(),
                        new ArrayList<>(),
                        e.getRatings()
                ))
                .toList();
    }

}
