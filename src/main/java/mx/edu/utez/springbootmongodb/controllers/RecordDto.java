package mx.edu.utez.springbootmongodb.controllers;

import lombok.*;
import mx.edu.utez.springbootmongodb.models.location.Location;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RecordDto {
    private Integer serialNumber;
    private Double distance;
    private Boolean isFull;
    private String dateAndTime;
    private Location location;
}
