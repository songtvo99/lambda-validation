package com.kegmil.fluentvalidator;

public abstract class AbstractProcessor implements Processor {
    private final Processor processor;

    public AbstractProcessor(Processor processor) {
        this.processor = processor;
    }

    @Override
    public <TContext> void process(TContext context) {
        if (processor != null) {
            processor.process(context);
        }
    }
}
