package com.sakura.dev.repository.specification;

import lombok.Data;

/**
 * Created by rc452 on 2017/4/27.
 */
@Data
public class SearchCriteria {
    public SearchCriteria(){

    }
    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    private String key;
    private SearchOperation operation;
    private Object value;
}