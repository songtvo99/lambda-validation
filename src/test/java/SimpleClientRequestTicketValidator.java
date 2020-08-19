import com.kegmil.validators.AbstractValidator;

import java.util.Objects;

public class SimpleClientRequestTicketValidator extends AbstractValidator<ClientRequestTicket> {
    public SimpleClientRequestTicketValidator() {
        ruleFor(ClientRequestTicket::getContact)
                .must(Objects::nonNull)
                .withMessage(" The contact must be not null ");

        ruleFor(ClientRequestTicket::getId)
                .must(id -> id > 0)
                .when(clientRequestTicket -> Objects.nonNull(clientRequestTicket.getId()))
                .withMessage("The id value must be greater than 0 ");

    }
}

