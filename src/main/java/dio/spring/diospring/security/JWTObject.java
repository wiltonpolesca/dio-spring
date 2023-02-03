package dio.spring.diospring.security;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class JWTObject {
    private String subject;
    private Date issuedAt;
    private Date expiration;
    private List<String> roles;
}
