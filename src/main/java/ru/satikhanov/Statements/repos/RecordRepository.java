package ru.satikhanov.Statements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.satikhanov.Statements.models.Group;
import ru.satikhanov.Statements.models.Record;
import ru.satikhanov.Statements.models.User;

import java.util.List;
import java.util.Optional;
@Repository
public interface RecordRepository extends JpaRepository<Record, Integer> {
    Optional<Record> getRecordByIdrecord(int id);
    Optional<Record> getRecordByIddiscipline(int id);
    Optional<Record> getRecordByIdgroup(int id);
    Optional<Record> getRecordByIduser(int id);
    Optional<Record> getRecordByIdtype(int id);
    Optional<Record> getRecordByStatus(String status);
    List<Record> getAllByIduser(User id);
    List<Record> getAllByIdgroup(Group id);
}
