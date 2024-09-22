package ru.satikhanov.Statements.services.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.satikhanov.Statements.models.Group;
import ru.satikhanov.Statements.models.Student;
import ru.satikhanov.Statements.repos.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Transactional()
    public void save(Student student){
        studentRepository.save(student);
    }
    @Transactional(readOnly = true)
    public List<Student> getStudentByGroupId(Group id){
        return studentRepository.getStudentsByIdgroup(id);
    }
    @Transactional
    public void deleteStudentById(Integer id){
        studentRepository.deleteById(id);
    }
}
