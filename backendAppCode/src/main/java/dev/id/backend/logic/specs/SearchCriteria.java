package dev.id.backend.logic.specs;

import lombok.Data;

@Data
public class SearchCriteria {
    private String key;
    private Object value;
    private SearchOperation operation;

    public SearchCriteria(String key, SearchOperation searchOperation, Object value) {
        this.key = key;
        this.value = value;
        this.operation = searchOperation;
    }

}
