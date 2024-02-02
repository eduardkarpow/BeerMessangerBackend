package dev.eduard.beermessanger.services;

import dev.eduard.beermessanger.WebSockets.Publishers.MessageChangeNotifier;
import dev.eduard.beermessanger.models.Chat;
import dev.eduard.beermessanger.models.Message;
import dev.eduard.beermessanger.models.MessageInput;
import dev.eduard.beermessanger.models.PhonesInput;
import dev.eduard.beermessanger.repositories.ChatRepository;
import dev.eduard.beermessanger.repositories.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ChatRepository chatRepository;
    private final MessageChangeNotifier messageChangeNotifier;

    public MessageService(MessageRepository messageRepository, ChatRepository chatRepository, MessageChangeNotifier messageChangeNotifier) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.messageChangeNotifier = messageChangeNotifier;
    }
    public Message addMessage(MessageInput messageInput){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Message message = new Message(messageInput.text(), messageInput.attach(),
                messageInput.chat(), LocalDateTime.parse(messageInput.datetime(), formatter), messageInput.isSent());

        Message saved = messageRepository.save(message);
        messageChangeNotifier.notifyChange(saved);
        return saved;
    }
    public List<Message> checkMessages(PhonesInput phonesInput) {

        Chat chat = chatRepository.findById(phonesInput.chat()).get();
        if(phonesInput.isSender()){
            chat.getMessages().stream().map(message -> {
                if(message.getIsSent()){
                    message.setChecked(true);
                    messageRepository.save(message);
                }
                return message;
            });
        } else {
            chat.getMessages().stream().map(message -> {
                if(!message.getIsSent()){
                    message.setChecked(true);
                    messageRepository.save(message);
                }
                return message;
            });
        }
        chatRepository.save(chat);
        return chat.getMessages().stream().toList();
    }
}
