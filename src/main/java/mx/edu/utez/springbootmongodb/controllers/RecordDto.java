package mx.edu.utez.springbootmongodb.controllers;

import lombok.*;
import mx.edu.utez.springbootmongodb.models.location.Location;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class RecordDto {
    private Integer trashcanId;
    private Double distance;
    private Boolean isFull;
    private LocalDateTime dateAndTime;
    private Location location;

}
