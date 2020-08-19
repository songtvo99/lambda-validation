package com.kegmil.validators;

import java.util.function.Function;

public class BaseValidatorRuleBehavior<T, TValue> implements ValidatorRuleBehavior<T, TValue> {

    private final Function<T, TValue> ruleFor;
    private final Function<TValue, Boolean> mustClause;
    private final Function<TValue, ?> actionWhenNotValid;

    public BaseValidatorRuleBehavior(
            Function<T, TValue> ruleFor,
            Function<TValue, Boolean> mustClause,
            Function<TValue, ?> actionWhenNotValid
    ) {
        this.actionWhenNotValid = actionWhenNotValid;
        this.ruleFor = ruleFor;
        this.mustClause = mustClause;
    }

    public TValue getContextValue(T data) {
        return this.ruleFor.apply(data);
    }

    @Override
    public Function<TValue, Boolean> getCondition() {
        return this.mustClause;
    }

    @Override
    public Function<TValue, ?> getActionWhenNotValid() {
        return actionWhenNotValid;
    }
}
