package br.com.geofusion.ShoppingCart.model;

import java.math.BigDecimal;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Classe que representa um item no carrinho de compras.
 */
@Entity
public class Item {
    private @Id @GeneratedValue Long code;
    private ProductSynthetic productSynthetic;
    private ShoppingCart shoppingCart;
    private BigDecimal unitPrice;
    private int quantity;

    /**
     * Construtor da classe Item.
     *
     * @param productSynthetic
     * @param unitPrice
     * @param quantity
     */
    public Item(ShoppingCart shoppingCart, ProductSynthetic productSynthetic, BigDecimal unitPrice, int quantity) {
        this.shoppingCart = shoppingCart;
        this.productSynthetic = productSynthetic;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    /**
     * Retorna o produto.
     *
     * @return Produto
     */
    public ProductSynthetic getProduct() {
        return this.productSynthetic;
    }

    /**
     * Retorna o valor unitário original do produto.
     *
     * @return BigDecimal
     */
    public BigDecimal getOriginalUnitPrice() {
        if (this.productSynthetic!=null)
            this.productSynthetic.getPriceCurrent();
        return new BigDecimal(0);
    }

    /**
     * Retorna o valor unitário original do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        return this.unitPrice.multiply(new BigDecimal(this.quantity));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof Item))
            return false;
        Item obj = (Item) o;
        return Objects.equals(this.code, obj.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

    @Override
    public String toString() {
        return "ShoppingCartItem { code=" + this.code + '}';
    }
}

