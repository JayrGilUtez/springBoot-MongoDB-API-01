package mx.edu.utez.springbootmongodb.models.record;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.edu.utez.springbootmongodb.models.location.Location;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



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
    private String dateAndTime;
    private Location location;

}
