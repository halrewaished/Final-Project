package com.example.waterprovider.service;

import com.example.waterprovider.DTO.CartDTO;
import com.example.waterprovider.exception.InvalidIdException;
import com.example.waterprovider.model.Cart;
import com.example.waterprovider.model.User;
import com.example.waterprovider.repository.CartRepository;
import com.example.waterprovider.repository.ProductRepository;
import com.example.waterprovider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    public List<Cart> getCarts() {
        return cartRepository.findAll();
    }

    public void addCart(CartDTO cart) throws InvalidIdException {
        User user = userRepository.findById(cart.getUserId())
                .orElseThrow(
                        ()-> new InvalidIdException("Invalid user id"));

        Cart cart1 = new Cart(null,0,0,user,null);
        user.setCart(cart1);
        cartRepository.save(cart1);
    }

    public void updateCart(Cart cart, Integer id) throws InvalidIdException {
        Cart cart1 = cartRepository.findById(id)
                .orElseThrow(
                        ()-> new InvalidIdException("Invalid cart id"));
        cartRepository.save(cart);

    }

    public void deleteCart(Integer id) throws InvalidIdException {
        Cart cart1 = cartRepository.findById(id)
                .orElseThrow(
                        ()-> new InvalidIdException("Invalid cart id"));
        cartRepository.deleteById(id);
    }
}
