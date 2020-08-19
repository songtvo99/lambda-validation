import com.kegmil.fluentvalidator.ErrorMessage;
import com.kegmil.fluentvalidator.ValidationRuleBuilder;
import com.kegmil.validators.*;
import com.kegmil.validators.impl.ValidatorRuleProcessorBuilderImpl;
import org.junit.Test;

import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestValidationRuleBuilder {

    @Test
    public void testValidationRuleBuilderFollowSerialized() {
        ValidationRuleBuilder<Person> builder = null;

    }

    @Test
    public void testValidationRuleImplement() {
        BaseValidatorContext baseValidatorContext = new BaseValidatorContext();

        ClientRequestTicket customerData = new ClientRequestTicket();

        BaseValidatorRuleImpl validator = new BaseValidatorRuleImpl(baseValidatorContext);
        BaseValidatorRuleBehavior<ClientRequestTicket, Integer> validatorRuleContext =
                new BaseValidatorRuleBehavior<>
                        (ClientRequestTicket::getId, Objects::nonNull,
                                value -> new ErrorMessage("Id", "Must not be null", value));

        if (validator.doCondition(validatorRuleContext, customerData)) {
            validator.doAction(validatorRuleContext, customerData);
        }

        ValidatorResult result = baseValidatorContext.getValidatorResult();

        assertFalse(result.isValid());

    }

    @Test
    public void testValidationBuilderFollowStepByStepMustBeSuccess() {

        BaseValidatorContext context = new BaseValidatorContext();

        ValidatorRuleProcessorBuilder<ClientRequestTicket, Integer> validatorRuleProcessorBuilder
                = new ValidatorRuleProcessorBuilderImpl<>(context);

        ClientRequestTicket customerEntity = new ClientRequestTicket();
        customerEntity.setContact(new Person());
        customerEntity.setId(10);

        validatorRuleProcessorBuilder
                .ruleFor(ClientRequestTicket::getId)
                .must( id -> id != null && id > 0 )
                .when(Objects::nonNull)
                .withMessage(" customer id must be not null and id is gt 0 ")
                .next(Person.class)
                    .ruleFor(ClientRequestTicket::getContact)
                    .must(Objects::nonNull)
                    .when(Objects::nonNull)
                    .withMessage(" The contact must be not null ")
                        .next( Person.class )
                        .ruleFor(ClientRequestTicket::getContact)
                        .must(contact ->
                                "MANAGER".equalsIgnoreCase(contact.getPosition())
                                        && contact.getSalary() > 10000
                        )
                        .withMessage(" Violate policy company so high salary ");

        validatorRuleProcessorBuilder.validate(customerEntity);

        System.out.println(
                context.getValidatorResult()
                        .getErrorMessages()
                        .stream()
                        .map(ErrorMessage::getErrorMessage)
                        .collect(Collectors.joining(",")) );

        assertFalse(context.getValidatorResult().isValid());

    }

    @Test
    public void testBuildValidatorRuleWithClass() {
        ClientRequestTicket clientRequestTicket = new ClientRequestTicket();
        ClientRequestTicketComplexValidator clientRequestTicketValidator = new ClientRequestTicketComplexValidator();
        ValidatorResult validatorResult = clientRequestTicketValidator.validate(clientRequestTicket);

        assertFalse(validatorResult.isValid());
    }

    @Test
    public void testWithSimpleValidatorMustBeHasErrorInValidatorResult() {
        ClientRequestTicket clientRequestTicket = new ClientRequestTicket();

        SimpleClientRequestTicketValidator simpleClientRequestTicketValidator
                = new SimpleClientRequestTicketValidator();

        ValidatorResult validatorResult = simpleClientRequestTicketValidator.validate(clientRequestTicket);

        assertFalse(validatorResult.isValid());
    }

    @Test
    public void testWithSimpleValidatorMustBeNoErrorInValidatorResult() {
        ClientRequestTicket clientRequestTicket = new ClientRequestTicket();
        clientRequestTicket.setContact(new Person());
        clientRequestTicket.setId(1);

        SimpleClientRequestTicketValidator simpleClientRequestTicketValidator
                = new SimpleClientRequestTicketValidator();

        ValidatorResult validatorResult = simpleClientRequestTicketValidator.validate(clientRequestTicket);

        assertTrue(validatorResult.isValid());
    }
}
