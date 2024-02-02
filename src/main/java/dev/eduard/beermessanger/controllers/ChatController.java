package dev.eduard.beermessanger.controllers;

import dev.eduard.beermessanger.models.Chat;
import dev.eduard.beermessanger.models.ChatInput;
import dev.eduard.beermessanger.models.User;
import dev.eduard.beermessanger.models.UserExtended;
import dev.eduard.beermessanger.services.ChatService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ChatController {

    private final ChatService chatService;


    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MutationMapping
    Chat addChatHandler(@Argument ChatInput chatInput){
        return chatService.addChat(chatInput);
    }
    @QueryMapping
    List<UserExtended> getChatsHandler(@Argument String phone){
        return chatService.getChats(phone);
    }
    @QueryMapping
    Chat getChatHandler(@Argument Integer id){
        return chatService.getChat(id);
    }
}
