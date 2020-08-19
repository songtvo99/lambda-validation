package com.kegmil.validators;

import java.util.function.Function;

public interface EndHandlerWithThrowMessageCommand<T, TValue> {
    EndHandlerWithThrowMessageCommand<T, TValue> withThrow(Function<TValue, Exception> exceptionSupplier);
    <TAnyValue> ValidatorRuleProcessorBuilder<T, TAnyValue> next(Class<TAnyValue> anyValueClass);
}
