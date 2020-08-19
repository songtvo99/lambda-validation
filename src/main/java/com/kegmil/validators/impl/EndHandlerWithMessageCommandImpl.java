package com.kegmil.validators.impl;

import com.kegmil.validators.EndHandlerWithMessageCommand;
import com.kegmil.validators.ValidatorRuleProcessorBuilder;

public class EndHandlerWithMessageCommandImpl<T, TValue>
        implements EndHandlerWithMessageCommand<T, TValue> {

    ValidatorRuleProcessorBuilder<T, TValue> containerBuilder;

    public EndHandlerWithMessageCommandImpl(ValidatorRuleProcessorBuilder<T, TValue> containerBuilder) {
        this.containerBuilder = containerBuilder;
    }

    @Override
    public <TAnyValue> ValidatorRuleProcessorBuilder<T, TAnyValue> next(Class<TAnyValue> anyValueClass) {
        ValidatorRuleProcessorBuilder<T, TAnyValue> result =
                new ValidatorRuleProcessorBuilderImpl<>(containerBuilder.getValidatorContext());
        containerBuilder.addChild(result);
        return result;
    }

}
