package mx.edu.utez.springbootmongodb.models.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "users")
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String mail;
    private String password;
    private String role;
    private boolean status;
    private boolean blocked;
}
