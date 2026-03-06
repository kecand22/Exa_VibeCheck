package anna.backend.controller;

import anna.backend.dto.EventDto;
import anna.backend.services.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/events")
@CrossOrigin(origins = "http://localhost:5173")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("")
    public ResponseEntity<List<EventDto>> getAllEvents() {
        List<EventDto> dtos = eventService.getAllEvents();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("{eventId}")
    public ResponseEntity<EventDto> getEventById(
            @PathVariable("eventId") long id
    ) {
        EventDto dto = eventService.getEventById(id);

        if (dto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("artist/{artistId}")
    public ResponseEntity<List<EventDto>> findEventsByArtist(
            @PathVariable("artistId") long id
    ) {
        List<EventDto> dtos = this.eventService.getEventsByArtistId(id);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
