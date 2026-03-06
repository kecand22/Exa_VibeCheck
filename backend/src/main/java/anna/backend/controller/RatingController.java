package anna.backend.controller;

import anna.backend.dto.RatingDto;
import anna.backend.services.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/ratings")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @GetMapping("event/{eventId}")
    public ResponseEntity<List<RatingDto>> getRatingsFromEvent(
            @PathVariable("eventId") long id
    ) {
        List<RatingDto> dtos = ratingService.findRatingsByEventId(id);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping("{eventId}")
    public ResponseEntity<RatingDto> postRatingDTO(
            @PathVariable("eventId") long id,
            @RequestBody RatingDto ratingDTO
    ) {
        RatingDto newDTO = this.ratingService.saveNewRating(ratingDTO, id);

        if (newDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(newDTO, HttpStatus.CREATED);
    }

}
