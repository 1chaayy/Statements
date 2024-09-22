package ru.satikhanov.Statements.services.user;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.satikhanov.Statements.models.User;
import ru.satikhanov.Statements.repos.RoleRepository;
import ru.satikhanov.Statements.repos.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Data
@Service
@Primary
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    @Transactional
    public void addUser(User user) {
        user.setRoleList(Collections.singletonList(roleRepository.getRoleByIdrole(2)));
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserByIduser(int id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUser(int id, User user) {
        user.setRoleList(userRepository.getUserByIduser(id).get().getRoleList());
        user.setIduser(id);
        userRepository.save(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByIduser(int id) {
        return userRepository.getUserByIduser(id);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}