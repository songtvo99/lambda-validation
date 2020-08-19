package com.kegmil.validators;

public interface EndHandlerWithActionCommand<T, TValue> {
    <TAnyValue> ValidatorRuleProcessorBuilder<T, TAnyValue> next(Class<TAnyValue> anyValueClass);
}
