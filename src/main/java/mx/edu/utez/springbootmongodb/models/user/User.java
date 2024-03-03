package mx.edu.utez.springbootmongodb.models.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.springbootmongodb.models.role.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    private String id;
    private String mail;
    private String password;
    private Set<Role> role;
    private boolean status;
    private boolean blocked;
}
