package ru.satikhanov.Statements.services.user;

import ru.satikhanov.Statements.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void addUser(User user);
    void deleteUserByIduser(int id);
    void updateUser(int id, User user);
    User getUserByUsername(String username);
    Optional<User> getUserByIduser(int id);
    Optional<User> getUserByEmail(String email);
    List<User> getUsers();
}