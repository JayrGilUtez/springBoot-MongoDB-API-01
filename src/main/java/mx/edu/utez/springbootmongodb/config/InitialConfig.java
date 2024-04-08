package mx.edu.utez.springbootmongodb.config;

import lombok.RequiredArgsConstructor;
import mx.edu.utez.springbootmongodb.models.role.Role;
import mx.edu.utez.springbootmongodb.models.role.RoleRepository;
import mx.edu.utez.springbootmongodb.models.user.User;
import mx.edu.utez.springbootmongodb.models.user.UserRepository;
import mx.edu.utez.springbootmongodb.services.db.DatabaseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class InitialConfig implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    // Borrar base
    private final DatabaseService databaseService;

    @Override
    public void run(String... args) throws Exception {
        // Borrar base
        databaseService.resetDatabase();

        Role adminRole = getOrSaveRole(new Role("ADMIN"));
        Role userRole = getOrSaveRole(new Role("USER"));

        User user = new User();
        user.setMail("noriega@mail.com");
        user.setPassword(passwordEncoder.encode("123456"));
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        user.setRole(roles);
        user.setStatus(true);
        user.setBlocked(false);
        userRepository.save(user);

        User user2 =new User();
        user2.setMail("Test@mail.com");
        user2.setPassword(passwordEncoder.encode("test"));
        Set<Role> roles2 = new HashSet<>();
        roles2.add(userRole);
        user2.setRole(roles2);
        user2.setStatus(true);
        user2.setBlocked(false);
        userRepository.save(user2);
    }

    public Role getOrSaveRole(Role role) {
        Optional<Role> foundRole = roleRepository.findByName(role.getName());
        return foundRole.orElseGet(() -> roleRepository.save(role));
    }
}
