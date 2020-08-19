package com.kegmil.fluentvalidator;

public class ErrorMessage {

    private final String errorMessage;
    private final String fieldName;
    private final Object valueFail;

    public ErrorMessage(String errorMessage) {
        this(errorMessage, "", null);
    }

    public ErrorMessage(String errorMessage, String fieldName) {
        this(errorMessage, fieldName, null);
    }

    public ErrorMessage(String errorMessage, String fieldName, Object valueFail) {
        this.errorMessage = errorMessage;
        this.fieldName = fieldName;
        this.valueFail = valueFail;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getValueFail() {
        return valueFail;
    }
}
