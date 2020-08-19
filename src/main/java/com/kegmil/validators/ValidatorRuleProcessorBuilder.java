package com.kegmil.validators;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ValidatorRuleProcessorBuilder<T, TValue> {
    ValidatorContext getValidatorContext();
    WhenCommand<T, TValue> when(Predicate<T> preCondition);
    RuleHandler<T, TValue> ruleFor(Function<T, TValue> function);
    WithConditionHandler<T, TValue> must(Function<TValue, Boolean> predicateCondition);
    EndHandlerWithMessageCommand<T, TValue> withMessage(String message);
    EndHandlerWithActionCommand<T, TValue> withAction(Consumer<TValue> consumer);
    EndHandlerWithThrowMessageCommand<T, TValue> withThrowException(Function<TValue, Exception> exceptionSupplier);
    <TAny> void addChild(ValidatorRuleProcessorBuilder<T, TAny> builder);
    List<ValidatorRuleProcessorBuilder<T, ?>> getChildren();
    void validate(T data);

}
