package dev.eduard.beermessanger.services;

import dev.eduard.beermessanger.models.LoginInput;
import dev.eduard.beermessanger.models.User;
import dev.eduard.beermessanger.models.UserInput;
import dev.eduard.beermessanger.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public User register(UserInput userInput){
        Boolean userFromDB = userRepository.existsById(userInput.phone());
        User user = new User(userInput.phone(), userInput.firstName(),
                userInput.lastName(), userInput.password(),
                userInput.avatar());
        if(!userFromDB) {
            return userRepository.save(user);
        }
        throw new Error("user exists");
    }
    public User login(LoginInput loginInput){
        Boolean isUserInDB = userRepository.existsById(loginInput.phone());
        if(isUserInDB){
            User user = userRepository.getReferenceById(loginInput.phone());
            System.out.println(user.getPassword().equals(loginInput.password()));
            if(user.getPassword().equals(loginInput.password())){
                return user;
            }
            throw new Error("Incorrect login or password");
        }
        throw new Error("User doesn't exists");
    }

    public User addUser(UserInput userInput) {
        User user = new User(userInput.phone(), userInput.firstName(),
                userInput.lastName(), userInput.password(),
                userInput.avatar());
        return userRepository.save(user);
    }
}
