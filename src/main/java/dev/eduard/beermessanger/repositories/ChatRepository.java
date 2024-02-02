package dev.eduard.beermessanger.repositories;

import dev.eduard.beermessanger.models.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
