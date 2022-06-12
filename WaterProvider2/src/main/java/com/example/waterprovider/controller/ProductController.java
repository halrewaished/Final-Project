package com.example.waterprovider.controller;

import com.example.waterprovider.DTO.Api;
import com.example.waterprovider.model.Product;
import com.example.waterprovider.service.ProductService;
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
public class ProductController {

    private final ProductService productService;

    Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("products")
    public ResponseEntity<List<Product>> getProducts(){
        logger.info("Request in get Products ");
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping("add-product")
    public ResponseEntity<Api> addProduct(@RequestBody @Valid Product product){
        logger.info("Request in add product ");
        productService.addProduct(product);
        return ResponseEntity.status(200).body(new Api("New product added ",201));
    }

    @PutMapping("update-product/{id}")
    public ResponseEntity<Api> updateProduct(@RequestBody @Valid Product product,@PathVariable Integer id){
        logger.info("Request in update product ");
        productService.updateProduct(product,id);
        return ResponseEntity.status(200).body(new Api("Product is updated",201));
    }

    @DeleteMapping("delete-product/{id}")
    public ResponseEntity<Api> deleteProduct(@PathVariable Integer id){
        logger.info("Request in delete product ");
        productService.deleteProduct(id);
        return ResponseEntity.status(200).body(new Api("Product is deleted",201));
    }

    @PostMapping("addToCart/{productId}/{cartId}/{quantity}")
    public ResponseEntity<Api> addCart(@PathVariable Integer productId,
                                       @PathVariable Integer cartId,@PathVariable Integer quantity){
        logger.info("Request in add product to cart ");
        Integer addStatus =  productService.addToCart(productId,cartId,quantity);
        switch (addStatus){
            case -1:
                return ResponseEntity.status(400).body(new Api("Quantity is bigger than the stock ",400));
            case 1:
                return ResponseEntity.status(200).body(new Api("Add product to cart ",201));
            default:
                return ResponseEntity.status(500).body(new Api("Server error !",500));
        }
    }

    @DeleteMapping("deleteFromCart/{userId}/{productId}")
    public ResponseEntity<Api> deleteCart(@PathVariable Integer userId, @PathVariable Integer productId){
        logger.info("Request in delete product from cart ");
        productService.deleteFromCart(userId,productId);
        return ResponseEntity.status(200).body(new Api("Delete product from cart ",201));
    }

    @PutMapping("buy/{userId}")
    public ResponseEntity<Api> buyProduct(@PathVariable Integer userId){
        logger.info("Request in buy product ");
        Integer buyStatus = productService.buyProduct(userId);
        switch (buyStatus){
            case -1:
                return ResponseEntity.status(400).body(new Api("Cart is empty",400));
            case 1:
                return ResponseEntity.status(200).body(new Api("Buy product is done !",201));
            default:
                return ResponseEntity.status(500).body(new Api("Server error !",500));
        }

    }


}
