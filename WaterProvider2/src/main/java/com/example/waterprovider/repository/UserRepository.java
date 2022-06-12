package com.example.waterprovider.repository;

import com.example.waterprovider.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User findUsersByUsername(String username);

}
