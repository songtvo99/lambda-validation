package com.kegmil.fluentvalidator;

public interface RuleHandler {
    <TContext> void handle(TContext context);
}
