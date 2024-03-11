package mx.edu.utez.springbootmongodb.services;

import mx.edu.utez.springbootmongodb.models.trashcan.Trashcan;
import mx.edu.utez.springbootmongodb.models.trashcan.TrashcanRepository;
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

    public List<Trashcan> findAll(){
        return repository.findAll();
    }

    public Trashcan findByName(String trashcanName){
        return repository.findByTrashcanName(trashcanName);
    }

    public void deleteBySerialNumber(Integer serialNumber){
        repository.deleteBySerialNumber(serialNumber);
    }



}
