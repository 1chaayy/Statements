package ru.satikhanov.Statements.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue
    @Column(name = "idgroup")
    private int idgroup;

    @Column(name = "name", unique = true)
    @Size(min=1, max=50, message = "Название должно быть больше 0 и меньше 50 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String name;

    @OneToMany(mappedBy = "idgroup")
    private List<Record> records;
}
