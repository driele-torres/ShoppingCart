package br.com.geofusion.ShoppingCart.model;

import java.math.BigDecimal;
import java.util.Objects;

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
        return productSynthetic;
    }

    /**
     * Retorna o valor unitário original do produto.
     *
     * @return BigDecimal
     */
    public BigDecimal getOriginalUnitPrice() {
        if (productSynthetic!=null)
            productSynthetic.getPriceCurrent();
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
        return 0;
    }

    /**
     * Retorna o valor total do item.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Item))
            return false;
        Item employee = (Item) o;
        return Objects.equals(this.code, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role);
    }

    @Override
    public String toString() {
        return "ShoppingCartItem{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
    }
}

