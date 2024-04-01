package mx.edu.utez.springbootmongodb.services.user;

import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.models.user.User;
import mx.edu.utez.springbootmongodb.models.user.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, @Lazy PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<ApiResponse> save(User user){

        return new ResponseEntity<>(new ApiResponse(repository.save(user),HttpStatus.OK), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> findAll() {
        return new ResponseEntity<>(new ApiResponse(repository.findAll(), HttpStatus.OK), HttpStatus.OK);
    }

    public Optional<User> findByMail(String mail) {
        return repository.findByMail(mail);
    }

    public ResponseEntity<ApiResponse> findById(String id) {
        return new ResponseEntity<>(new ApiResponse(repository.findById(id), HttpStatus.OK), HttpStatus.OK);
    }

    public ResponseEntity<ApiResponse> changePassword(String email, String password) {
        Optional<User> user = repository.findByMail(email);
        if (user.isPresent()) {
            user.get().setPassword(passwordEncoder.encode(password));
            repository.save(user.get());
            return new ResponseEntity<>(new ApiResponse("Password changed", HttpStatus.OK), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ApiResponse("User not found", HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
    }


    public void delete(User user){
        repository.delete(user);
    }

}
