package dev.id.backend.logic.security.utils;

import dev.id.backend.logic.security.models.requests.ContextRequest;
import org.springframework.stereotype.Component;


@Component
public class ContextRequestUtil {
    private final ThreadLocal<ContextRequest> requestContextThreadLocal = new ThreadLocal<>();

    public ContextRequest getRequestContext() {
        ContextRequest requestContext = requestContextThreadLocal.get();
        if (requestContext == null) {
            requestContext = new ContextRequest();
            requestContextThreadLocal.set(requestContext);
        }
        return requestContext;
    }

    public void setUserId(String userId) {
        getRequestContext().setUserId(userId);
    }

    public String getUserId() {
        return getRequestContext().getUserId();
    }

    public void clear() {
        requestContextThreadLocal.remove();
    }
}
