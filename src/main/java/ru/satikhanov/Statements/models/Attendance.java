package ru.satikhanov.Statements.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idattendance;

    @ManyToOne
    @JoinColumn(name = "idstudent")
    private Student idstudent;

    @ManyToOne
    @JoinColumn(name = "iddiscipline")
    private Discipline iddiscipline;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String status; // PRESENT, ABSENT, EXCUSED
}
