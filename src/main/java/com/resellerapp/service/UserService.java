package com.resellerapp.service;

import com.resellerapp.model.User;
import com.resellerapp.repository.UserRepository;
import com.resellerapp.util.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LoggedUser loggedUser;


    public UserService(UserRepository userRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    public User getLoggedUser() {

        long id = this.loggedUser.getId();
        return this.userRepository.findById(id).get();
    }



    public List<User> getAllOtherUsers(long id){
        return this.userRepository.findByIdNot(id);
    }

    public Optional<User> getById(long id) {
        return this.userRepository.findById(id);
    }
}
