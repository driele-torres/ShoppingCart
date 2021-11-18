package br.com.geofusion.ShoppingCart.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
public class ShoppingCart {
    private @Id
    @GeneratedValue
    Long code;
    private StatusShoppingCart status;
    private List<Item> items;

    /**
     * Permite a adição de um novo item no carrinho de compras.
     *
     * Caso o item já exista no carrinho para este mesmo produto, as seguintes regras deverão ser seguidas:
     * - A quantidade do item deverá ser a soma da quantidade atual com a quantidade passada como parâmetro.
     * - Se o valor unitário informado for diferente do valor unitário atual do item, o novo valor unitário do item deverá ser
     * o passado como parâmetro.
     *
     * Devem ser lançadas subclasses de RuntimeException caso não seja possível adicionar o item ao carrinho de compras.
     *
     * @param productSynthetic
     * @param unitPrice
     * @param quantity
     */
    public void addItem(ProductSynthetic productSynthetic, BigDecimal unitPrice, int quantity) {

    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param productSynthetic
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(ProductSynthetic productSynthetic) {
        return false;
    }

    /**
     * Permite a remoção do item de acordo com a posição.
     * Essa posição deve ser determinada pela ordem de inclusão do produto na
     * coleção, em que zero representa o primeiro item.
     *
     * @param itemIndex
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(int itemIndex) {
        return false;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        BigDecimal amount = null;
        return amount;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return items
     */
    public Collection<Item> getItems() {
        return null;
    }

    public StatusShoppingCart getStatus() {
        return status;
    }

    public boolean isActive() {
        return StatusShoppingCart.ACTIVE.equals(this.status);
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (!(o instanceof ShoppingCart))
            return false;
        ShoppingCart obj = (ShoppingCart) o;
        return Objects.equals(this.code, obj.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.code);
    }

    @Override
    public String toString() {
        return "ShoppingCart { code=" + this.code + '}';
    }
}
