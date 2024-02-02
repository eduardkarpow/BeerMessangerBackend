package dev.eduard.beermessanger.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserExtended {
    public Integer id;
    public User user;
    public String lastMessage;
    public String dateTime;

}

