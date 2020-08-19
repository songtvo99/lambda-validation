package com.kegmil.validators;

import com.kegmil.fluentvalidator.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class ValidatorResultImpl implements ValidatorResult {

    private final List<ErrorMessage> errorMessages;

    public ValidatorResultImpl() {
        errorMessages = new ArrayList<>();
    }

    public ValidatorResultImpl(List<ErrorMessage> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public boolean isValid() {
        return errorMessages == null || errorMessages.size() == 0;
    }

    public List<ErrorMessage> getErrorMessages() {
        if (errorMessages == null) {
            return new ArrayList<>();
        }
        return errorMessages;
    }
}
