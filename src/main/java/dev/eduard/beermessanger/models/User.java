package dev.eduard.beermessanger.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String phone;

    private String firstName;

    private String lastName;

    private String password;

    private String avatar;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Receiver> receivers = new ArrayList<Receiver>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sender> senders = new ArrayList<Sender>();

    public User(String phone, String firstName, String lastName, String password, String avatar) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.avatar = avatar;
    }
}
