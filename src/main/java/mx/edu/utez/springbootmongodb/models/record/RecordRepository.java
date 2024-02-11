package mx.edu.utez.springbootmongodb.models.record;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface RecordRepository extends MongoRepository<Record, String> {

    List<Record> findAllByTrashcanId(Integer trashcanId);



}
