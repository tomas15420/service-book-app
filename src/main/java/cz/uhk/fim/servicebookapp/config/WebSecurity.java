package cz.uhk.fim.servicebookapp.config;


import cz.uhk.fim.servicebookapp.repository.UserRepository;
import cz.uhk.fim.servicebookapp.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class WebSecurity {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/", "/login", "/register").permitAll();
            auth.anyRequest().authenticated();
        }).formLogin(login -> {
            login.loginPage("/login");
            login.loginProcessingUrl("/login");
            login.usernameParameter("username");
            login.passwordParameter("password");
            login.defaultSuccessUrl("/");
        }).logout(logout -> {
            logout.logoutUrl("/logout");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/");
            logout.deleteCookies("JSESSIONID");
        });

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
