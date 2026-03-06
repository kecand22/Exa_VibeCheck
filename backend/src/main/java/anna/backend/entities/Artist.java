package anna.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artistId;
    private String firstName;
    private String lastName;
    private String description;
    private String imageUrl;

    @ManyToMany(mappedBy = "artists")
    @JsonIgnore
    private List<Event> events;

}
