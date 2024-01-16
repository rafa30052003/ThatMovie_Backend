package org.thatmovie.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thatmovie.exception.RecordNotFoundException;
import org.thatmovie.model.User;
import org.thatmovie.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No user found with id: " + id);
        }
    }

    public User createUser(User user) {
        User end;
        if (user.getId() != -1) {//update
            Optional<User> result = userRepository.findById(user.getId());
            if (result.isPresent()) {
                User fromDB = result.get();
                fromDB.setName(user.getName());
                fromDB.setSurname(user.getSurname());
                fromDB.setUsername(user.getUsername());
                fromDB.setPassword(user.getPassword());
                fromDB.setAvatar(user.getAvatar());
                fromDB.setBio(user.getBio());
                end = userRepository.save(fromDB);
            } else {
                throw new RecordNotFoundException("No user found with id: " + user.getId());
            }
        } else {//inset
            end=userRepository.save(user);
        }
        return end;
    }

    public void deleteUser(int id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No user found with id: " + id);
        }
    }

}
