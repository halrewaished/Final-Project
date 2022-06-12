package com.example.waterprovider.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor @Setter @Getter
public class ProductDTO {

    private Integer cartId;
    private String name;
    private String size;
    private Integer pricePerUnit;
    private Integer quantity;
    private Integer stock;

}
