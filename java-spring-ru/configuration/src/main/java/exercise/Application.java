package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import exercise.component.UserProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import exercise.model.User;

@SpringBootApplication
@RestController
@Component
@ConfigurationProperties(prefix = "users.admins")
@Setter
@Getter
public class Application {

    // Все пользователи
    private List<User> users = Data.getUsers();

    // BEGIN

    @Autowired
    private UserProperties properties;

    @GetMapping("/admins")
    public List<String> showAdmins() {
        var admins = properties.getAdmins();
        return users.stream()
                .filter(p -> admins.contains(p.getEmail()))
                .map(p -> p.getName()).sorted()
                .toList();
    }
    
    // END

    @GetMapping("/users")
    public List<User> index() {
        return users;
    }

    @GetMapping("/users/{id}")
    public Optional<User> show(@PathVariable Long id) {
        var user = users.stream()
            .filter(u -> u.getId() == id)
            .findFirst();
        return user;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
