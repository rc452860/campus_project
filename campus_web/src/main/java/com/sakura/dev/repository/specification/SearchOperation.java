package com.sakura.dev.repository.specification;

import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;

/**
 * Created by rc452 on 2017/4/27.
 */
public enum SearchOperation {
    EQUALITY, NEGATION, GREATER_THAN,GREATER_THAN_OR_EQUAL_TO, LESS_THAN,LESS_THAN__OR_EQUAL_TO, LIKE, STARTS_WITH, ENDS_WITH, CONTAINS,IN;

    public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">",">=", "<","<=", "~" };

    public static SearchOperation getSimpleOperation(String input) {
        for (int i = 0;i<SIMPLE_OPERATION_SET.length;i++){
            if (SIMPLE_OPERATION_SET[i].equals(input)){
                return SearchOperation.values()[i];
            }
        }
        throw new IndexOutOfBoundsException("not support operation！");
    }
}
