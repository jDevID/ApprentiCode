package dev.id.backend.logic.utils;


import dev.id.backend.logic.specs.SearchCriteria;
import dev.id.backend.logic.specs.SearchOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SearchCriteriaParser {
    private static final String CRITERIA_PATTERN = "([a-zA-Z0-9_]+?)([:!><~])([a-zA-Z0-9_\\-%]+?);";

    public static List<SearchCriteria> parse(String searchFilter) {
        List<SearchCriteria> criteriaList = new ArrayList<>();

        if (searchFilter == null || searchFilter.isEmpty()) {
            return criteriaList;
        }

        Pattern pattern = Pattern.compile(CRITERIA_PATTERN);
        Matcher matcher = pattern.matcher(searchFilter);

        while (matcher.find()) {
            String key = matcher.group(1);
            String operation = matcher.group(2);
            String value = matcher.group(3);

            SearchOperation searchOperation = SearchOperation.getSimpleOperation(operation.charAt(0));

            if (searchOperation != null) {
                criteriaList.add(new SearchCriteria(key, searchOperation, value));
            }
        }

        return criteriaList;
    }
}
