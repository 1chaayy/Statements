package ru.satikhanov.Statements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.satikhanov.Statements.models.Discipline;
@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Integer> {
    Discipline getDisciplineByIddiscipline(int id);
}
