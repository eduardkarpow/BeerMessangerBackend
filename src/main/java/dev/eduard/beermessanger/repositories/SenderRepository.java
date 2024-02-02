package dev.eduard.beermessanger.repositories;

import dev.eduard.beermessanger.models.Sender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SenderRepository extends JpaRepository<Sender, Integer> {
}
