package com.example.waterprovider.controller;

import com.example.waterprovider.DTO.Api;
import com.example.waterprovider.DTO.CartDTO;
import com.example.waterprovider.model.Cart;
import com.example.waterprovider.service.CartService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/")
public class CartController {

    private final CartService cartService;

    Logger logger = LoggerFactory.getLogger(CartController.class);

    @GetMapping("carts")
    public ResponseEntity<List<Cart>> getCarts(){
        logger.info("Request in get carts ");
        return ResponseEntity.status(200).body(cartService.getCarts());
    }

    @PostMapping("add-cart")
    public ResponseEntity<Api> addCart(@RequestBody CartDTO cart){
        logger.info("Request in add cart ");
        cartService.addCart(cart);
        return ResponseEntity.status(200).body(new Api("New cart added ",201));
    }

    @PutMapping("update-cart/{id}")
    public ResponseEntity<Api> updateCart(@RequestBody @Valid Cart cart,@PathVariable Integer id){
        logger.info("Request in update cart ");
        cartService.updateCart(cart,id);
        return ResponseEntity.status(200).body(new Api("Cart is updated",201));
    }

    @DeleteMapping("delete-cart/{id}")
    public ResponseEntity<Api> deleteCart(@PathVariable Integer id){
        logger.info("Request in delete cart ");
        cartService.deleteCart(id);
        return ResponseEntity.status(200).body(new Api("Cart is deleted",201));
    }

}
