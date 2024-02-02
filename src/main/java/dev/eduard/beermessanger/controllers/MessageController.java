package dev.eduard.beermessanger.controllers;

import dev.eduard.beermessanger.models.Message;
import dev.eduard.beermessanger.models.MessageInput;
import dev.eduard.beermessanger.models.PhonesInput;
import dev.eduard.beermessanger.services.MessageService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MessageController {
    private final MessageService messageService;


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MutationMapping
    Message addMessageHandler(@Argument MessageInput messageInput){
        return messageService.addMessage(messageInput);
    }
    @MutationMapping
    List<Message> checkMessagesHandler(@Argument PhonesInput phonesInput){
        return messageService.checkMessages(phonesInput);
    }
}
