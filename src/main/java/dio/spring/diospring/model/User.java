package dio.spring.diospring.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String username;
    
    @Column(length = 100, nullable = false)
    private String password;
    
    @ElementCollection(fetch= FetchType.EAGER)
    @CollectionTable(name="user_roles", joinColumns =  @JoinColumn(name="id"))
    @Column(name = "role_id")
    private List<String> roles = new ArrayList<>();
    
    public User(){ }

    public User(String username){
        this.username = username;
     }

    @Override
    public String toString() {
        return "User: { login: " + username + "}";
    }
}
