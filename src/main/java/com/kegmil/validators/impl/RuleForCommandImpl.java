package com.kegmil.validators.impl;

import com.kegmil.validators.RuleHandler;
import com.kegmil.validators.ValidatorRuleProcessorBuilder;
import com.kegmil.validators.WithConditionHandler;
import java.util.function.Function;

public class RuleForCommandImpl<T, TValue> implements RuleHandler<T, TValue> {

    private final ValidatorRuleProcessorBuilder<T, TValue> containerBuilder;

    public RuleForCommandImpl(ValidatorRuleProcessorBuilder<T, TValue> containerBuilder) {
        this.containerBuilder = containerBuilder;
    }

    @Override
    public WithConditionHandler<T, TValue> must(Function<TValue, Boolean> condition) {
        return containerBuilder.must(condition);
    }

}
