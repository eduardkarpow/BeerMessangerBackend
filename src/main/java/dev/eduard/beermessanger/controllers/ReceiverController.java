package dev.eduard.beermessanger.controllers;

import dev.eduard.beermessanger.models.Receiver;
import dev.eduard.beermessanger.models.ReceiverInput;
import dev.eduard.beermessanger.services.ReceiverService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReceiverController {
    private final ReceiverService receiverService;

    public ReceiverController(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    @MutationMapping
    Receiver addReceiverHandler(@Argument ReceiverInput receiverInput){
        return receiverService.addReceiver(receiverInput);
    }
}
