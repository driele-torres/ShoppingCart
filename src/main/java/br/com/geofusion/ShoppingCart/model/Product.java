package br.com.geofusion.ShoppingCart.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe que representa um produto que pode ser adicionado
 * como item ao carrinho de compras.
 * <p>
 * Importante: Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 */

@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    private String id;
    private String description;

    @Column(name = "amount_available")
    private BigDecimal amountAvailable;

    @Column(name = "amount_pending")
    private BigDecimal amountPending;

    @Column(name = "price_current")
    private BigDecimal priceCurrent;

    /**
     * Construtor da classe Produto.
     *
     * @param description
     * @param amountAvailable
     * @param priceCurrent
     */
    public Product(String id, String description, BigDecimal amountAvailable, BigDecimal priceCurrent) {
        this.id = id;
        this.description = description;
        this.priceCurrent = priceCurrent;
        this.amountAvailable = amountAvailable;
    }

    public Product() {

    }

    /**
     * Retorna o código da produto.
     *
     * @return Long
     */
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retorna a descrição do produto.
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmountAvailable() {
        return this.amountAvailable;
    }

    public void setAmountAvailable(BigDecimal amountAvailable) {
        this.amountAvailable = amountAvailable;
    }

    public BigDecimal getPriceCurrent() {
        return this.priceCurrent;
    }

    public void setPriceCurrent(BigDecimal priceCurrent) {
        this.priceCurrent = priceCurrent;
    }

    public BigDecimal getAmountPending() {
        return this.amountPending;
    }

    public void setAmountPending(BigDecimal amountPending) {
        this.amountPending = amountPending;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.description);
    }

    @Override
    public String toString() {
        return "Product{ id=" + this.id + ", description='" + this.description + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Product))
            return false;
        Product obj = (Product) o;
        return Objects.equals(this.id, obj.id);
    }
}