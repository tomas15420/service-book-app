package cz.uhk.fim.servicebookapp.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurity {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers(HttpMethod.GET,"/", "/login", "/register").permitAll();
            auth.anyRequest().authenticated();
        }).formLogin(login -> {
            login.loginPage("/login");
            login.defaultSuccessUrl("/");
        }).logout(logout -> {
            logout.logoutUrl("/logout");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/");
            logout.deleteCookies("JSESSIONID");
        });

        return http.build();
    }
}
