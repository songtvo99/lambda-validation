package com.kegmil.validators.impl;

import com.kegmil.fluentvalidator.ErrorMessage;
import com.kegmil.fluentvalidator.ValidatorRule;
import com.kegmil.validators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ValidatorRuleProcessorBuilderImpl<T, TValue>
        implements ValidatorRuleProcessorBuilder<T, TValue> {

    private Predicate<T> preCondition;
    private Function<T, TValue> ruleFor;
    private Function<TValue, Boolean> mustClause;
    private Function<TValue, ?> actionWhenNotValid;
    private final ValidatorContext baseValidatorContext;

    private final List<ValidatorRuleProcessorBuilder<T, ?>> children = new ArrayList<>();

    public ValidatorRuleProcessorBuilderImpl(ValidatorContext validatorContext) {
        this.baseValidatorContext = validatorContext;
    }

    @Override
    public WhenCommand<T, TValue> when(Predicate<T> preCondition) {
        this.preCondition = preCondition;
        return new WhenCommandImpl<>(this);
    }

    @Override
    public RuleHandler<T, TValue> ruleFor(Function<T, TValue> function) {
        this.ruleFor = function;
        return new RuleForCommandImpl<>(this);
    }

    @Override
    public WithConditionHandler<T, TValue> must(Function<TValue, Boolean> mustClause) {
        this.mustClause = mustClause;
        return new WithConditionCommandImpl<>(this);
    }

    @Override
    public EndHandlerWithMessageCommand<T, TValue> withMessage(String message) {
        Function<TValue, ErrorMessage> displayErrorMessage = value ->
                new ErrorMessage(message,"", value);
        this.actionWhenNotValid = displayErrorMessage;
        return new EndHandlerWithMessageCommandImpl<>(this);
    }

    @Override
    public EndHandlerWithActionCommand<T, TValue> withAction(Consumer<TValue> consumer) {
        Function<TValue, ErrorMessage> actionWhenError = value -> {
            consumer.accept(value);
            return new ErrorMessage("", "" , value);
        } ;
        this.actionWhenNotValid = actionWhenError;
        return new EndHandlerWithActionCommandImpl<>(this);
    }

    @Override
    public EndHandlerWithThrowMessageCommand<T, TValue> withThrowException(Function<TValue, Exception> exceptionFunction) {
        this.actionWhenNotValid = exceptionFunction;
        return new EndHandlerWithThrowMessageImpl<>(this);
    }

    public void validate(T data) {
        if (preCondition == null) {
            applyValidatorRule(data);
        } else if ( preCondition.test(data)) {
            applyValidatorRule(data);
        }
    }

    private void applyValidatorRule(T data) {
        ValidatorRuleBehavior<T, TValue> validatorRuleBehavior =
                new BaseValidatorRuleBehavior<>(ruleFor, mustClause, actionWhenNotValid);
        ValidatorRule<ValidatorRuleBehavior<T, TValue>, T> rule = new BaseValidatorRuleImpl<>(baseValidatorContext);
        boolean isNotValid = rule.doCondition(validatorRuleBehavior, data);

        if (isNotValid) {
            rule.doAction(validatorRuleBehavior, data);
        } else {
            if (!children.isEmpty()) {
                children.forEach(child -> child.validate(data));
            }
        }
    }

    @Override
    public ValidatorContext getValidatorContext() {
        return baseValidatorContext;
    }

    @Override
    public <TAny> void addChild(ValidatorRuleProcessorBuilder<T, TAny> builder) {
        children.add(builder);
    }

    @Override
    public List<ValidatorRuleProcessorBuilder<T, ?>> getChildren() {
        return children;
    }
}
