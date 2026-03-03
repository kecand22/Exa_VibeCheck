package anna.backend.entities;

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
    private List<Artist> artists;

}
