package mx.edu.utez.springbootmongodb.models.location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Location {
    // utez 18.850410301711264, -99.20075160125488
    private Double latitude;
    private Double longitude;

}
