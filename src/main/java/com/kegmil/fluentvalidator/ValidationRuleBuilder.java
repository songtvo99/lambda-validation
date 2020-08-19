package com.kegmil.fluentvalidator;

import java.util.function.Function;

public interface ValidationRuleBuilder<T> {
    ValidationRuleBuilder<T> ruleFor(Function<T, ?> supplier);
    <TContext> ValidationRuleBuilder<T> when(Function<TContext, Boolean> condition);
    ValidationRuleBuilder<T> withMessage(String message);
}
