package dev.id.backend.logic.security.configs;
/*

 */

import dev.id.backend.logic.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration // loaded at boot
@RequiredArgsConstructor // to Inj Dependencies
public class ApplicationConfig {
    private final UserRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        // need Repo to get user in DB
        return username -> repository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + " not found"));
    }

    // now provide Bean: authenticator provider
    @Bean
    public AuthenticationProvider authenticationProvider() { // DATA ACCESS OBJECT (fetch details, pw ,..) many impl possible
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(); // need 2 prop
        authProvider.setUserDetailsService(userDetailsService()); //1
        authProvider.setPasswordEncoder(passwordEncoder());//2 pw encoder
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();

    }
}
