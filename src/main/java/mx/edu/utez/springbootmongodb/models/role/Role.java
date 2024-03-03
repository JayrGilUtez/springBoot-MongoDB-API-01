package mx.edu.utez.springbootmongodb.models.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.springbootmongodb.models.user.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(collection = "roles")
@NoArgsConstructor
@Setter
@Getter
public class Role {
    @Id
    private String id;
    private String name;
    private Set<User> users;

    public Role(String name) {
        this.name = name;
    }
}
