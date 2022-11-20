package dio.spring.diospring.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    // @Override
    // protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Cria usuário em memória para teste
        // auth.inMemoryAuthentication()
        // .withUser("user")
        // .password("{noop}user123")
        // .roles("USERS")
        // .and()
        // .withUser("admin")
        // .password("{noop}admin123") //{noop} necessário para criptografia
        // .roles("ADMIN");
        
        /*
         * Tipos de criptografia:
         *   {bcrypt} for BCryptPasswordEncoder (mais comum)
         *   {noop} for NoOpPasswordEncoder
         *   {pbkdf2} for Pbkdf2PasswordEncoder
         *   {scrypt} for ScryptPasswordEncoder
         *   {sha256} for StandardPasswordEncoder
         */
    // }
    
    @Autowired
    private SecurityDatabaseService securityService;
    
    @Autowired
    public void globalSecurityDetails(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/").permitAll()
          .antMatchers("/login").permitAll()
          .antMatchers("/manager").hasAnyRole("ADMIN")
          .antMatchers("/user").hasAnyRole("ADMIN", "USERS")
        //   .anyRequest().authenticated().and().formLogin(); // deixa de usar a tela de login do spring
          .anyRequest().authenticated().and().httpBasic();
    }
}
