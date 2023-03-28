package backend.specification;


import backend.domain.dto.EntryDto;
import backend.domain.dto.TagDto;
import backend.domain.entity.Entry;
import backend.filter.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public final class EntrySpecification extends GenericSpecification<EntryDto> {

    public EntrySpecification(List<SearchCriteria> criteriaList) {
        super(criteriaList);
    }
    public static Specification<Entry> hasComplexity(String complexityName) {
        return (entry, cq, cb) -> cb.equal(entry.get("complexity").get("name"), complexityName);
    }

    public static Specification<Entry> hasTag(String tagName) {
        return (entry, cq, cb) -> {
            // Join Entry with Tags
            jakarta.persistence.criteria.Join<EntryDto, TagDto> tags = entry.join("tags");
            return cb.equal(tags.get("name"), tagName);
        };
    }

    public static Specification<Entry> hasCommandLike(String command) {
        return (entry, cq, cb) -> cb.like(entry.get("command"), "%" + command + "%");
    }

    public static Specification<Entry> isActive(Boolean active) {
        return (entry, cq, cb) -> cb.equal(entry.get("active"), active);
    }

    public static Specification<Entry> hasProject(Long projectId) {
        return (entry, cq, cb) -> cb.equal(entry.get("complexity").get("project").get("id"), projectId);
    }
}
