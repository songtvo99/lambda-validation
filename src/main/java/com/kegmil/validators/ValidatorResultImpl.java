package com.kegmil.validators;

import com.kegmil.fluentvalidator.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

public class ValidatorResultImpl implements ValidatorResult {

    private final List<ErrorMessage> errorMessages;

    public ValidatorResultImpl() {
        errorMessages = new ArrayList<>();
    }

    public boolean isValid() {
        return errorMessages.size() == 0;
    }

    public List<ErrorMessage> getErrorMessages() {
        return errorMessages;
    }
}
