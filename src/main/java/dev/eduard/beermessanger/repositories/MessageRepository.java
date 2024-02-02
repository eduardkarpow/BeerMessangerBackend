package dev.eduard.beermessanger.repositories;

import dev.eduard.beermessanger.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {
}
