package security.configs.jwt;

import security.utils.ContextRequestUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService; // this to check if user in DB inside internalFilter
    private final ContextRequestUtil requestContextUtil;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        // First Action to JWT Auth is checking if JWToken, included inside HTTP Header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        // Auth Token always starts by 'Bearer '
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        // extract Token from index 7 after 'Bearer '
        jwt = authHeader.substring(7);
        // need a JwtService to get email
        userEmail = jwtService.extractUsername(jwt);

        // once we have our JwtServices (extract, decode, claims, token infos)..
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { // && check if not already authenticated
            // check if user is in DB, instantiate userDetailsService here !! we want our own implementation
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
            // once ApplicationConfig is added we can retrieve this
            if (jwtService.isTokenValid(jwt, userDetails)) { // authToken needed to update Secu Context
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken( // Creating a user
                        userDetails,
                        null, // no credentials when creating user
                        userDetails.getAuthorities()
                );
                authToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request) // WebAuthenticationDetailsSource not existing in Spring 3.0.2
                );
                // FINAL STEP: update Security Context Holder
                SecurityContextHolder.getContext().setAuthentication(authToken);
                requestContextUtil.setUserId(userDetails.getUsername());
            }
        } // always calling filterChain.doFilter(), pass to next filter to be executed
        filterChain.doFilter(request, response);
    }

}
