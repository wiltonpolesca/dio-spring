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

import dio.spring.diospring.model.Manager;
import dio.spring.diospring.repository.ManagerRepository;

@RestController
@RequestMapping("manager")
public class ManagerController {

    @Autowired
    ManagerRepository repo;

    @GetMapping
    // @PreAuthorize("hasAnyRole('ADMIN')")
    List<Manager> getAll() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    Manager getById(@PathVariable("id") Integer id) {
        return repo.findById(id);
    }
    
    @DeleteMapping("/{id}")
    void delete(@PathVariable("id") Integer id) {
        repo.deleteById(id);
    }

    @PostMapping
    void post(@RequestBody Manager manager) {
        repo.save(manager);
    }
}
