package br.com.geofusion.ShoppingCart.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String clientId) {
        super("Could not find client " + clientId);
    }
}