package dev.eduard.beermessanger.controllers;

import dev.eduard.beermessanger.models.Sender;
import dev.eduard.beermessanger.models.SenderInput;
import dev.eduard.beermessanger.services.SenderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SenderController {

    private final SenderService senderService;

    public SenderController(SenderService senderService) {
        this.senderService = senderService;
    }

    @MutationMapping
    Sender addSenderHandler(@Argument SenderInput senderInput){
        return senderService.addSender(senderInput);
    }
}
