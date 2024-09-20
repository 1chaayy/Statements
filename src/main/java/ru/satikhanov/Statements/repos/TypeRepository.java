package ru.satikhanov.Statements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.satikhanov.Statements.models.Type;
@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    Type getTypeByIdtype(int id);
}
