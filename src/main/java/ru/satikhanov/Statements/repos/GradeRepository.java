package ru.satikhanov.Statements.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.satikhanov.Statements.models.Grade;
import ru.satikhanov.Statements.models.Student;

import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade,Integer> {

    @Query("select g from Grade g where g.student.idstudent=:idStudent and g.record.idrecord=:idRecord")
    Optional<Grade> getGradeByStudentAndRecord(Integer idStudent, Integer idRecord);
    Grade getGradeByStudent(Student student);
}
