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
    private String id;
    private String description;

    public Client(){
    }

    public Client(String id){
        this.id = id;
    }

    public Client(String id, String description){
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description);
    }

    @Override
    public String toString() {
        return "Client{ id=" + this.id + ", name='" + this.description + '}';
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
        return Objects.equals(this.id, obj.id) && Objects.equals(this.description, obj.description);
    }
}

