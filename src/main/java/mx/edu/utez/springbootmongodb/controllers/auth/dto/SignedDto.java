package mx.edu.utez.springbootmongodb.controllers.auth.dto;

import lombok.Value;
import org.springframework.security.core.userdetails.User;

import javax.management.relation.Role;
import java.util.List;

@Value
public class SignedDto {
    String token;
    String tokenType;
    User user;
    String roles;
}
