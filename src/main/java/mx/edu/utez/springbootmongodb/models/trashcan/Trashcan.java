package mx.edu.utez.springbootmongodb.models.trashcan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "trashcans")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Trashcan {
    @Id
    private String id;

    private Integer serialNumber; // serialNumber es el trashcanId que mada el esp
    private String trashcanName;
}
