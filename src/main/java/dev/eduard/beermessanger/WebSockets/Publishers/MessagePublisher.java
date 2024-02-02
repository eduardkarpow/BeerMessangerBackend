package dev.eduard.beermessanger.WebSockets.Publishers;

import dev.eduard.beermessanger.models.Message;
import dev.eduard.beermessanger.repositories.ChatRepository;
import dev.eduard.beermessanger.repositories.MessageRepository;
import graphql.kickstart.tools.GraphQLSubscriptionResolver;
import org.reactivestreams.Publisher;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;


import java.util.List;


@Controller
public class MessagePublisher implements GraphQLSubscriptionResolver {

    private final ChatRepository chatRepository;
    private final MessageChangeNotifier messageChangeNotifier;

    public MessagePublisher(ChatRepository chatRepository, MessageChangeNotifier messageChangeNotifier) {
        this.chatRepository = chatRepository;
        this.messageChangeNotifier = messageChangeNotifier;
    }


    @SubscriptionMapping
    public Publisher<Message> messages(){
        return Flux.create(fluxSink -> messageChangeNotifier.registerListener(fluxSink::next));
    }



}
