package br.com.geofusion.ShoppingCart.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * Classe que representa o cliente que pode criar um carrinho de compras.
 */
@Entity
@Table(name = "client")
public class Client implements Serializable {
    @Id
    private Long code;
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

