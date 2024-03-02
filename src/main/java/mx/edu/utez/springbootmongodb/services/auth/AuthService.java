package mx.edu.utez.springbootmongodb.services.auth;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.springbootmongodb.config.ApiResponse;
import mx.edu.utez.springbootmongodb.controllers.auth.dto.SignedDto;
import mx.edu.utez.springbootmongodb.models.user.User;
import mx.edu.utez.springbootmongodb.models.user.UserRepository;
import mx.edu.utez.springbootmongodb.security.jwt.JwtProvider;
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
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public ResponseEntity<ApiResponse> signIn(String mail, String password) {
        try {
            Optional<User> foundUser = userRepository.findByMail(mail);
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
            // TODO: Fix this
            SignedDto signedDto = new SignedDto(token, "Bearer", user, user.getRole());
            return new ResponseEntity<>(new ApiResponse(signedDto, HttpStatus.OK), HttpStatus.OK);
        } catch (Exception e) {
            String message = "Credentials are incorrect";
            if (e instanceof DisabledException)
                message = "User is not active";
            return new ResponseEntity<>(new ApiResponse(HttpStatus.BAD_REQUEST, true, message), HttpStatus.BAD_REQUEST);
        }
    }

}
