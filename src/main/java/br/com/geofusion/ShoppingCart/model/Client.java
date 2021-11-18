package br.com.geofusion.ShoppingCart.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe que representa o cliente que pode criar um carrinho de compras.
 */
@Entity
public class Client {

    private @Id @GeneratedValue Long code;
    private String description;

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code, this.description);
    }

    @Override
    public String toString() {
        return "Client{ code=" + this.code + ", name='" + this.description + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Client))
            return false;
        Client obj = (Client) o;
        return Objects.equals(this.code, obj.code) && Objects.equals(this.description, obj.description);
    }
}

