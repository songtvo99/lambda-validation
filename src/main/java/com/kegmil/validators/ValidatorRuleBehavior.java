package com.kegmil.validators;

import java.util.function.Function;

public interface ValidatorRuleBehavior<T, TValue> {
    Function<TValue, ?> getActionWhenNotValid();
    TValue getContextValue(T data);
    Function<TValue, Boolean> getCondition();
}
