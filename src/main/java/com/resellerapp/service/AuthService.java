package com.resellerapp.service;

import com.resellerapp.model.DTOs.LoginDTO;
import com.resellerapp.model.DTOs.RegisterDTO;
import com.resellerapp.model.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final LoggedUser loggedUser;


    public AuthService(UserRepository userRepository, ModelMapper modelMapper, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.loggedUser = loggedUser;
    }


    public boolean register(RegisterDTO registerDTO) {
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPaswword())) {
            return false;
        }
        Optional<User> byEmail = userRepository.findByEmail(registerDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }
        Optional<User> byUsername = userRepository.findByUsername(registerDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }


        User user = this.modelMapper.map(registerDTO, User.class);
        this.userRepository.save(user);

        return true;
    }

    public boolean login(LoginDTO loginDTO) {

        Optional<User> optionalUser = this.userRepository.findByUsernameAndPassword(loginDTO.getUsername(), loginDTO.getPassword());

        if (optionalUser.isEmpty()) {
            return false;
        }

        this.loggedUser.login(optionalUser.get());
        return true;
    }

    public void logout() {
        this.loggedUser.logout();
    }

}
