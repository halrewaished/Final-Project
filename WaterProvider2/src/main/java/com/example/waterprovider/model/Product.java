package com.example.waterprovider.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Name is required !")
    private String name;
    @NotEmpty(message = "Size is required !")
    private String size;
    @NotNull(message = "Price is required !")
    private Integer pricePerUnit;

    @JsonIgnore
    private Integer stock;


    @ManyToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Cart> cart;
}
