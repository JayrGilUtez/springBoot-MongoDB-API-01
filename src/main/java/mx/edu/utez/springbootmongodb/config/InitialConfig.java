package mx.edu.utez.springbootmongodb.config;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.springbootmongodb.models.user.User;
import mx.edu.utez.springbootmongodb.models.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class InitialConfig implements CommandLineRunner {
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setMail("noriega@mail.com");
        user.setPassword("123456");
        user.setRole("ADMIN");
        user.setStatus(true);
        user.setBlocked(false);
        userRepository.save(user);
    }
}
