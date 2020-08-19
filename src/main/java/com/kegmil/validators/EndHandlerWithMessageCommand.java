package com.kegmil.validators;

public interface EndHandlerWithMessageCommand<T, TValue> {
    <TAnyValue> ValidatorRuleProcessorBuilder<T, TAnyValue> next(Class<TAnyValue> anyValueClass);
}
