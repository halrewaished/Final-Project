package com.example.waterprovider.service;

import com.example.waterprovider.exception.InvalidIdException;
import com.example.waterprovider.model.Cart;
import com.example.waterprovider.model.Product;
import com.example.waterprovider.model.User;
import com.example.waterprovider.repository.CartRepository;
import com.example.waterprovider.repository.ProductRepository;
import com.example.waterprovider.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;


    public List<Product> getProducts() {

        return productRepository.findAll();
    }

    public void addProduct(Product product) {

        productRepository.save(product);
    }


    public void updateProduct(Product product, Integer id) throws InvalidIdException {
        Product product1 = productRepository.findById(id)
                .orElseThrow(
                        ()-> new InvalidIdException("Invalid product id"));
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) throws InvalidIdException {
        Product product1 = productRepository.findById(id)
                .orElseThrow(
                        ()-> new InvalidIdException("Invalid product id"));
        productRepository.deleteById(id);
    }

    public Integer addToCart(Integer productId, Integer cartId, Integer quantity) {

        Product product = productRepository.findById(productId).get();
        Cart cart = cartRepository.findById(cartId).get();

        if(quantity > product.getStock()){
            return -1;
        }

        Integer sum = cart.getTotalPrice() + (product.getPricePerUnit() * quantity);
        cart.setTotalPrice(sum);
        cart.setQuantity(quantity);
        cart.getProduct().add(product);
        cartRepository.save(cart);

        return 1;
    }

    public void deleteFromCart(Integer productId,Integer userId) {
        User user = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();

        Set<Product> product2 = user.getCart().getProduct();
        product2.remove(product);

        Cart cart = cartRepository.findById(userId).get();

        cart.setProduct(product2);

        cartRepository.save(cart);

    }

    public Integer buyProduct(Integer userId) throws InvalidIdException{

        User user = userRepository.findById(userId)
                .orElseThrow(
                        ()-> new InvalidIdException("Invalid user id"));

        if(user.getCart() == null){
            return -1;
        }

        Set<Product> products = user.getCart().getProduct();
        for (Product p : products) {
            Integer sum = (p.getPricePerUnit() * user.getCart().getQuantity());
            Integer newBalance = user.getBalance() - sum;

            Integer newStock = p.getStock() - user.getCart().getQuantity();

            user.setBalance(newBalance);
            userRepository.save(user);

            p.setStock(newStock);
            productRepository.save(p);

        }
        return 1;
    }
}
