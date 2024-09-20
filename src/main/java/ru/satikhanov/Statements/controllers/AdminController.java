package ru.satikhanov.Statements.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.satikhanov.Statements.security.UserDetails;
import ru.satikhanov.Statements.services.group.GroupService;
import ru.satikhanov.Statements.services.record.RecordService;
import ru.satikhanov.Statements.services.user.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final GroupService groupService;
    private final RecordService recordService;

    @Autowired
    public AdminController(UserService userService, GroupService groupService, RecordService recordService) {
        this.userService = userService;
        this.groupService = groupService;
        this.recordService = recordService;
    }

    @GetMapping("")
    private String home(Model model) {
        model.addAttribute("user", ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("records", recordService.getRecords());
        return "adminHome";
    }

    @PostMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteUserByIduser(id);
        return "redirect:/admin";
    }

    @PostMapping("/deleteGroup/{id}")
    public String deleteGroup(@PathVariable("id") int id) {
        groupService.deleteGroupByIdgroup(id);
        return "redirect:/admin";
    }

    @PostMapping("/deleteRecord/{id}")
    public String deleteRecord(@PathVariable("id") int id) {
        recordService.deleteRecordByIdrecord(id);
        return "redirect:/admin";
    }

    @GetMapping("/userRecords/{id}")
    public String userRecords(Model model, @PathVariable int id) {
        model.addAttribute("records", recordService.getAllByIduser(userService.getUserByIduser(id).get()));
        return "userRecords";
    }

    @GetMapping("/groupRecords/{id}")
    public String groupRecords(Model model, @PathVariable int id) {
        model.addAttribute("records", recordService.getAllByIdgroup(groupService.getGroupByIdgroup(id)));
        return "groupRecords";
    }
}
