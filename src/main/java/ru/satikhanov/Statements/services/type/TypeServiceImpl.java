package ru.satikhanov.Statements.services.type;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.satikhanov.Statements.models.Discipline;
import ru.satikhanov.Statements.models.Type;
import ru.satikhanov.Statements.repos.TypeRepository;

import java.util.List;

@Data
@Service
@Primary
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;

    @Override
    public void addType(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void deleteTypeByIdtype(int id) {
        typeRepository.deleteById(id);
    }

    @Override
    public void updateType(Type type) {
        typeRepository.save(type);
    }

    @Override
    public List<Type> getAllTypes() {
        return typeRepository.findAll();
    }

    @Override
    public Type getTypeByIdtype(int id) {
        return typeRepository.getTypeByIdtype(id);
    }
}
