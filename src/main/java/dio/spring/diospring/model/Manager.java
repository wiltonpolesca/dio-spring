package dio.spring.diospring.model;

import lombok.Data;

@Data
public class Manager {
    private Integer id;
    private String name;
    
    public Manager(){ }

    public Manager(String name){
        this.name = name;
     }

    @Override
    public String toString() {
        return "Manager: { name: " + name + "}";
    }
}
