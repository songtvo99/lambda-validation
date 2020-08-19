package com.kegmil.validators;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public interface WithConditionHandler<T, TValue> {
    WhenCommand<T, TValue> when(Predicate<T> predicate);
    EndHandlerWithMessageCommand<T, TValue> withMessage(String message);
    EndHandlerWithThrowMessageCommand<T, TValue> withThrow(Function<TValue, Exception> throwExceptionWhenError);
    EndHandlerWithActionCommand<T, TValue> withAction(Consumer<TValue> actionWhenError);
}
