package ru.satikhanov.Statements.services.discipline;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.satikhanov.Statements.models.Discipline;
import ru.satikhanov.Statements.models.User;
import ru.satikhanov.Statements.repos.DisciplineRepository;

import java.util.List;

@Data
@Service
@Primary
public class DisciplineServiceImpl implements DisciplineService {
    private final DisciplineRepository disciplineRepository;

    @Override
    public void addDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    @Override
    public void deleteDisciplineByIddiscipline(int id) {
        disciplineRepository.deleteById(id);
    }

    @Override
    public void updateDiscipline(Discipline discipline) {
        disciplineRepository.save(discipline);
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        return disciplineRepository.findAll();
    }

    @Override
    public Discipline getDisciplineByIddiscipline(int id) {
        return disciplineRepository.getDisciplineByIddiscipline(id);
    }
}
