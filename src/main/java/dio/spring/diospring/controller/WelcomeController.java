package dio.spring.diospring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {
    
    @GetMapping
    String welcome() {
        return "Welcome, your rest API is working";
    }
}
