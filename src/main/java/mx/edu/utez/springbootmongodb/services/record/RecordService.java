package mx.edu.utez.springbootmongodb.services.record;

import mx.edu.utez.springbootmongodb.models.record.Record;
import mx.edu.utez.springbootmongodb.models.record.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordRepository repository;
    public RecordService(RecordRepository repository) {
        this.repository = repository;
    }

    public Record save(Record record){
        return repository.save(record);
    }

    public List<Record> findAll(){
        return repository.findAll();
    }

    public List<Record> findRecordsBySerialNumber(Integer serialNumber){
        return repository.findAllBySerialNumber(serialNumber);

    }

}
