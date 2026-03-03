package anna.backend.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artistId;
    private String firstname;
    private String lastname;
    private String description;
    private String imageUrl;

    @ManyToMany
    private List<Event> events;

}
