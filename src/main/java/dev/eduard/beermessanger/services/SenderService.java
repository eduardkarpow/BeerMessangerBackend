package dev.eduard.beermessanger.services;

import dev.eduard.beermessanger.models.Sender;
import dev.eduard.beermessanger.models.SenderInput;
import dev.eduard.beermessanger.repositories.SenderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SenderService {
    private final SenderRepository senderRepository;


    public SenderService(SenderRepository senderRepository) {
        this.senderRepository = senderRepository;
    }

    public Sender addSender(SenderInput senderInput){
        Sender sender = new Sender(senderInput.phone());
        return senderRepository.save(sender);
    }
}
