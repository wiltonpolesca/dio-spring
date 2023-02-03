package dio.spring.diospring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dio.spring.diospring.model.User;
import dio.spring.diospring.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder encoder;
    
    public void create (User user) {
        String pass = user.getPassword();
        
        //criptografa o password
        user.setPassword(encoder.encode(pass));
        repository.save(user);
    }
}
