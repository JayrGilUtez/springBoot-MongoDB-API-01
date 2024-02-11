package mx.edu.utez.springbootmongodb.controllers;

import mx.edu.utez.springbootmongodb.models.record.Record;
import org.springframework.web.bind.annotation.*;
import mx.edu.utez.springbootmongodb.services.record.RecordService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/record")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Record> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public List<Record> getAllByTrashcanId(@PathVariable Integer id){
        return service.findRecordsByTrashcanId(id);
    }

    @PostMapping("/")
    public Record save(@RequestBody RecordDto recordDto){
        Record record = Record.builder()
                .trashcanId(recordDto.getTrashcanId())
                .distance(recordDto.getDistance())
                .isFull(recordDto.getIsFull())
                .dateAndTime(LocalDateTime.now())
                .location(recordDto.getLocation())
                .build();
        return service.save(record);

    }





}
