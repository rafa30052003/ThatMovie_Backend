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

    /**
     * Obtiene todos los usuarios
     *
     * @return La lista de usuarios
     */
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    /**
     * Obtiene un usuario por su id
     *
     * @param id El id del usuario
     * @return El usuario con el id dado
     */
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("No se encontró un usuario con el id: " + id);
        }
    }

    /**
     * Crea o actualiza un usuario
     *
     * @param user El usuario a crear o actualizar
     * @return El usuario creado o actualizado
     */
    public User createOrUpdateUser(User user) {
        User end;
        if (user.getId() != -1) {// Actualizar
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
                throw new RecordNotFoundException("No se encontró un usuario con el id: " + user.getId());
            }
        } else {// Insertar
            end=userRepository.save(user);
        }
        return end;
    }

    /**
     * Elimina un usuario por su id
     *
     * @param id El id del usuario a eliminar
     */
    public void deleteUser(int id) {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No se encontró un usuario con el id: " + id);
        }
    }

    /**
     * Obtiene un usuario por su nombre de usuario
     *
     * @param username El nombre de usuario del usuario
     * @return El usuario con el nombre de usuario dado
     */
    public User getUserByUsername(String username) {
        Optional<User> result = userRepository.findByUsername(username);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RecordNotFoundException("No se encontró un usuario con el nombre de usuario: " + username);
        }
    }
}