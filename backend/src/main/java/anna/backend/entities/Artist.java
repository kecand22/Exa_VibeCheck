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
    private String firstname;
    private String lastname;
    private String description;
    private String imageUrl;

    @ManyToMany
    @JsonIgnore
    private List<Event> events;

}
