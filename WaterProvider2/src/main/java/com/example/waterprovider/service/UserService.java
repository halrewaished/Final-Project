package com.example.waterprovider.service;

import com.example.waterprovider.DTO.HistoryDTO;
import com.example.waterprovider.exception.InvalidIdException;
import com.example.waterprovider.model.Cart;
import com.example.waterprovider.model.History;
import com.example.waterprovider.model.User;
import com.example.waterprovider.repository.CartRepository;
import com.example.waterprovider.repository.HistoryRepository;
import com.example.waterprovider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final HistoryRepository historyRepository;

    public List<User> getUsers() {

        return userRepository.findAll();
    }

    public void addUser(User user) {
        String hashedPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.save(user);
    }

    public void updateUser(User user, Integer id) throws InvalidIdException {
        User user1 = userRepository.findById(id)
                .orElseThrow(
                        ()-> new InvalidIdException("Invalid user id"));

        userRepository.save(user);
    }

    public void deleteUser(Integer id) throws InvalidIdException {
        User user1 = userRepository.findById(id)
                .orElseThrow(
                        ()-> new InvalidIdException("Invalid user id"));
        userRepository.deleteById(id);
    }

    public Cart showCart(Integer userId) throws InvalidIdException {
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new InvalidIdException("Invalid user id"));
        Cart cart = cartRepository.findById(userId)
                .orElseThrow(
                        () -> new InvalidIdException("Invalid user id"));
        return cart;

    }

    public void addHistory(HistoryDTO historyDTO) throws InvalidIdException {
        User user = userRepository.findById(historyDTO.getUserId())
                .orElseThrow(
                    ()-> new InvalidIdException("Invalid user id"));
        History history = new History(null,historyDTO.getProductId(),user);

        user.getHistories().add(history);
        historyRepository.save(history);
    }


}
