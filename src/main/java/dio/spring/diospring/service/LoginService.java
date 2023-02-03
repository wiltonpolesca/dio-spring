package dio.spring.diospring.service;

import java.util.Date;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.Encoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dio.spring.diospring.configs.SecurityConfig;
import dio.spring.diospring.dtos.Login;
import dio.spring.diospring.dtos.Session;
import dio.spring.diospring.model.User;
import dio.spring.diospring.repository.UserRepository;
import dio.spring.diospring.security.JWTCreator;
import dio.spring.diospring.security.JWTObject;

@Service
public class LoginService {
    
    @Autowired
    private UserRepository repository;
    
    @Autowired
    private PasswordEncoder encoder;
    
    public Session login(Login credentials) {
        User user = repository.findByUsername(credentials.getUsername());
        
        if (user == null) {
            throw new RuntimeErrorException(null, "User or password invalid.");
        }
        
        if (!encoder.matches(credentials.getPassword(), user.getPassword())){
            throw new RuntimeErrorException(null, "User or password invalid.");
        }
        
        Session session = new Session();
        session.setLogin(user.getUsername());
        
        JWTObject jwtObject = new JWTObject();
        jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
        jwtObject.setExpiration(new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION));
        jwtObject.setRoles(user.getRoles());
        
        session.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
        
        return session;
    }
    
    
}
