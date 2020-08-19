package com.kegmil.validators;

import java.util.function.Function;

public interface RuleHandler<T, TValue> {
    WithConditionHandler<T, TValue> must(Function<TValue, Boolean> condition);

}
