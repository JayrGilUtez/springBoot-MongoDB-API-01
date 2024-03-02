package mx.edu.utez.springbootmongodb.models.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByMail(String mail);
    @Query(value = "{_id: ?0}", fields = "{role: 1}")
    String getIdUserRole(String idUser);
}
