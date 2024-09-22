package ru.satikhanov.Statements.services.grade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import ru.satikhanov.Statements.models.Grade;
import ru.satikhanov.Statements.models.Record;
import ru.satikhanov.Statements.models.Student;
import ru.satikhanov.Statements.repos.GradeRepository;
import ru.satikhanov.Statements.repos.RecordRepository;
import ru.satikhanov.Statements.repos.StudentRepository;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RecordRepository recordRepository;

    public void assignGrades(int idRecord, int idStudent, String grade) {
        Record record = recordRepository.getRecordByIdrecord(idRecord)
                .orElseThrow(() -> new NoSuchElementException("Record not found"));

        Student student = studentRepository.findById(idStudent)
                .orElseThrow(() -> new NoSuchElementException("Student not found"));

        Optional<Grade> gradeOptional = gradeRepository.getGradeByStudentAndRecord(idStudent, idRecord);
        if(gradeOptional.isEmpty()){
            Grade gradeEntity = new Grade();
            gradeEntity.setStudent(student);
            gradeEntity.setRecord(record);
            gradeEntity.setGrade(grade);
            gradeRepository.save(gradeEntity);
            System.out.println(gradeEntity);
        }else{
            Grade grade1 = gradeRepository.getById(gradeOptional.get().getId());
            grade1.setGrade(grade);
            System.out.println(grade1);
            gradeRepository.save(grade1);
        }

    }

    public Grade getGradeByStudentIdAndRecordId(Integer studentId, Integer recordId){
        return gradeRepository.getGradeByStudentAndRecord(studentId, recordId).get();
    }
}
