package com.kegmil.validators.impl;

import com.kegmil.validators.EndHandlerWithActionCommand;
import com.kegmil.validators.ValidatorRuleProcessorBuilder;

public class EndHandlerWithActionCommandImpl<T, TValue> implements EndHandlerWithActionCommand<T, TValue> {

    private final ValidatorRuleProcessorBuilder<T, TValue> containerBuilder;

    public EndHandlerWithActionCommandImpl(ValidatorRuleProcessorBuilder<T, TValue> containerBuilder) {
        this.containerBuilder = containerBuilder;
    }

    @Override
    public <TAnyValue> ValidatorRuleProcessorBuilder<T, TAnyValue> next(Class<TAnyValue> tAnyValueClass) {
        ValidatorRuleProcessorBuilder<T, TAnyValue> result =
                new ValidatorRuleProcessorBuilderImpl<>(containerBuilder.getValidatorContext());
        containerBuilder.addChild(result);
        return result;
    }
}
