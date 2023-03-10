package dio.spring.diospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.spring.diospring.model.User;
import dio.spring.diospring.service.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping
    // // @PreAuthorize("hasAnyRole('ADMIN', 'USERS')")
    void post(@RequestBody User user) {
        service.create(user);
    }
}
