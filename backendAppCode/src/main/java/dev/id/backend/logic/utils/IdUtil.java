package dev.id.backend.logic.utils;

public interface IdUtil {
    public interface IdGetter<T, ID> {
        ID getId(T entity);
    }

    public interface IdSetter<T, ID> {
        void setId(T entity, ID id);
    }


}


