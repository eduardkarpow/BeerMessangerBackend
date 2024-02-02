package dev.eduard.beermessanger.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;
    private String attach;
    private LocalDateTime datetime;
    private Boolean checked;
    private Boolean isSent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="chat_id")
    private Chat chat = new Chat();

    public Message(String text, String attach, Integer chat, LocalDateTime datetime, Boolean isSent) {
        this.text = text;
        this.attach = attach;
        this.chat.setId(chat);
        this.datetime = datetime;
        this.isSent = isSent;
        this.checked = false;
    }
}
