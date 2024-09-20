package ru.satikhanov.Statements.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Entity
@Component
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int iduser;

    @Column(name = "username")
    @Size(min=1, max=50, message = "Имя должно быть больше 0 и меньше 50 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String username;

    @Column(name = "password")
    @Size(min=1, max=50, message = "Неверный формат пароля!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String password;

    @Column(name = "lastname")
    @Size(min=1, max=50, message = "Фамилия должно быть больше 0 и меньше 50 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String lastname;

    @Column(name = "email")
    @Size(min=1, max=50, message = "Адрес электронной почты должно быть больше 0 и меньше 50 символов!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String email;

    @Column(name = "phone")
    @Pattern(regexp = "^(\\+7|8)[0-9]{10}$", message = "Некорректный номер!")
    @NotEmpty(message = "Поле не может быть пустым!")
    private String phone;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "id_users", referencedColumnName = "iduser"),
            inverseJoinColumns = @JoinColumn(name = "id_roles", referencedColumnName = "idrole")
    )
    private List<Role> roleList;

    @OneToMany(mappedBy = "iduser")
    private List<Record> records;
}

