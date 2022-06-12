package com.example.waterprovider.controller;

import com.example.waterprovider.DTO.Api;
import com.example.waterprovider.DTO.HistoryDTO;
import com.example.waterprovider.model.Cart;
import com.example.waterprovider.model.User;
import com.example.waterprovider.service.UserService;
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
public class UserController {

    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers(){
        logger.info("Request in get users ");
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("register")
    public ResponseEntity<Api> addUser(@RequestBody @Valid User user){
        logger.info("Request in add user ");
        userService.addUser(user);
        return ResponseEntity.status(200).body(new Api("New user added ",201));
    }

    @PutMapping("update-user/{id}")
    public ResponseEntity<Api> updateUser(@RequestBody @Valid User user,@PathVariable Integer id){
        logger.info("Request in update user ");
        userService.updateUser(user,id);
        return ResponseEntity.status(200).body(new Api("User is updated",201));
    }

    @DeleteMapping("delete-user/{id}")
    public ResponseEntity<Api> deleteUser(@PathVariable Integer id){
        logger.info("Request in delete user ");
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new Api("User is deleted",201));
    }

    @GetMapping("show-cart/{userId}")
    public ResponseEntity<Cart> showCart(@PathVariable Integer userId){
        logger.info("Request in get cart ");
        return ResponseEntity.status(200).body(userService.showCart(userId));
    }

    @PostMapping("add-history")
    public ResponseEntity<Api> addHistory(@RequestBody HistoryDTO historyDTO){
        logger.info("Request in add history ");
        userService.addHistory(historyDTO);
        return ResponseEntity.status(200).body(new Api("History added",201));
    }

    //log in

    @GetMapping("customer")
    public ResponseEntity<?> user(){

        return ResponseEntity.status(200).body(new Api("Hello Customer",200));
    }

    @GetMapping("admin")
    public ResponseEntity<?> admin(){

        return ResponseEntity.status(200).body(new Api("Hello Admin",200));
    }


}
