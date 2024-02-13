package mx.edu.utez.springbootmongodb.controllers;

import lombok.*;
import mx.edu.utez.springbootmongodb.models.location.Location;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RecordDto {
    private Integer trashcanId;
    private Double distance;
    private Boolean isFull;
    private String dateAndTime;
    private Location location;

}
