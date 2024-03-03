package mx.edu.utez.springbootmongodb.controllers.auth.dto;

import lombok.Value;
import mx.edu.utez.springbootmongodb.models.role.Role;
import mx.edu.utez.springbootmongodb.models.user.User;

import java.util.List;


@Value
public class SignedDto {
    String token;
    String tokenType;
    User user;
    List<Role> roles;
}
