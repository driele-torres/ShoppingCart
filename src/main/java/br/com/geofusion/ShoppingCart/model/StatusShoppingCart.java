package br.com.geofusion.ShoppingCart.model;

public enum StatusShoppingCart {
    ACTIVE("Ativo"),
    INACTIVE("Inativo");

    private String description;

    StatusShoppingCart(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
