package com.kegmil.validators;

import com.kegmil.fluentvalidator.ErrorMessage;

public interface ValidatorContext {
    void addErrorMessage(ErrorMessage errorMessage);
    ValidatorResult getValidatorResult();
}
