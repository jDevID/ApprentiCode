package backend.specification;

import backend.filter.SearchCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {
    private final List<SearchCriteria> list;

    public GenericSpecification(List<SearchCriteria> criteriaList) {
        this.list = criteriaList != null ? criteriaList : new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    @Nullable
    public Predicate toPredicate(@NonNull Root<T> root, @NonNull CriteriaQuery<?> query, @NonNull CriteriaBuilder builder) {

        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {
            switch (criteria.getOperation()) {
                case EQUALITY:
                    predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case NEGATION:
                    predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                    break;
                case GREATER_THAN:
                    predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LESS_THAN:
                    predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case LIKE:
                    predicates.add(builder.like(root.get(criteria.getKey()), criteria.getValue().toString()));
                    break;
                case STARTS_WITH:
                    predicates.add(builder.like(root.get(criteria.getKey()), criteria.getValue() + "%"));
                    break;
                case ENDS_WITH:
                    predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue()));
                    break;
                case CONTAINS:
                    predicates.add(builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%"));
                    break;
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }


}
