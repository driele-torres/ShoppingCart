package br.com.geofusion.ShoppingCart.exception;

public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException(String clientId) {
        super("Could not find ShoppingCart para client" + clientId);
    }
}