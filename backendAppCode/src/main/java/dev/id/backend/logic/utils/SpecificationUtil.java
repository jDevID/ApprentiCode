package dev.id.backend.logic.utils;

import dev.id.backend.logic.specs.GenericSpecification;
import dev.id.backend.logic.specs.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class SpecificationUtil {
    public static <T> Specification<T> createSpecificationFromCriteria(List<SearchCriteria> searchCriteria) {
        return new GenericSpecification<>(searchCriteria);
    }
}