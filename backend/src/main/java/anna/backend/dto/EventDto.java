package anna.backend.dto;

import anna.backend.entities.Rating;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//"title": "Midnight Echoes",
//    "location": "Hamburg",
//    "eventDate": "2025-07-03",
//    "imageUrl": "https://images.unsplash.com/photo-1506157786151-b8491531f063?auto=format&fit=crop&w=800&q=60",
//    "artists": ["Luna Vega", "Ivy Moon"],
//    "ratings": [
//      { "stars": 5, "comment": "Beautiful and emotional.", "createdAt": "2025-07-04T09:00:00" }
//    ]
public class EventDto {
    private String title;
    private String location;
    private Date eventDate;
    private String imageUrl;
    private List<String> artists;
    private List<Rating> ratings;
}
