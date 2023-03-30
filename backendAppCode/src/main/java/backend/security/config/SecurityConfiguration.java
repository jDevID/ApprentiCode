package backend.security.config;


import backend.security.config.jwt.JwtAuthenticationEntryPoint;
import backend.security.config.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration  {
    private final JwtAuthenticationFilter jwtAuthFilter; // final = automatically injected
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationEntryPoint jwtAuthEntryPoint;

    // SecurityFilterChain = configure all the Beans needed in app
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http  // what are URL and path you want to secure (=> need Whitelist, so no token asked)
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/roles/**").hasAuthority("READ_ROLE")
                .antMatchers(HttpMethod.POST, "/api/v1/roles").hasAuthority("CREATE_ROLE")
                .antMatchers(HttpMethod.PUT, "/api/v1/roles/**").hasAuthority("UPDATE_ROLE")
                .antMatchers(HttpMethod.DELETE, "/api/v1/roles/**").hasAuthority("DELETE_ROLE")
                .anyRequest() // all others
                .authenticated() // get authenticated
                .and() // Session should be stateless to insure each Request is authenticated
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // how we want to create them
                .and() // which authentication provider to choose
                .authenticationProvider(authenticationProvider) // now we want to use our JwtFilter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //we want our Filter to work before, update then call username auth filter
                // Now we need to provide this authenticator provider Bean
        return http.build();
    }
}