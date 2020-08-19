package com.kegmil.validators.impl;

import com.kegmil.validators.*;

import java.util.function.Consumer;
import java.util.function.Function;

public class WhenCommandImpl<T, TValue> implements WhenCommand<T, TValue> {

    private final ValidatorRuleProcessorBuilder<T, TValue> containerBuilder;

    public WhenCommandImpl(ValidatorRuleProcessorBuilder<T, TValue> containerBuilder) {
        this.containerBuilder = containerBuilder;
    }

    @Override
    public EndHandlerWithMessageCommand<T, TValue> withMessage(String message) {
        return containerBuilder.withMessage(message);
    }

    @Override
    public EndHandlerWithThrowMessageCommand<T, TValue> withThrow(Function<TValue, Exception> throwExceptionWhenError) {
        return containerBuilder.withThrowException(throwExceptionWhenError);
    }

    @Override
    public EndHandlerWithActionCommand<T, TValue> withAction(Consumer<TValue> actionWhenError) {
        return containerBuilder.withAction(actionWhenError);
    }
}
