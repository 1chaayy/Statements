package ru.satikhanov.Statements.services.group;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.satikhanov.Statements.models.Group;
import ru.satikhanov.Statements.repos.GroupRepository;

import java.util.List;

@Data
@Service
@Primary
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public void deleteGroupByIdgroup(int id) {
        groupRepository.deleteById(id);
    }

    @Override
    public void updateGroup(Group group) {
        groupRepository.save(group);
    }

    @Override
    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public Group getGroupByIdgroup(int id) {
        return groupRepository.getGroupByIdgroup(id);
    }
}
