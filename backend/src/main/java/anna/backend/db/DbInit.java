package anna.backend.db;

import anna.backend.repositories.ArtistRepository;
import anna.backend.repositories.EventRepository;
import anna.backend.repositories.RatingRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;

@Component
@Slf4j
public class DbInit implements ApplicationRunner {
    private final EventRepository eventRepository;
    private final ArtistRepository artistRepository;
    private final RatingRepository ratingRepository;

    public DbInit(EventRepository eventRepository, ArtistRepository artistRepository, RatingRepository ratingRepository) {
        this.eventRepository = eventRepository;
        this.artistRepository = artistRepository;
        this.ratingRepository = ratingRepository;
    }


    @Override
    @Transactional
    public void run(ApplicationArguments args) throws Exception {
        log.info("ApplicationRunner startet...");

        ObjectMapper objectMapper = new ObjectMapper();
        InputStream eventInputStream = getClass().getResourceAsStream("/vibes_events.json");

    }
}
