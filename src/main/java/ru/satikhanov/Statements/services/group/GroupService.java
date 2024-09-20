package ru.satikhanov.Statements.services.group;

import ru.satikhanov.Statements.models.Group;

import java.util.List;

public interface GroupService {
    void addGroup(Group group);
    void deleteGroupByIdgroup(int id);
    void updateGroup(Group group);
    List<Group> getAllGroups();
    Group getGroupByIdgroup(int id);
}
