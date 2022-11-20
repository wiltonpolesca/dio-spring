package dio.spring.diospring.init;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import dio.spring.diospring.model.User;
import dio.spring.diospring.repository.UserRepository;

// classe para iniciar dados para uso interno
@Component
public class StartApplication implements CommandLineRunner{

    @Autowired
    private UserRepository repository;
    
    @Transactional
    @Override
    public void run(String... args) throws Exception {
        User user = repository.findByUsername("admin");
        if (user == null) {
            user = new User();
            user.setName("Admin");
            user.setUsername("admin");
            user.setPassword("admin123");
            user.getRoles().add("ADMIN");
            repository.save(user);
        }

        user = repository.findByUsername("user");
        if (user == null) {
            user = new User();
            user.setName("User");
            user.setUsername("user");
            user.setPassword("user123");
            user.getRoles().add("USERS");
            repository.save(user);
        }
    }
}
