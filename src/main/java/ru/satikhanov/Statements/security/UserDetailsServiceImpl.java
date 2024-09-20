package ru.satikhanov.Statements.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.satikhanov.Statements.models.User;
import ru.satikhanov.Statements.services.user.UserService;

import java.util.Optional;

@Getter
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.getUserByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Имя пользователя не найдено!");
        }
        User user = optionalUser.get();
        return new ru.satikhanov.Statements.security.UserDetails(user);
    }

}