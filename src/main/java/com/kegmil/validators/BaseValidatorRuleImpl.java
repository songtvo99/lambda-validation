package com.kegmil.validators;

import com.kegmil.fluentvalidator.ErrorMessage;
import com.kegmil.fluentvalidator.ValidatorRule;

import java.util.function.Function;

public class BaseValidatorRuleImpl<T, TValue, TBehavior extends ValidatorRuleBehavior<T, TValue>>
        implements ValidatorRule<TBehavior, T> {

    private final ValidatorContext validatorContext;
    public BaseValidatorRuleImpl(ValidatorContext validatorContext) {
        this.validatorContext = validatorContext;
    }

    @Override
    public boolean doCondition(TBehavior context, T data) {
        TValue value = context.getContextValue(data);
        Function<TValue, Boolean> condition = context.getCondition();
        return !condition.apply(value);
    }

    @Override
    public void doAction(TBehavior context, T data) {
        Object result = context.getActionWhenNotValid().apply(context.getContextValue(data));

        if (result instanceof Exception) {
            throw new RuntimeException ((Exception)result);
        }

        if (result instanceof ErrorMessage) {
            validatorContext.addErrorMessage((ErrorMessage)result);
        } else {
            validatorContext.addErrorMessage(new ErrorMessage(String.valueOf(result)));
        }
    }
}
