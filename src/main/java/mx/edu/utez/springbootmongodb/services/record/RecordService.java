package mx.edu.utez.springbootmongodb.services.record;

import com.mongodb.BasicDBObject;
import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.models.location.Location;
import mx.edu.utez.springbootmongodb.models.record.Record;
import mx.edu.utez.springbootmongodb.models.record.RecordRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.*;

@Service
public class RecordService {
    private final RecordRepository repository;

    private final MongoTemplate mongoTemplate;

    public RecordService(RecordRepository repository, MongoTemplate mongoTemplate) {
        this.repository = repository;
        this.mongoTemplate = mongoTemplate;
    }

    public Record save(Record record) {
        System.out.println("|" + record.getDateAndTime() + "|");
        return repository.save(record);
    }

    public List<Record> findAll() {
        return repository.findAll();
    }

    public List<Record> findRecordsBySerialNumber(Integer serialNumber) {
        return repository.findAllBySerialNumber(serialNumber);

    }

    public ResponseEntity<ApiResponse> findAllRecordsOfCurrentMonth(Integer serialNumber) {
        Query query = new Query();
        String month = LocalDateTime.now().toString().substring(0, 7); // Get current month --> YYYY-MM

        query.addCriteria(Criteria.where("serialNumber").is(serialNumber)
                .andOperator(Criteria.where("$expr").is(
                        new BasicDBObject("$regexMatch",
                                new BasicDBObject("input", new BasicDBObject("$substr", Arrays.asList("$dateAndTime", 0, 10)))
                                        .append("regex", month)
                                        .append("options", "i")
                        )
                ))
        );

        List<Record> records = mongoTemplate.find(query, Record.class);
        return new ResponseEntity<>(new ApiResponse(records, HttpStatus.OK), HttpStatus.OK);
    }

    // findAllRecordsOfCurrentDay
    public ResponseEntity<ApiResponse> findAllRecordsOfCurrentDay(Integer serialNumber) {
        Query query = new Query();
        String day = LocalDateTime.now().toString().substring(0, 10); // Get current month --> YYYY-MM

        query.addCriteria(Criteria.where("serialNumber").is(serialNumber)
                .andOperator(Criteria.where("$expr").is(
                        new BasicDBObject("$regexMatch",
                                new BasicDBObject("input", new BasicDBObject("$substr", Arrays.asList("$dateAndTime", 0, 10)))
                                        .append("regex", day)
                                        .append("options", "i")
                        )
                ))
        );

        List<Record> records = mongoTemplate.find(query, Record.class);
        return new ResponseEntity<>(new ApiResponse(records, HttpStatus.OK), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> findAllRecordsOfLastSevenDays(Integer serialNumber) {
        //String day = LocalDateTime.now().toString().substring(0, 10); // Get current day --> YYYY-MM-DD
        List<Record> recordsOfTheWeek = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            Query query = new Query();
            String day = LocalDateTime.now().minusDays(i).toString().substring(0, 10);
            System.out.println(day);
            query.addCriteria(Criteria.where("serialNumber").is(serialNumber)
                    .andOperator(Criteria.where("$expr").is(
                            new BasicDBObject("$regexMatch",
                                    new BasicDBObject("input", new BasicDBObject("$substr", Arrays.asList("$dateAndTime", 0, 10)))
                                            .append("regex", day)
                                            .append("options", "i")
                            )
                    ))
            );

            List<Record> recordsOfOneDay = mongoTemplate.find(query, Record.class);
            recordsOfTheWeek.addAll(recordsOfOneDay);
        }
        return new ResponseEntity<>(new ApiResponse(recordsOfTheWeek, HttpStatus.OK), HttpStatus.OK);
    }

    public Record findLastRecordBySerialNumber(Integer serialNumber) {
        return repository.findFirstBySerialNumberOrderByDateAndTimeDesc(serialNumber);
    }

    public Map<Integer, Location> findLastLocations() {
        List<Record> records = repository.findAllSerialNumbers();
        Map<Integer, Location> lastLocations = new HashMap<>();
        for (Record record : records) {
            Query query = new Query();
            query.addCriteria(Criteria.where("serialNumber").is(record.getSerialNumber()));
            query.with(Sort.by(Sort.Direction.DESC, "dateAndTime"));
            query.limit(1);

            Record lastRecord = mongoTemplate.findOne(query, Record.class);
            if (lastRecord != null) {
                lastLocations.put(record.getSerialNumber(), lastRecord.getLocation());
            }
        }
        return lastLocations;
    }

}
