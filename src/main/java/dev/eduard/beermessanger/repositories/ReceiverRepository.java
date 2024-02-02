package dev.eduard.beermessanger.repositories;

import dev.eduard.beermessanger.models.Receiver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiverRepository extends JpaRepository<Receiver, Integer> {
}
