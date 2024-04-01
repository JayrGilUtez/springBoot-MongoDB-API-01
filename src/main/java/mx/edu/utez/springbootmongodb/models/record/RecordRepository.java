package mx.edu.utez.springbootmongodb.models.record;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecordRepository extends MongoRepository<Record, String> {

    List<Record> findAllBySerialNumber(Integer serialNumber);
    //Obtener el ultimo registro
    Record findFirstBySerialNumberOrderByDateAndTimeDesc(Integer serialNumber);
    // Eliminar todos los records con distance > 100
    void deleteAllByDistanceGreaterThan(Double distance);

}
