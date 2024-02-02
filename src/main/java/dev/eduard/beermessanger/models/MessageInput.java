package dev.eduard.beermessanger.models;

import java.time.LocalDateTime;

public record MessageInput(String text, String attach, Integer chat, String datetime, Boolean isSent) {
}
