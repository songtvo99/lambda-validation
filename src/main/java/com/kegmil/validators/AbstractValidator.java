package com.kegmil.validators;

import com.kegmil.validators.impl.ValidatorRuleProcessorBuilderImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class AbstractValidator<T> {

    private final ValidatorContext validatorContext = new BaseValidatorContext();
    private final List<ValidatorRuleProcessorBuilder<T, ?>> validatorRuleProcessorBuilders = new ArrayList<>();
    protected <TValue> RuleHandler<T, TValue> ruleFor(Function<T, TValue> ruleForValue) {
        ValidatorRuleProcessorBuilder<T, TValue> builder = new ValidatorRuleProcessorBuilderImpl<>(validatorContext);
        validatorRuleProcessorBuilders.add(builder);
        return builder.ruleFor(ruleForValue);
    }

    public ValidatorResult validate(T data) {
        validatorRuleProcessorBuilders
                .forEach( processirBuilder -> processirBuilder.validate(data));
        return validatorContext.getValidatorResult();
    }

}
