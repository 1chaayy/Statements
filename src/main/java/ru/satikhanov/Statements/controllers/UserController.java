package ru.satikhanov.Statements.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.satikhanov.Statements.models.*;
import ru.satikhanov.Statements.models.Record;
import ru.satikhanov.Statements.security.UserDetails;
import ru.satikhanov.Statements.services.discipline.DisciplineService;
import ru.satikhanov.Statements.services.group.GroupService;
import ru.satikhanov.Statements.services.record.RecordService;
import ru.satikhanov.Statements.services.type.TypeService;
import ru.satikhanov.Statements.services.user.UserService;

import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {
    private final RecordService recordService;
    private final DisciplineService disciplineService;
    private final TypeService typeService;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public UserController(RecordService recordService, DisciplineService disciplineService, TypeService typeService, GroupService groupService, UserService userService) {
        this.recordService = recordService;
        this.disciplineService = disciplineService;
        this.typeService = typeService;
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping("")
    private String home(Model model) {
        UserDetails user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("user", user.getUser());
        model.addAttribute("records",recordService.getAllByIduser(user.getUser()));

        return "teacherHome";
    }

    @PostMapping("/deleteRecord/{id}")
    public String deleteRecord(@PathVariable("id") int id) {
        recordService.deleteRecordByIdrecord(id);
        return "redirect:/user"; // перенаправление на страницу со списком записей после удаления
    }

    @GetMapping("/addRecord/{id}")
    public String addRecordForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("record", new Record());
        model.addAttribute("disciplines", disciplineService.getAllDisciplines());
        model.addAttribute("types", typeService.getAllTypes());
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("user", userService.getUserByIduser(id).get());

        return "addRecord";
    }

    @PostMapping("/addRecord/{id}")
    public String addRecord(@PathVariable("id") int id, @ModelAttribute("record") Record record) {
        record.setStatus("Открыта");
        record.setIduser(userService.getUserByIduser(id).get());
        recordService.addRecord(record);
        return "redirect:/user";
    }
}
