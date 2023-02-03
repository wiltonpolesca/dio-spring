package dio.spring.diospring.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.h2.server.web.WebServlet;

import dio.spring.diospring.security.JWTFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  // @Override
  // protected void configure(AuthenticationManagerBuilder auth) throws Exception
  // {
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
   * {bcrypt} for BCryptPasswordEncoder (mais comum)
   * {noop} for NoOpPasswordEncoder
   * {pbkdf2} for Pbkdf2PasswordEncoder
   * {scrypt} for ScryptPasswordEncoder
   * {sha256} for StandardPasswordEncoder
   */
  // }

  /** BASIC AUTH */
  // @Autowired
  // private SecurityDatabaseService securityService;

  // @Autowired
  // public void globalSecurityDetails(AuthenticationManagerBuilder auth) throws
  // Exception {
  // auth.userDetailsService(securityService).passwordEncoder(NoOpPasswordEncoder.getInstance());
  // }

  // @Override
  // protected void configure(HttpSecurity http) throws Exception {
  // http.authorizeRequests()
  // .antMatchers("/").permitAll()
  // .antMatchers("/login").permitAll()
  // .antMatchers("/manager").hasAnyRole("ADMIN")
  // .antMatchers("/users").hasAnyRole("ADMIN", "USERS")
  // // .anyRequest().authenticated().and().formLogin(); // deixa de usar a tela
  // de login do spring
  // .anyRequest().authenticated().and().httpBasic();

  /** BASIC AUTH END */

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  private static final String[] SWAGGER_WHITLIST = {
      "/V2/api-docs",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/swagger-ui.html",
      "/webjars/**"
  };

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.headers().frameOptions().disable();
    http.cors().and().csrf().disable()
        .addFilterAfter(new JWTFilter(), UsernamePasswordAuthenticationFilter.class)
        .authorizeRequests()
        .antMatchers(SWAGGER_WHITLIST).permitAll()
        .antMatchers("/h2-console/**").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .antMatchers(HttpMethod.POST, "/users").permitAll()
        .antMatchers("/manager").hasAnyRole("ADMIN")
        .antMatchers("/users").hasAnyRole("ADMIN", "USERS")
        .anyRequest().authenticated()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Bean // Enabing h2-database to be access by web
  public ServletRegistrationBean h2servletRegistration() {
    ServletRegistrationBean registrationBean = new ServletRegistrationBean (
      new WebServlet(), 
      "/h2-console/*");
    return registrationBean;
  }
}
