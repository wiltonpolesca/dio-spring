package dio.spring.diospring.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "security.config")
public class SecurityConfig {
    public static String PREFIX;
    public static String KEY;
    public static Long EXPIRATION;

    public void setPrefix(String value) {
        PREFIX = value;
    }

    public void setKey(String value) {
        KEY = value;
    }
    
    public void setExpiration(Long value) {
        EXPIRATION = value;
    }
}
