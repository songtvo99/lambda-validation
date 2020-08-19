package com.kegmil.validators.impl;

import com.kegmil.validators.EndHandlerWithThrowMessageCommand;
import com.kegmil.validators.ValidatorRuleProcessorBuilder;

import java.util.function.Function;

public class EndHandlerWithThrowMessageImpl<T, TValue> implements EndHandlerWithThrowMessageCommand<T, TValue> {

    private final ValidatorRuleProcessorBuilder<T, TValue> containerBuilder;

    public EndHandlerWithThrowMessageImpl(ValidatorRuleProcessorBuilder<T, TValue> containerBuilder) {
        this.containerBuilder = containerBuilder;
    }

    @Override
    public EndHandlerWithThrowMessageCommand<T, TValue> withThrow(Function<TValue, Exception> exceptionWhenNotValid) {
        return containerBuilder.withThrowException(exceptionWhenNotValid);
    }

    @Override
    public <TAnyValue> ValidatorRuleProcessorBuilder<T, TAnyValue> next(Class<TAnyValue> anyValueClass) {
        ValidatorRuleProcessorBuilder<T, TAnyValue> result =
                new ValidatorRuleProcessorBuilderImpl<>(containerBuilder.getValidatorContext());
        containerBuilder.addChild(result);
        return result;
    }
}
