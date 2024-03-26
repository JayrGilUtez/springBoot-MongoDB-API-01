package mx.edu.utez.springbootmongodb.controllers.trashcan;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TrashcanDto {
    private Integer serialNumber;
    private String trashcanName;
    private Double level; // Nivel de llenado.
}
