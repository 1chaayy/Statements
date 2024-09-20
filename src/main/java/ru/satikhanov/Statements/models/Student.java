package ru.satikhanov.Statements.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idstudent;

    @Column(name = "firstname")
    @Size(min=1, max=50, message = "Имя должно быть больше 0 и меньше 50 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String firstname;

    @Column(name = "lastname")
    @Size(min=1, max=50, message = "Фамилия должно быть больше 0 и меньше 50 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String lastname;

    @ManyToOne
    @JoinColumn(name = "idgroup")
    private Group idgroup;
}
