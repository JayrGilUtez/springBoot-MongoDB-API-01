package mx.edu.utez.springbootmongodb.models.trashcan;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrashcanRepository extends MongoRepository<Trashcan, String> {
    Trashcan findByTrashcanName(String trashcanName);
    Trashcan findBySerialNumber(Integer serialNumber);
    void deleteBySerialNumber(Integer serialNumber);




}
