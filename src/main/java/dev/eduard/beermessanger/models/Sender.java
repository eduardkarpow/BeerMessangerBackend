package dev.eduard.beermessanger.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Sender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne()
    @JoinColumn(name="user_phone")
    private User user = new User();

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chat> chat = new ArrayList<Chat>();

    public Sender(String phone) {
        user.setPhone(phone);
    }
}
