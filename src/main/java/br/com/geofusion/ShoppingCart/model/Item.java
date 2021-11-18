package br.com.geofusion.ShoppingCart.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe que representa um item no carrinho de compras.
 */
@Entity
@Table(name = "shopping_cart_item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private int quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "product_code")
    private Product product;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_code")
    private ShoppingCart shoppingCart;

    /**
     * Construtor da classe Item.
     *
     * @param product
     * @param unitPrice
     * @param quantity
     */
    public Item(ShoppingCart shoppingCart, Product product, BigDecimal unitPrice, int quantity) {
        this.shoppingCart = shoppingCart;
        this.product = product;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    /**
     * Retorna o valor unitário original do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getUnitPrice() {
        return this.unitPrice;
    }
    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice=unitPrice;
    }

    /**
     * Retorna a quantidade dos item.
     *
     * @return int
     */
    public int getQuantity() {
        return this.quantity;
    }
    public void setQuantity( int quantity) {
        this.quantity=quantity;
    }
    /**
     * Retorna o produto.
     *
     * @return Produto
     */
    public Product getProduct() {
        return this.product;
    }
    public void setProduct(Product product) {
        this.product=product;
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        return this.unitPrice.multiply(new BigDecimal(this.quantity));
    }

    /**
     * Retorna o valor unitário original do produto.
     *
     * @return BigDecimal
     */
    public BigDecimal getOriginalUnitPrice() {
        if (this.product !=null)
            this.product.getPriceCurrent();
        return new BigDecimal(0);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

    @Override
    public String toString() {
        return "ShoppingCartItem { code=" + this.code + '}';
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
}

