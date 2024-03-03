package mx.edu.utez.springbootmongodb.services.user;

import mx.edu.utez.springbootmongodb.models.role.Role;
import mx.edu.utez.springbootmongodb.models.role.RoleRepository;
import mx.edu.utez.springbootmongodb.models.user.User;
import mx.edu.utez.springbootmongodb.models.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public void saveUserRole(String roleId, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRole().add(role);
        userRepository.save(user);
    }
}
