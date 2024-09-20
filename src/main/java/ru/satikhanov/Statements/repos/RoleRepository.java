package ru.satikhanov.Statements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.satikhanov.Statements.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getRoleByIdrole(int id);
}
