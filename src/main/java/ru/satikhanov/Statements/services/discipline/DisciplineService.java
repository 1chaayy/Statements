package ru.satikhanov.Statements.services.discipline;

import ru.satikhanov.Statements.models.Discipline;

import java.util.List;

public interface DisciplineService {
    void addDiscipline(Discipline discipline);
    void deleteDisciplineByIddiscipline(int id);
    void updateDiscipline(Discipline discipline);
    List<Discipline> getAllDisciplines();
    Discipline getDisciplineByIddiscipline(int id);
}
