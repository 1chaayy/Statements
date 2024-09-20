package ru.satikhanov.Statements.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idgrade;

    @ManyToOne
    @JoinColumn(name = "idstudent")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "iddiscipline")
    private Discipline discipline;

    @Column(name = "grade")
    private int grade; // Например, от 1 до 100

    @ManyToOne
    @JoinColumn(name = "iduser")
    private User teacher;

    @Column(name = "date")
    private LocalDate date;
}