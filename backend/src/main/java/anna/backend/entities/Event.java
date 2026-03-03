package anna.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long eventId;
    private String title;
    private String location;
    private LocalDate eventDate;
    private String imageUrl;

    @ManyToMany
    @JoinTable(name = "event_artist",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artists;


    private List<Rating> ratings;

}
