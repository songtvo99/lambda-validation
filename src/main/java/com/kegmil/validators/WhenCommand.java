package com.kegmil.validators;

import java.util.function.Consumer;
import java.util.function.Function;

public interface WhenCommand<T, TValue> {
    EndHandlerWithMessageCommand<T, TValue> withMessage(String message);
    EndHandlerWithThrowMessageCommand<T, TValue> withThrow(Function<TValue, Exception> throwExceptionWhenError);
    EndHandlerWithActionCommand<T, TValue> withAction(Consumer<TValue> actionWhenError);
}
