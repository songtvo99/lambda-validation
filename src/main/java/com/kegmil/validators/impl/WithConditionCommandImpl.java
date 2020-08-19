package com.kegmil.validators.impl;

import com.kegmil.validators.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class WithConditionCommandImpl<T, TValue> implements WithConditionHandler<T, TValue> {

    private final ValidatorRuleProcessorBuilder<T, TValue> builderContainer;

    public WithConditionCommandImpl(
            ValidatorRuleProcessorBuilder<T, TValue> builderContainer
    ) {
        this.builderContainer = builderContainer;
    }

    @Override
    public WhenCommand<T, TValue> when(Predicate<T> predicate) {
        return this.builderContainer.when(predicate);
    }

    @Override
    public EndHandlerWithMessageCommand<T, TValue> withMessage(String message) {
        return builderContainer.withMessage(message);
    }

    @Override
    public EndHandlerWithThrowMessageCommand<T, TValue> withThrow(Function<TValue, Exception> exception) {
        return builderContainer.withThrowException(exception);
    }

    @Override
    public EndHandlerWithActionCommand<T, TValue> withAction(Consumer<TValue> actionWhenError) {
        return builderContainer.withAction(actionWhenError);
    }
}
