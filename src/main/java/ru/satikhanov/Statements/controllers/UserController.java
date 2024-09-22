package ru.satikhanov.Statements.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.satikhanov.Statements.models.Grade;
import ru.satikhanov.Statements.models.Record;
import ru.satikhanov.Statements.models.Student;
import ru.satikhanov.Statements.security.UserDetails;
import ru.satikhanov.Statements.services.discipline.DisciplineService;
import ru.satikhanov.Statements.services.grade.GradeService;
import ru.satikhanov.Statements.services.group.GroupService;
import ru.satikhanov.Statements.services.record.RecordService;
import ru.satikhanov.Statements.services.student.StudentService;
import ru.satikhanov.Statements.services.type.TypeService;
import ru.satikhanov.Statements.services.user.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;


@Controller
@RequestMapping("/user")
public class UserController {
    private final RecordService recordService;
    private final DisciplineService disciplineService;
    private final TypeService typeService;
    private final GroupService groupService;
    private final UserService userService;
    private final StudentService studentService;
    private final GradeService gradeService;

    @Autowired
    public UserController(RecordService recordService, DisciplineService disciplineService, TypeService typeService, GroupService groupService, UserService userService, StudentService studentService, GradeService gradeService) {
        this.recordService = recordService;
        this.disciplineService = disciplineService;
        this.typeService = typeService;
        this.groupService = groupService;
        this.userService = userService;
        this.studentService = studentService;
        this.gradeService = gradeService;
    }

    @GetMapping("")
    private String home(Model model) {


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
    @GetMapping("/changeRecord/{id}")
    public String changeRecord(@PathVariable("id") int id, Model model) {
        Record record = recordService.getRecordByIdrecord(id).orElseThrow(() -> new NoSuchElementException("Record not found"));
        List<Student> studentsGroup = studentService.getStudentByGroupId(record.getIdgroup());

        // Создайте Map для хранения оценок
        Map<Integer, String> gradesMap = new HashMap<>();

        for (Student student : studentsGroup) {
            Grade grade = gradeService.getGradeByStudentIdAndRecordId(student.getIdstudent(), id);
            // Сохраните оценку в Map
            gradesMap.put(student.getIdstudent(), grade != null ? grade.getGrade() : null);
        }

        // Добавьте Map в модель
        model.addAttribute("grades", gradesMap);
        model.addAttribute("studentsGroup", studentsGroup);
        model.addAttribute("cRecord", record);

        return "changeRecord";
    }
    @PostMapping("/closeRecord/{id}")
    public String closeRecord(@PathVariable int id, Model model){
        recordService.closeRecordById(id);
        return "redirect:/user";
    }
    @PostMapping("/addRecord/{id}")
    public String addRecord(@PathVariable("id") int id, @ModelAttribute("record") Record record) {
        record.setStatus("Открыта");
        record.setIduser(userService.getUserByIduser(id).get());
        recordService.addRecord(record);
        return "redirect:/user";
    }
    @ModelAttribute
    public void addModels(Model model){
        UserDetails user = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        model.addAttribute("user", user.getUser());
        model.addAttribute("records",recordService.getAllByIduser(user.getUser()));

    }

    @PostMapping("/assignGrades/{id}")
    public String assignGrades(@PathVariable("id") int idRecord,
                               @RequestParam Map<String, String> allParams) {
        // Извлекаем оценки и идентификаторы студентов
        allParams.forEach((key, value) -> {
            if (key.startsWith("grades_")) {
                String cleanedKey = key.replace("grades_", ""); // Извлекаем ID студента из ключа
                int studentId = Integer.parseInt(cleanedKey);
                String grade = value;

                System.out.println("ID студента: " + studentId + ", Оценка: " + grade);

                // Вызов сервиса для сохранения оценки
                gradeService.assignGrades(idRecord, studentId, grade);
            }
        });

        return "redirect:/user";
    }
}
