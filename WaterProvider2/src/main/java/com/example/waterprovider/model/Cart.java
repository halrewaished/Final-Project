package com.example.waterprovider.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Cart {

    @Id
    private Integer id;

    private Integer totalPrice = 0;

    private Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Product> product;

}
