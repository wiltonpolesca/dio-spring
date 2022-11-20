package dio.spring.diospring.configs;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dio.spring.diospring.model.User;
import dio.spring.diospring.repository.UserRepository;

@Service
public class SecurityDatabaseService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        });

        UserDetails userDetail = new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                authorities);

        return userDetail;
    }
}
