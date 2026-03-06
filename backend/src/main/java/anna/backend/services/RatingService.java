package anna.backend.services;

import anna.backend.dto.RatingDto;
import anna.backend.entities.Event;
import anna.backend.entities.Rating;
import anna.backend.repositories.EventRepository;
import anna.backend.repositories.RatingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    private  final RatingRepository ratingRepository;
    private  final EventRepository eventRepository;


    public RatingService(RatingRepository ratingRepository, EventRepository eventRepository) {
        this.ratingRepository = ratingRepository;
        this.eventRepository = eventRepository;
    }

    public List<RatingDto> findRatingsByEventId(long id) {
        List<Rating> ratings = ratingRepository.findAllByEvent_EventId(id);
        return ratings.stream().map(Rating::toDTO).toList();
    }

    public RatingDto saveNewRating(RatingDto dto, long eventId) {
        Event event = eventRepository.findById(eventId).orElse(null);

        if (event == null) {
            return null;
        }

        Rating rating = new Rating();
        rating.setComment(dto.getComment());
        rating.setCreatedAt(dto.getCreatedAt());
        rating.setStars(dto.getStars());
        rating.setEvent(event);

        return this.ratingRepository.save(rating).toDTO();
    }
}
