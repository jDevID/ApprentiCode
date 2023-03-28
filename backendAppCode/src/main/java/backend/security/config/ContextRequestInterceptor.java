package backend.security.config;

import backend.security.util.ContextRequestUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ContextRequestInterceptor implements HandlerInterceptor {

    private final ContextRequestUtil requestContextUtil;

    public ContextRequestInterceptor(ContextRequestUtil requestContextUtil) {
        this.requestContextUtil = requestContextUtil;
    }

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
        requestContextUtil.clear();
    }

}
