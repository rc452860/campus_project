package com.sakura.dev.repository.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

/**
 * Created by rc452 on 2017/4/27.
 */
public class GenericSpec<T> implements Specification<T>{
    private SearchCriteria criteria;

    public GenericSpec(SearchCriteria criteria) {
        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        switch (criteria.getOperation()) {
            case EQUALITY:
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case NEGATION:
                return builder.notEqual(root.get(criteria.getKey()), criteria.getValue());
            case GREATER_THAN:
                if (root.get(criteria.getKey()).getJavaType().equals(Date.class)){
                    return builder.greaterThan(root.<Date>get(criteria.getKey()), (Date)criteria.getValue());
                }else{
                    return builder.greaterThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
                }
            case GREATER_THAN_OR_EQUAL_TO:
                if (root.get(criteria.getKey()).getJavaType().equals(Date.class)){
                    return builder.greaterThanOrEqualTo(root.<Date>get(criteria.getKey()), (Date)criteria.getValue());
                }else{
                    return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
                }
            case LESS_THAN:
                if (root.get(criteria.getKey()).getJavaType().equals(Date.class)){
                    return builder.lessThan(root.<Date>get(criteria.getKey()), (Date)criteria.getValue());
                }else{
                    return builder.lessThan(root.<String>get(criteria.getKey()), criteria.getValue().toString());
                }
            case LESS_THAN__OR_EQUAL_TO:
                if (root.get(criteria.getKey()).getJavaType().equals(Date.class)){
                    return builder.lessThanOrEqualTo(root.<Date>get(criteria.getKey()), (Date)criteria.getValue());
                }else{
                    return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
                }
            case LIKE:
                return builder.like(root.<String>get(
                        criteria.getKey()), criteria.getValue().toString());
            case STARTS_WITH:
                return builder.like(root.<String>get(criteria.getKey()), criteria.getValue() + "%");
            case ENDS_WITH:
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue());
            case CONTAINS:
                return builder.like(root.<String>get(
                        criteria.getKey()), "%" + criteria.getValue() + "%");
            default:
                return null;
        }
    }
}
