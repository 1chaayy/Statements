package ru.satikhanov.Statements.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "types")
public class Type {

    @Id
    @GeneratedValue
    private int idtype;

    @Column(name = "name")
    @Size(min=1, max=50, message = "Название должно быть больше 0 и меньше 50 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String name;

    @OneToMany(mappedBy = "idtype")
    private List<Record> records;
}
