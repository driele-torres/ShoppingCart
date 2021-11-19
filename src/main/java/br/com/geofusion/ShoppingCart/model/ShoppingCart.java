package br.com.geofusion.ShoppingCart.model;

import br.com.geofusion.ShoppingCart.exception.ShoppingCartAddItemException;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa o carrinho de compras de um cliente.
 */
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StatusShoppingCart status= StatusShoppingCart.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopping_cart_id")
    private List<Item> items;

    public ShoppingCart(){
    }

    public ShoppingCart(Client client) {
        this.client = client;
    }

    public ShoppingCart(StatusShoppingCart status, Client client, List<Item> items) {
        this.status = status;
        this.client = client;
        this.items = items;
    }

    /**
     * Retorna a lista de itens do carrinho de compras.
     *
     * @return items
     */
    public Collection<Item> getItems() {
        return items;
    }

    public StatusShoppingCart getStatus() {
        return status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return StatusShoppingCart.ACTIVE.equals(this.status);
    }

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
     * @param product
     * @param unitPrice
     * @param quantity
     */
    public void addItem(Product product, BigDecimal unitPrice, int quantity) throws ShoppingCartAddItemException {
        if (product == null)
            throw new ShoppingCartAddItemException("object null");
        if (quantity <=0 )
            throw new ShoppingCartAddItemException("invalid quantity");
        if (unitPrice.compareTo(BigDecimal.ZERO)>=0) {
            unitPrice = product.getPriceCurrent();
        }
        if (unitPrice.compareTo(BigDecimal.ZERO)>=0) {
            throw new ShoppingCartAddItemException("invalid price");
        }

        Item item = this.getItem(product);

        boolean inCart = (item != null);

        int amount = quantity;
        int position = 0;
        if (inCart) {
            amount += item.getQuantity();
            item.setUnitPrice(unitPrice);
        } else {
            position = this.items.size();
        }

        if ( amount > product.getAmountAvailable() )
            throw new ShoppingCartAddItemException("exceed quantity");

        if (!inCart) {
            item = new Item(this, product, unitPrice, amount , position);
            this.items.add(item);
        }
    }

    /**
     * Permite a remoção do item que representa este produto do carrinho de compras.
     *
     * @param product
     * @return Retorna um boolean, tendo o valor true caso o produto exista no carrinho de compras e false
     * caso o produto não exista no carrinho.
     */
    public boolean removeItem(Product product) {
        if (product == null)
            return false;
        Item item = this.getItem(product);
        return (item != null);
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
        int qtdItems = this.items.size();
        if (itemIndex>=qtdItems)
            return  false;
        return true;
    }

    /**
     * Retorna o valor total do carrinho de compras, que deve ser a soma dos valores totais
     * de todos os itens que compõem o carrinho.
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount() {
        BigDecimal amount = this.items
                .stream()
                .map(Item::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return amount;
    }

    public boolean invalidate() {
        this.status = StatusShoppingCart.INACTIVE;
        return !this.isActive();
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "ShoppingCart { id=" + this.id + '}';
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
        return Objects.equals(this.id, obj.id);
    }

    private Item getItem(Product product){
        return this.items.stream()
                .filter(itemPart -> product.getId().equals(itemPart.getProduct().getId()))
                .findAny()
                .orElse(null);
    }
}
