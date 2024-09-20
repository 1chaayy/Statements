package ru.satikhanov.Statements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.satikhanov.Statements.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User getUserByUsername(String username);
    Optional<User> getUserByIduser(int id);
    Optional<User> getUserByEmail(String email);
}