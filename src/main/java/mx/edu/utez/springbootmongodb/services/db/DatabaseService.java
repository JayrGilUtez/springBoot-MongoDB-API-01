package mx.edu.utez.springbootmongodb.services.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    public DatabaseService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void resetDatabase() {
        mongoTemplate.dropCollection("users");
        mongoTemplate.dropCollection("roles");
    }

}
