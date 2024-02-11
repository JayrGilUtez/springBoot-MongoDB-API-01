package mx.edu.utez.springbootmongodb.models.record;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.springbootmongodb.models.location.Location;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Data
@Document(collection = "records")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    @Id
    private String id;

    private Integer trashcanId;
    private Double distance;
    private Boolean isFull;
    private LocalDateTime dateAndTime;
    private Location location;

    public Record(Double distance, Integer trashcanId, Boolean isFull, LocalDateTime dateAndTime, Location location) {
        this.distance = distance;
        this.trashcanId = trashcanId;
        this.isFull = isFull;
        this.dateAndTime = dateAndTime;
        this.location = location;
    }


}
