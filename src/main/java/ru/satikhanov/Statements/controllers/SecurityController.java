package ru.satikhanov.Statements.controllers;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.satikhanov.Statements.models.User;
import ru.satikhanov.Statements.services.user.UserService;
import ru.satikhanov.Statements.util.UserValidator;

import javax.validation.Valid;

@Data
@Controller
public class SecurityController {
    private final UserValidator userValidator;
    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(@ModelAttribute("user") User user) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid User user,
                           BindingResult result) {
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "register";
        }
        userService.addUser(user);
        return "redirect:/login";
    }

    @GetMapping("/account/{id}")
    private String account(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserByIduser(id).get());
        return "account";
    }

    @PostMapping("/account/{id}")
    private String accountEdit(@PathVariable("id") int id, @ModelAttribute("user") @Valid User user,
                               BindingResult result) {

        user.setIduser(id);
        userValidator.validate(user, result);
        if (result.hasErrors()) {
            return "account";
        }
        userService.updateUser(id, user);
        return "redirect:/logout";
    }
}
