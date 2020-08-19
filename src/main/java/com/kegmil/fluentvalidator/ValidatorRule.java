package com.kegmil.fluentvalidator;

public interface ValidatorRule<TBehavior, T> {
    boolean doCondition(TBehavior behavior, T data);
    void doAction(TBehavior behavior, T data);
}
