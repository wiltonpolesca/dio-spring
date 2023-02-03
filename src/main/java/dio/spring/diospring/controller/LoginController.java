package dio.spring.diospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dio.spring.diospring.dtos.Login;
import dio.spring.diospring.dtos.Session;
import dio.spring.diospring.service.LoginService;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    
    @PostMapping
    Session post(@RequestBody Login login) {
        return loginService.login(login);
    }
}
