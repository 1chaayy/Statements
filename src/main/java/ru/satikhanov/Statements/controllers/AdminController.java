package ru.satikhanov.Statements.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.satikhanov.Statements.models.Group;
import ru.satikhanov.Statements.models.Student;
import ru.satikhanov.Statements.models.User;
import ru.satikhanov.Statements.security.UserDetails;
import ru.satikhanov.Statements.services.group.GroupService;
import ru.satikhanov.Statements.services.record.RecordService;
import ru.satikhanov.Statements.services.student.StudentService;
import ru.satikhanov.Statements.services.user.UserService;
import ru.satikhanov.Statements.util.UserValidator;

import javax.validation.Valid;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final GroupService groupService;
    private final RecordService recordService;
    private final UserValidator userValidator;
    private final StudentService studentService;


    @Autowired
    public AdminController(UserService userService, GroupService groupService, RecordService recordService, UserValidator userValidator, StudentService studentService) {
        this.userService = userService;
        this.groupService = groupService;
        this.recordService = recordService;
        this.userValidator = userValidator;
        this.studentService = studentService;
    }

    @GetMapping("")
    private String home(Model model) {

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

    @PostMapping("/createUser")
    public String createUser(@ModelAttribute("newUser") @Valid User user,
                           BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "adminHome";
        }
        userService.addUser(user);
        return "redirect:/admin";
    }
    @PostMapping("/createGroup")
    public String createGroup(@ModelAttribute("newGroup") @Valid Group group,
                             BindingResult result) {
        if (result.hasErrors()) {
            return "adminHome";
        }
        groupService.addGroup(group);
        return "redirect:/admin";
    }

    @GetMapping("/updateUser/{id}")
    public String getUpdateUserPage(@PathVariable int id, Model model){
        System.out.println(id);
        User user =  userService.getUserByIduser(id).get();
        model.addAttribute("updatedUser",user);
        return "updateUser";
    }

    @PostMapping("/updateUser/{id}")
    public String updateUser(@PathVariable int id, @ModelAttribute("updatedUser") @Valid User user,
                             BindingResult result){
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "updateUser";
        }
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/createStudent")
    public String createStudent(@ModelAttribute("newStudent") @Valid Student student,
                                BindingResult result) {
        if(result.hasErrors()) {
            return "adminHome";
        }

        studentService.save(student);
        return "redirect:/admin";
    }

    @PostMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id){
        studentService.deleteStudentById(id);
        return "redirect:/admin";
    }

    @GetMapping("/groupStudents/{id}")
    public String getStudents(@PathVariable int id, Model model) {
        Group group = groupService.getGroupByIdgroup(id);
        model.addAttribute("students", studentService.getStudentByGroupId(group));
        model.addAttribute("group", groupService.getGroupByIdgroup(id));
        return "groupStudents";
    }
    @ModelAttribute
    public void addModelAttributes(Model model){
        model.addAttribute("user", ((UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser());
        model.addAttribute("users", userService.getUsers());
        model.addAttribute("groups", groupService.getAllGroups());
        model.addAttribute("records", recordService.getRecords());
        model.addAttribute("newUser", new User());
        model.addAttribute("newGroup", new Group());
        model.addAttribute("newStudent", new Student());
    }
}
