package backend.filter;

import backend.util.SearchOperation;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteriaBuilder {
    private final List<SearchCriteria> criteriaList = new ArrayList<>();

    public SearchCriteriaBuilder add(String key, SearchOperation operation, Object value) {
        criteriaList.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public List<SearchCriteria> build() {
        return criteriaList;
    }
}