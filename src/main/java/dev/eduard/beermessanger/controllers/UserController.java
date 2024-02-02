package dev.eduard.beermessanger.controllers;

import dev.eduard.beermessanger.models.LoginInput;
import dev.eduard.beermessanger.models.User;
import dev.eduard.beermessanger.models.UserInput;
import dev.eduard.beermessanger.services.UserService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @QueryMapping
    List<User> getAllUsersHandler() {
        return userService.getAllUsers();
    }

    @MutationMapping
    User addUserHandler(@Argument UserInput userInput){
        return userService.addUser(userInput);
    }
    @MutationMapping
    User loginHandler(@Argument LoginInput loginInput){
        return userService.login(loginInput);
    }
    @MutationMapping
    User registerHandler(@Argument UserInput userInput){
        return userService.register(userInput);
    }
}
