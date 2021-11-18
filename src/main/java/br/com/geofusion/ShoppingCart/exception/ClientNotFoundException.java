package br.com.geofusion.ShoppingCart.exception;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(Long code) {
        super("Could not find client " + code);
    }
}