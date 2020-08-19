import com.kegmil.validators.AbstractValidator;

import java.util.Objects;

public class ClientRequestTicketComplexValidator extends AbstractValidator<ClientRequestTicket> {

    public ClientRequestTicketComplexValidator() {
        ruleFor(ClientRequestTicket::getId).must(Objects::nonNull).withMessage("Id of customer must be not null");

        ruleFor(ClientRequestTicket::getContact)
                .must(Objects::nonNull)
                .when(clientRequestTicket -> Objects.nonNull(clientRequestTicket))
                .withMessage("Contact of customer must be not null")
                    .next(Person.class)
                        .ruleFor(ClientRequestTicket::getContact)
                        .must(Objects::nonNull)
                        .withMessage(" The contact must have value ");
            ;

        ruleFor(ClientRequestTicket::getContact)
                .must(Objects::nonNull)
                .when(clientRequestTicket -> Objects.nonNull(clientRequestTicket))
                .withMessage(" the contact must be not null ")
                    .next(Double.class)
                        .ruleFor( customer -> customer.getContact().getSalary() )
                        .must(this::CustomValidate)
                        .withMessage(" Tah dah ");
        
    }

    private boolean CustomValidate(Double salary) {
        return salary != null
                && salary > 1000;
    }

}
