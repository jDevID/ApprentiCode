package dev.id.backend.logic.utils;

public interface IdUtil {
    interface IdGetter<T, ID> {
        ID getId(T entity);
    }

    interface IdSetter<T, ID> {
        void setId(T entity, ID id);
    }

}
