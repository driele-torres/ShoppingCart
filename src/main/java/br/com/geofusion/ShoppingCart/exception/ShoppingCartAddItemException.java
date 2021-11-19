package br.com.geofusion.ShoppingCart.exception;

public class ShoppingCartAddItemException extends RuntimeException {
    public ShoppingCartAddItemException(String msg) {
        super("Could not add product " + msg);
    }
}