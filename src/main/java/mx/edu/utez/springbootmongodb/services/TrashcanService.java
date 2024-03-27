package mx.edu.utez.springbootmongodb.services;

import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.models.record.Record;
import mx.edu.utez.springbootmongodb.models.record.RecordRepository;
import mx.edu.utez.springbootmongodb.models.trashcan.Trashcan;
import mx.edu.utez.springbootmongodb.models.trashcan.TrashcanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrashcanService {
    private final TrashcanRepository trashcanRepository;
    private final RecordRepository recordRepository;
    public TrashcanService(TrashcanRepository trashcanRepository, RecordRepository recordRepository) {
        this.trashcanRepository = trashcanRepository;
        this.recordRepository = recordRepository;
    }
    public Trashcan save (Trashcan trashcan){

        return trashcanRepository.save(trashcan);
    }

    public ResponseEntity<ApiResponse> findBySerialNumber(Integer serialNumber){
        return new ResponseEntity<>(new ApiResponse(trashcanRepository.findBySerialNumber(serialNumber), HttpStatus.OK),HttpStatus.OK);
        //return repository.findBySerialNumber(serialNumber);
    }

    public ResponseEntity<ApiResponse> findAll(){
        List<Trashcan> trashcans = trashcanRepository.findAll();
        for(Trashcan trashcan : trashcans){
            Record lastRecord = recordRepository.findFirstBySerialNumberOrderByDateAndTimeDesc(trashcan.getSerialNumber());
            trashcan.setLevel(getLevel(lastRecord.getDistance()));
        }
        return new ResponseEntity<>(new ApiResponse(trashcans, HttpStatus.OK), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> findByName(String trashcanName){
        return new ResponseEntity<>(new ApiResponse(trashcanRepository.findByTrashcanName(trashcanName),HttpStatus.OK), HttpStatus.OK);
    }


    public void deleteBySerialNumber(Integer serialNumber){
        trashcanRepository.deleteBySerialNumber(serialNumber);
    }

    public double getLevel(double distance){
        double dMax = 90.0;
        double dMin = 10.0;
        double level = (1-( (distance - dMin) / (dMax - dMin) )) * 100;
        return Math.round(level * 10.0) / 10.0;
    }

}
