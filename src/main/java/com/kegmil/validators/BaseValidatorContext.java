package com.kegmil.validators;

import com.kegmil.fluentvalidator.ErrorMessage;

public class BaseValidatorContext implements ValidatorContext {

    private final ValidatorResult result = new ValidatorResultImpl();

    @Override
    public void addErrorMessage(ErrorMessage errorMessage) {
        result.getErrorMessages().add(errorMessage);
    }

    @Override
    public ValidatorResult getValidatorResult() {
        return result;
    }
}
