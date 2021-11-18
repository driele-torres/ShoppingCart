package br.com.geofusion.ShoppingCart.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long code) {
        super("Could not find product " + code);
    }
}