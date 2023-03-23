package dev.id.backend.logic.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

//TODO: security

@Component
public class RequestContextUtil {
    public static final String USER_ID_HEADER = "X-User-Id";

    public Long getUserId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        String userIdHeader = request.getHeader(USER_ID_HEADER);
        if (userIdHeader == null) {
            throw new RuntimeException("Missing User ID header");
        }
        return Long.parseLong(userIdHeader);
    }
}