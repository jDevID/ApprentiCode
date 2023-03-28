package criteria;

import backend.util.SearchOperation;

import java.util.ArrayList;
import java.util.List;

public class SearchCriteriaBuilder {
    private final List<criteria.SearchCriteria> criteriaList = new ArrayList<>();

    public SearchCriteriaBuilder add(String key, SearchOperation operation, Object value) {
        criteriaList.add(new criteria.SearchCriteria(key, operation, value));
        return this;
    }

    public List<criteria.SearchCriteria> build() {
        return criteriaList;
    }
}