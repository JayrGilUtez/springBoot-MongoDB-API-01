package mx.edu.utez.springbootmongodb.models.record;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface RecordRepository extends MongoRepository<Record, String> {

    List<Record> findAllBySerialNumber(Integer serialNumber);
    Record findFirstBySerialNumberOrderByDateAndTimeDesc(Integer serialNumber);
    // Eliminar todos los records con distance > 100
    void deleteAllByDistanceGreaterThan(Double distance);

    // Obtener todos los records de un bote y mes en especifco
    @Query("'{ 'serialNumber': ?0, $expr: { $regexMatch: { input: { $substr: [ '$dateAndTime', 0, 7 ] }, regex: ?1, options: 'i' } } }'")
    List<Record> findBySerialNumberAndMonth(Integer serialNumber, String month);

}
