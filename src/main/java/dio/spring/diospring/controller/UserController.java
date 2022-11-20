package dio.spring.diospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.spring.diospring.model.User;
import dio.spring.diospring.repository.UserRepository;
import javassist.NotFoundException;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserRepository repo;

    @GetMapping
    // @PreAuthorize("hasAnyRole('ADMIN')")
    List<User> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ADMIN', 'USERS')")
    User getById(@PathVariable("id") Integer id) {
        return repo.getById(id);
    }

    @GetMapping("/by-user-name/{username}")
    // @PreAuthorize("hasAnyRole('ADMIN', 'USERS')")
    User getById(@PathVariable("username") String username) {
        return repo.findByUsername(username);
    }
    
    @DeleteMapping("/{id}")
    // @PreAuthorize("hasAnyRole('ADMIN', 'USERS')")
    void delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
    }

    @PostMapping
    // @PreAuthorize("hasAnyRole('ADMIN', 'USERS')")
    void post(@RequestBody User user) {
        repo.save(user);
    }

    // @PutMapping
    // void put(@RequestBody User user) {
    //     repo.save(user);
    // }

}
