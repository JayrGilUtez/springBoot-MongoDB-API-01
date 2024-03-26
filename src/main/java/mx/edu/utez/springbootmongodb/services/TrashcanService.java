package mx.edu.utez.springbootmongodb.services;

import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.models.record.Record;
import mx.edu.utez.springbootmongodb.models.record.RecordRepository;
import mx.edu.utez.springbootmongodb.models.trashcan.Trashcan;
import mx.edu.utez.springbootmongodb.models.trashcan.TrashcanRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.text.DecimalFormat;

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
        return new ResponseEntity<>(new ApiResponse(trashcanRepository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> findByName(String trashcanName){
        return new ResponseEntity<>(new ApiResponse(trashcanRepository.findByTrashcanName(trashcanName),HttpStatus.OK), HttpStatus.OK);
    }

    public Trashcan updateLevelByLastRecord(Integer serialNumber) {
        Record lastRecord = recordRepository.findFirstBySerialNumberOrderByDateAndTimeDesc(serialNumber);
        Trashcan trashcan = trashcanRepository.findBySerialNumber(serialNumber);
        if (trashcan != null && lastRecord != null) {

            // Aqui va el calculo del porcenteaje de llenado partiendo de la distancia detectada

            //trashcan.setLevel(lastRecord.getDistance());
            trashcan.setLevel(getLevel(lastRecord.getDistance()));


            return trashcanRepository.save(trashcan);
        }
        return null;
    }



    public void deleteBySerialNumber(Integer serialNumber){
        trashcanRepository.deleteBySerialNumber(serialNumber);
    }

    //
    public Double getLevel(Double distance){
        DecimalFormat df = new DecimalFormat("#.#");
        Double level = ((distance * 100)/90);
        return Double.valueOf(df.format(level));
    }

}
