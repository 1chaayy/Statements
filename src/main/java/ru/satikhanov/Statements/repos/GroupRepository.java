package ru.satikhanov.Statements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.satikhanov.Statements.models.Group;
@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group getGroupByIdgroup(int id);
}
