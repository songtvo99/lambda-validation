package com.kegmil.validators;

import com.kegmil.fluentvalidator.ErrorMessage;
import java.util.List;

public interface ValidatorResult {
    boolean isValid();
    List<ErrorMessage> getErrorMessages();
}
