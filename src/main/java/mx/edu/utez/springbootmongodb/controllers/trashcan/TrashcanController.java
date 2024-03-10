package mx.edu.utez.springbootmongodb.controllers.trashcan;

import mx.edu.utez.springbootmongodb.models.trashcan.Trashcan;
import mx.edu.utez.springbootmongodb.services.TrashcanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/trashcan")
public class TrashcanController {
    private final TrashcanService service;
    public TrashcanController(TrashcanService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Trashcan> getAll(){
        return service.findAll();
    }

    @GetMapping("/{trashcanName}")
    public Trashcan findByTrashcanName(@PathVariable String trashcanName){
        return service.findByName(trashcanName);
    }

    @PostMapping("/")
    public Trashcan save(@RequestBody TrashcanDto trashcanDto){
        Trashcan trashcan = Trashcan.builder()
                .serialNumber(trashcanDto.getSerialNumber())
                .trashcanName(trashcanDto.getTrashcanName())
                .build();
        return service.save(trashcan);

    }





}
