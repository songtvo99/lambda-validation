package com.kegmil.fluentvalidator;

public interface Processor {
     <TContext> void process(TContext context);
}
