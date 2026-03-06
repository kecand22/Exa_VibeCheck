package anna.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RatingDto {
    private Long id;
    private Integer stars;
    private String comment;
    private LocalDateTime createdAt;
}
