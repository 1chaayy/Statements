package ru.satikhanov.Statements.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import ru.satikhanov.Statements.models.User;
import ru.satikhanov.Statements.repos.UserRepository;
import ru.satikhanov.Statements.services.user.UserService;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        userService.getUserByEmail(user.getEmail()).ifPresent(existingUser -> {
            if (existingUser.getIduser() != user.getIduser()) {
                errors.rejectValue("email", null, "Email уже занят!");
            }
        });
    }
}