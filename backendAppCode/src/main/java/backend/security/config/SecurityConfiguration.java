package backend.security.config;


import backend.security.config.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration  {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfiguration (JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }



    // https://stackoverflow.com/questions/74753700/cannot-resolve-method-antmatchers-in-authorizationmanagerrequestmatcherregis
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/projects/").authenticated()
                                .anyRequest().permitAll()
                        //.requestMatchers("/vertretungsplan").hasAnyRole("SCHUELER", "LEHRER", "VERWALTUNG")
                )
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);



        return http.build();
//        http.csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(jwtAuthEntryPoint)
//                .and()
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(req -> req.antMatchers("/api/auth/**")).permitAll()
//                        .requestMatchers(req -> req.antMatchers(HttpMethod.GET, "/api/v1/roles/**")).hasAuthority("READ_ROLE")
//                        .requestMatchers(req -> req.antMatchers(HttpMethod.POST, "/api/v1/roles")).hasAuthority("CREATE_ROLE")
//                        .requestMatchers(req -> req.antMatchers(HttpMethod.PUT, "/api/v1/roles/**")).hasAuthority("UPDATE_ROLE")
//                        .requestMatchers(req -> req.antMatchers(HttpMethod.DELETE, "/api/v1/roles/**")).hasAuthority("DELETE_ROLE")
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
