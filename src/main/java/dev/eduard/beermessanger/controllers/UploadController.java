package dev.eduard.beermessanger.controllers;


import dev.eduard.beermessanger.models.User;
import dev.eduard.beermessanger.repositories.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Optional;

@RestController
public class UploadController {

    private final UserRepository userRepository;

    public UploadController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @PostMapping("/upload/avatar")
    void uploadAvatar(@RequestParam("avatar") MultipartFile file, @RequestParam("phone") String phone){
        if(!file.isEmpty()){
            try{
                byte[] bytes = file.getBytes();
                String uploadDirectory = "C://Users/edkar/IdeaProjects/beermessanger/src/main/resources/static/images/"+phone+".jpg";
                String directoryForDB = "images/"+phone+".jpg";
                File uploadFile = new File(uploadDirectory);
                file.transferTo(uploadFile);
                User user = userRepository.findById(phone).get();
                user.setAvatar(directoryForDB);
                userRepository.save(user);
            } catch (Exception e){
                System.out.println(e);
            }
        } else{

        }
        //return userRepository.findAll().get(0);
    }
}
