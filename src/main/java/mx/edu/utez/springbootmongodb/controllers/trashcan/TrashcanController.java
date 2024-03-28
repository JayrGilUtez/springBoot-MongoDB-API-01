package mx.edu.utez.springbootmongodb.controllers.trashcan;

import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.models.record.RecordRepository;
import mx.edu.utez.springbootmongodb.models.trashcan.Trashcan;
import mx.edu.utez.springbootmongodb.services.TrashcanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@RestController
@RequestMapping("api/v1/trashcan")
public class TrashcanController {
    private final TrashcanService service;
    private final RecordRepository recordService;
    public TrashcanController(TrashcanService service, RecordRepository recordService) {
        this.service = service;
        this.recordService = recordService;
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse> getAll(){
        return service.findAll();
    }

    @GetMapping("/{serialNumber}")
    public ResponseEntity<ApiResponse> getBySerialNumber(@PathVariable Integer serialNumber){
        return service.findBySerialNumber(serialNumber);
    }

    @GetMapping("/name/{trashcanName}")
    public ResponseEntity<ApiResponse> findByTrashcanName(@PathVariable String trashcanName){
        return service.findByName(trashcanName);
    }

    @PostMapping("save/")
    public Trashcan save(@RequestBody TrashcanDto trashcanDto){
        Trashcan trashcan = Trashcan.builder()
                .serialNumber(trashcanDto.getSerialNumber())
                .trashcanName(trashcanDto.getTrashcanName())
                .level(0.0) // 0% es el nivel por default
                .build();
        return service.save(trashcan);
    }

    @PutMapping("update/")
    public Trashcan update(@RequestBody TrashcanDto trashcanDto) {
        // Primero buscamos el Trashcan existente por el serialNumber que viene en el dto
        ResponseEntity<ApiResponse> apiResponse = service.findBySerialNumber(trashcanDto.getSerialNumber());

        Trashcan existingTrashcan =  (Trashcan) Objects.requireNonNull(apiResponse.getBody()).getData();

        //Trashcan existingTrashcan = service.findBySerialNumber(trashcanDto.getSerialNumber());

        // Si existe el Trashcan
        if (existingTrashcan != null) {
            // Actualizamos los campos de ese trashcan
            existingTrashcan.setTrashcanName(trashcanDto.getTrashcanName());
            // Por ultimo hacemos un save para que se actualice en la db
            return service.save(existingTrashcan);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trashcan not found");
        }
    }


    @DeleteMapping("delete/{serialNumber}")
    public void deleteBySerialNumber(@PathVariable Integer serialNumber){
        service.deleteBySerialNumber(serialNumber);
    }




}
