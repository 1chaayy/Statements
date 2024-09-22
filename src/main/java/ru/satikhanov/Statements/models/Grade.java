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
    private int id;

    @ManyToOne
    @JoinColumn(name = "idstudent")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "idrecord")
    private Record record;

    @Column(name = "grade")
    private String grade;

}