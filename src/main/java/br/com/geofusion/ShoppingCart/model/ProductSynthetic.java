package br.com.geofusion.ShoppingCart.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe que representa um produto que pode ser adicionado
 * como item ao carrinho de compras.
 * <p>
 * Importante: Dois produtos são considerados iguais quando ambos possuem o
 * mesmo código.
 */

public class ProductSynthetic {
    private Long code;
    private String description;
    private BigDecimal amountAvailable;
    private BigDecimal amountPending;
    private BigDecimal priceCurrent;

    /**
     * Construtor da classe Produto.
     *
     * @param description
     * @param amountAvailable
     * @param priceCurrent
     */
    public ProductSynthetic(String description, BigDecimal amountAvailable, BigDecimal priceCurrent) {
        this.description = description;
        this.priceCurrent = priceCurrent;
        this.amountAvailable = amountAvailable;
    }

    /**
     * Retorna o código da produto.
     *
     * @return Long
     */
    public Long getCode() {
        return this.code;
    }

    /**
     * Retorna a descrição do produto.
     *
     * @return String
     */
    public String getDescription() {
        return this.description;
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
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof ProductSynthetic))
            return false;
        ProductSynthetic obj = (ProductSynthetic) o;
        return Objects.equals(this.code, obj.code);
    }

    public void setCode(Long code) {
        this.code = code;
    }
}