package br.com.geofusion.ShoppingCart.exception;

public class ShoppingCartAddItemException extends RuntimeException {
    public ShoppingCartAddItemException(String productId) {
        super("Could not add product " + productId);
    }
}