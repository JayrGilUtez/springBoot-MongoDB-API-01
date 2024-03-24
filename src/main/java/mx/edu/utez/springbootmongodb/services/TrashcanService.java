package mx.edu.utez.springbootmongodb.services;

import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.models.trashcan.Trashcan;
import mx.edu.utez.springbootmongodb.models.trashcan.TrashcanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class TrashcanService {
    private final TrashcanRepository repository;
    public TrashcanService(TrashcanRepository repository) {
        this.repository = repository;
    }
    public Trashcan save (Trashcan trashcan){

        return repository.save(trashcan);
    }

    public Trashcan findBySerialNumber(Integer serialNumber){
        return repository.findBySerialNumber(serialNumber);
    }

    public ResponseEntity<ApiResponse> findAll(){
        return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    public Trashcan findByName(String trashcanName){
        return repository.findByTrashcanName(trashcanName);
    }

    public void deleteBySerialNumber(Integer serialNumber){
        repository.deleteBySerialNumber(serialNumber);
    }



}
