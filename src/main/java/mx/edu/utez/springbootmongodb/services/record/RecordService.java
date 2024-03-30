package mx.edu.utez.springbootmongodb.services.record;

import com.mongodb.BasicDBObject;
import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.models.record.Record;
import mx.edu.utez.springbootmongodb.models.record.RecordRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.Arrays;
import java.util.List;

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
        String month = LocalDateTime.now().toString().substring(0,7); // Get current month --> YYYY-MM

        query.addCriteria(Criteria.where("serialNumber").is(serialNumber)
                .andOperator(Criteria.where("$expr").is(
                        new BasicDBObject("$regexMatch",
                                new BasicDBObject("input", new BasicDBObject("$substr", Arrays.asList("$dateAndTime", 0, 7)))
                                        .append("regex", month)
                                        .append("options", "i")
                        )
                ))
        );

        List<Record> records = mongoTemplate.find(query, Record.class);
        return new ResponseEntity<>(new ApiResponse(records, HttpStatus.OK), HttpStatus.OK);
    }




}
