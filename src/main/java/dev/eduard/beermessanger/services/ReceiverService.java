package dev.eduard.beermessanger.services;

import dev.eduard.beermessanger.models.Receiver;
import dev.eduard.beermessanger.models.ReceiverInput;
import dev.eduard.beermessanger.repositories.ReceiverRepository;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
    private final ReceiverRepository receiverRepository;

    public ReceiverService(ReceiverRepository receiverRepository) {
        this.receiverRepository = receiverRepository;
    }

    public Receiver addReceiver(ReceiverInput receiverInput){
        Receiver receiver = new Receiver(receiverInput.phone());
        return receiverRepository.save(receiver);
    }
}
