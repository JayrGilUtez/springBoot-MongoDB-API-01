package mx.edu.utez.springbootmongodb.controllers.auth.dto;

import lombok.Value;
import mx.edu.utez.springbootmongodb.models.user.User;


@Value
public class SignedDto {
    String token;
    String tokenType;
    User user;
    String roles;
}
