package dio.spring.diospring.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import dio.spring.diospring.handler.BusinessException;
import dio.spring.diospring.model.Manager;
import dio.spring.diospring.model.User;

@Repository
public class ManagerRepository {
    public void save (Manager manager) {
        
        if (manager.getName() == null) {
            throw new BusinessException("Login is required.");
        }
        
        if (manager.getId() == null) {
            System.out.println("Saving new manager [repository]");
        } else {
            System.out.println("Saving new manager [repository]");
        }
        
        System.out.println(manager);
    }
    
    public void deleteById(int id) {
        System.out.println(String.format("Deleting manager id: %d [repository]", id));
        System.out.println(id);
    }
    
    public List<Manager> findAll() {
        System.out.println("Listing all managers [repository]");
        List<Manager> list = new ArrayList<>();
        list.add(new Manager("manager 1"));
        list.add(new Manager("manager 2"));
        return list;
    }
    
    public Manager findById(Integer id) {
        System.out.println(String.format("Finding by ID %d [repository]", id));
        return new Manager("Manager 1");
    }

    public Manager findByname(String name) {
        System.out.println(String.format("Finding by Manager by Name %s [repository]", name));
        return new Manager("manager bla");
    }

}
