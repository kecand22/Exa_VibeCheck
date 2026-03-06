package anna.backend.entities;

import anna.backend.dto.RatingDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ratingId;
    private Integer stars;
    private String comment;
    private LocalDateTime createdAt;


    @ManyToOne
    @JsonIgnore
    private Event event;

    public RatingDto toDTO() {
        return new RatingDto(
                this.ratingId,
                this.stars,
                this.comment,
                this.createdAt
        );
    }
}
