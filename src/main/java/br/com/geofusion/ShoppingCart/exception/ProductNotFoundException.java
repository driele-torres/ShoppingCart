package br.com.geofusion.ShoppingCart.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String productId) {
        super("Could not find product " + productId);
    }
}