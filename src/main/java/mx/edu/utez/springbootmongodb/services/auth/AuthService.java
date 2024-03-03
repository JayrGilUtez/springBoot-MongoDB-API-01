package mx.edu.utez.springbootmongodb.services.auth;

import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.controllers.auth.dto.SignedDto;
import mx.edu.utez.springbootmongodb.models.user.User;
import mx.edu.utez.springbootmongodb.security.jwt.JwtProvider;
import mx.edu.utez.springbootmongodb.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthService(UserService service, AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.service = service;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    public ResponseEntity<ApiResponse> signIn(String mail, String password) {
        try {
            Optional<User> foundUser = service.findByMail(mail);
            if (foundUser.isEmpty())
                return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND, true, "User not found"), HttpStatus.NOT_FOUND);
            User user = foundUser.get();
            if (!user.isStatus())
                return new ResponseEntity<>(new ApiResponse(HttpStatus.FORBIDDEN, true, "User is not active"), HttpStatus.FORBIDDEN);
            if (user.isBlocked())
                return new ResponseEntity<>(new ApiResponse(HttpStatus.FORBIDDEN, true, "User is blocked"), HttpStatus.FORBIDDEN);
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(mail, password)
            );
            SecurityContextHolder.getContext().setAuthentication(auth);
            String token = jwtProvider.generateToken(auth);
            SignedDto signedDto = new SignedDto(token, "Bearer", user, user.getRole().stream().toList());
            return new ResponseEntity<>(new ApiResponse(signedDto, HttpStatus.OK), HttpStatus.OK);
        } catch (Exception e) {
            String message = "Credentials are incorrect\n" + e.getMessage();
            if (e instanceof DisabledException)
                message = "User is not active";
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, message), HttpStatus.BAD_REQUEST);
        }
    }

}
