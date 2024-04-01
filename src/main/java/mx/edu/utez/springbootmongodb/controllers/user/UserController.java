package mx.edu.utez.springbootmongodb.controllers.user;

import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.controllers.dto.user.UserDto;
import mx.edu.utez.springbootmongodb.models.role.Role;
import mx.edu.utez.springbootmongodb.models.user.User;
import mx.edu.utez.springbootmongodb.services.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    @PostMapping("/")
    public ResponseEntity<ApiResponse> save(@RequestBody UserDto userDto){
        Set<Role> roles = new HashSet<>();
        Role role = new Role("USER");
        roles.add(role);
        User user = User.builder()
                .password(userDto.getPassword())
                .mail(userDto.getMail())
                .role(roles)
                .build();
        return service.save(user);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable String id){
        Optional<User> optionalUser = (Optional<User>) service.findById(id).getBody().getData();
        if(optionalUser.isPresent()){
            service.delete(optionalUser.get());
        }
    }

}
