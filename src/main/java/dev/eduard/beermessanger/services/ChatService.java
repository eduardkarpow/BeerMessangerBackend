package dev.eduard.beermessanger.services;

import dev.eduard.beermessanger.models.*;
import dev.eduard.beermessanger.repositories.*;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChatService {

    private final ChatRepository chatRepository;
    private final ReceiverRepository receiverRepository;
    private final SenderRepository senderRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;



    public ChatService(ChatRepository chatRepository, ReceiverRepository receiverRepository, SenderRepository senderRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.chatRepository = chatRepository;
        this.receiverRepository = receiverRepository;
        this.senderRepository = senderRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }
    public List<UserExtended> getChats(String phone){
        Sender sender = new Sender();
        sender.getUser().setPhone(phone);
        Example<Sender> ex = Example.of(sender);
        Optional<Sender> senderFromDB = senderRepository.findOne(ex);
        Integer senderId = senderFromDB.stream().toList().get(0).getId();
        System.out.println(senderId);

        Receiver receiver = new Receiver();
        receiver.getUser().setPhone(phone);
        Example<Receiver> exRec = Example.of(receiver);
        Optional<Receiver> receiverFromDB = receiverRepository.findOne(exRec);
        Integer receiverId = receiverFromDB.stream().toList().get(0).getId();
        System.out.println(receiverId);

        return chatRepository.findAll().stream().filter(chat -> {
            if(chat.getReceiver().getId() == receiverId || chat.getSender().getId() == senderId){
                return true;
            }
            return false;
        }).map(chat -> {
            if(chat.getReceiver().getId() == receiverId){
                if(chat.getMessages().stream().toList().size() != 0){
                    return new UserExtended(chat.getId(), senderRepository.getById(chat.getSender().getId()).getUser(),
                            chat.getMessages().stream().toList().get(chat.getMessages().stream().toList().size()-1).getText(),
                            chat.getMessages().stream().toList().get(chat.getMessages().stream().toList().size()-1).getDatetime().toString());
                }
                return new UserExtended(chat.getId(), senderRepository.getById(chat.getSender().getId()).getUser(),"", "");
            } else{
                if(chat.getMessages().stream().toList().size() != 0){
                    return new UserExtended(chat.getId(), receiverRepository.getById(chat.getReceiver().getId()).getUser(),
                            chat.getMessages().stream().toList().get(chat.getMessages().stream().toList().size()-1).getText(),
                            chat.getMessages().stream().toList().get(chat.getMessages().stream().toList().size()-1).getDatetime().toString());
                }
                return new UserExtended(chat.getId(), receiverRepository.getById(chat.getReceiver().getId()).getUser(),"", "");
            }
        }).toList();

    }

    public Chat addChat(ChatInput chatInput){
        Sender sender = new Sender();
        sender.getUser().setPhone(chatInput.sender());
        Example<Sender> ex = Example.of(sender);
        Optional<Sender> senderFromDB = senderRepository.findOne(ex);
        Integer senderId = senderFromDB.stream().toList().get(0).getId();

        Receiver receiver = new Receiver();
        receiver.getUser().setPhone(chatInput.receiver());
        Example<Receiver> exRec = Example.of(receiver);
        Optional<Receiver> receiverFromDB = receiverRepository.findOne(exRec);
        Integer receiverId = senderFromDB.stream().toList().get(0).getId();

        Chat chat = new Chat(senderId, receiverId);
        return chatRepository.save(chat);
    }
    public Chat getChat(Integer id){
        return chatRepository.findById(id).get();
    }
}
