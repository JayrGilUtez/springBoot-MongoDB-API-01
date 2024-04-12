package mx.edu.utez.springbootmongodb.controllers;


import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.models.location.Location;
import mx.edu.utez.springbootmongodb.models.record.Record;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.edu.utez.springbootmongodb.services.record.RecordService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/currentDayRecords/{serialNumber}")
    public ResponseEntity<ApiResponse> getAllRecordsOfCurrentDayRecords(@PathVariable Integer serialNumber){
        return service.findAllRecordsOfCurrentDay(serialNumber);
    }

    @GetMapping("/lastSevenDaysRecords/{serialNumber}")
    public ResponseEntity<ApiResponse> getLastSevenDaysRecords(@PathVariable Integer serialNumber){
        return service.findAllRecordsOfLastSevenDays(serialNumber);
    }

    @GetMapping("/location/{serialNumber}")
    public ResponseEntity<ApiResponse> getLastRecordLocation(@PathVariable Integer serialNumber) {
        Record lastRecord = service.findLastRecordBySerialNumber(serialNumber);
        if (lastRecord != null) {
            return new ResponseEntity<>(new ApiResponse(lastRecord.getLocation(), HttpStatus.OK), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse("No record found for this serial number", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/lastLocations")
    public ResponseEntity<ApiResponse> getLastLocations() {
        Map<Integer, Location> lastLocations = service.findLastLocations();
        return new ResponseEntity<>(new ApiResponse(lastLocations, HttpStatus.OK), HttpStatus.OK);
    }


}
