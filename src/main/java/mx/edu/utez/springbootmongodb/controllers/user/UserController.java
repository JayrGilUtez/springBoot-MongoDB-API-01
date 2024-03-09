package mx.edu.utez.springbootmongodb.controllers.user;

import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.controllers.dto.user.UserDto;
import mx.edu.utez.springbootmongodb.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/change-password")
    public ResponseEntity<ApiResponse> changePassword(@RequestBody UserDto user) {
        return service.changePassword(user.getMail(), user.getPassword());
    }

}
