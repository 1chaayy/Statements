package ru.satikhanov.Statements.services.type;

import ru.satikhanov.Statements.models.Discipline;
import ru.satikhanov.Statements.models.Type;

import java.util.List;

public interface TypeService {
    void addType(Type type);
    void deleteTypeByIdtype(int id);
    void updateType(Type type);
    List<Type> getAllTypes();
    Type getTypeByIdtype(int id);
}
