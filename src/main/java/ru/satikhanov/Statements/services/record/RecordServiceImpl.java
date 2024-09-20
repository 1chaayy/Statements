package ru.satikhanov.Statements.services.record;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.satikhanov.Statements.models.Group;
import ru.satikhanov.Statements.models.Record;
import ru.satikhanov.Statements.models.User;
import ru.satikhanov.Statements.repos.RecordRepository;

import java.util.List;
import java.util.Optional;

@Data
@Service
@Primary
public class RecordServiceImpl implements RecordService {

    private final RecordRepository recordRepository;

    @Override
    public void addRecord(Record record) {
        recordRepository.save(record);
    }

    @Override
    public void deleteRecordByIdrecord(int id) {
        recordRepository.deleteById(id);
    }

    @Override
    public void updateRecord(Record record) {
        recordRepository.save(record);
    }

    @Override
    public Optional<Record> getRecordByIdrecord(int id) {
        return recordRepository.getRecordByIdrecord(id);
    }

    @Override
    public Optional<Record> getRecordByIddiscipline(int id) {
        return recordRepository.getRecordByIddiscipline(id);
    }

    @Override
    public Optional<Record> getRecordByIdgroup(int id) {
        return recordRepository.getRecordByIdgroup(id);
    }

    @Override
    public Optional<Record> getRecordByIduser(int id) {
        return recordRepository.getRecordByIduser(id);
    }
    @Override
    public List<Record> getAllByIduser(User id){
        return recordRepository.getAllByIduser(id);
    }

    @Override
    public List<Record> getAllByIdgroup(Group id){
        return recordRepository.getAllByIdgroup(id);
    }

    @Override
    public Optional<Record> getRecordByIdtype(int id) {
        return recordRepository.getRecordByIdtype(id);
    }

    @Override
    public Optional<Record> getRecordByStatus(String status) {
        return recordRepository.getRecordByStatus(status);
    }

    @Override
    public List<Record> getRecords() {
        return recordRepository.findAll();
    }
}
