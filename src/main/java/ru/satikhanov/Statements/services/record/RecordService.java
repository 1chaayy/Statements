package ru.satikhanov.Statements.services.record;

import ru.satikhanov.Statements.models.Group;
import ru.satikhanov.Statements.models.Record;
import ru.satikhanov.Statements.models.User;

import java.util.List;
import java.util.Optional;

public interface RecordService {
    void addRecord(Record record);
    void deleteRecordByIdrecord(int id);
    void updateRecord(Record record);
    Optional<Record> getRecordByIdrecord(int id);
    Optional<Record> getRecordByIddiscipline(int id);
    Optional<Record> getRecordByIdgroup(int id);
    Optional<Record> getRecordByIduser(int id);
    Optional<Record> getRecordByIdtype(int id);
    Optional<Record> getRecordByStatus(String status);
    List<Record> getRecords();
    List<Record> getAllByIduser(User id);
    List<Record> getAllByIdgroup(Group id);
}
