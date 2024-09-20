package ru.satikhanov.Statements.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "disciplines")
public class Discipline {
    @Id
    @GeneratedValue
    private int iddiscipline;

    @Column(name = "name", unique = true)
    @Size(min=1, max=100, message = "Название должно быть больше 0 и меньше 100 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String name;

    @OneToMany(mappedBy = "iddiscipline")
    private List<Record> records;
}
