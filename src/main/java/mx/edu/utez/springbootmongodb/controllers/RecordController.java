package mx.edu.utez.springbootmongodb.controllers;


import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.models.record.Record;
import org.springframework.http.ResponseEntity;
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
    public List<Record> getAll() {
        return service.findAll();
    }

    @GetMapping("/{serialNumber}")
    public List<Record> getAllBySerialNumber(@PathVariable Integer serialNumber) {
        return service.findRecordsBySerialNumber(serialNumber);
    }

    @PostMapping("/")
    public Record save(@RequestBody RecordDto recordDto) {
        Record record = Record.builder()
                .serialNumber(recordDto.getSerialNumber())
                .distance(recordDto.getDistance())
                .isFull(recordDto.getIsFull())
                .dateAndTime(LocalDateTime.now().minusHours(6).toString())
                .location(recordDto.getLocation())
                .build();
        return service.save(record);
    }

    // getAllRecordsOfCurrentMonth
    @GetMapping("/currentMonthRecords/{serialNumber}")
    public ResponseEntity<ApiResponse> getAllRecordsOfCurrentMonth(@PathVariable Integer serialNumber) {
        return service.findAllRecordsOfCurrentMonth(serialNumber);
    }

}
