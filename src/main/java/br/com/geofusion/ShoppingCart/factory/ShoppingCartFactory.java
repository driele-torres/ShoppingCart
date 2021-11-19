package br.com.geofusion.ShoppingCart.factory;

import br.com.geofusion.ShoppingCart.model.Client;
import br.com.geofusion.ShoppingCart.model.Item;
import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import br.com.geofusion.ShoppingCart.model.StatusShoppingCart;
import br.com.geofusion.ShoppingCart.repository.ShoppingCartRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Classe responsável pela criação e recuperação dos carrinhos de compras.
 */
public class ShoppingCartFactory {

    private final ShoppingCartRepository shoppingCartRepository;

    public ShoppingCartFactory(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }

    /**
     * Cria e retorna um novo carrinho de compras para o cliente passado como parâmetro.
     *
     * Caso já exista um carrinho de compras para o cliente passado como parâmetro, este carrinho deverá ser retornado.
     *
     * @param clientId
     * @return ShoppingCart
     */
    public ShoppingCart create(String clientId) {
        ShoppingCart shoppingCart = null;
        List<ShoppingCart> shoppingCarts = this.shoppingCartRepository.findByClientCodeAndStatus(clientId, StatusShoppingCart.ACTIVE.ordinal());
        if (!shoppingCarts.isEmpty())
            shoppingCart = shoppingCarts.stream().findFirst().get();
        else {
            shoppingCart = new ShoppingCart(new Client(clientId));
            this.shoppingCartRepository.saveAndFlush(shoppingCart);
        }
        return shoppingCart;
    }

    /**
     * Retorna o valor do ticket médio no momento da chamada ao método.
     * O valor do ticket médio é a soma do valor total de todos os carrinhos de compra dividido
     * pela quantidade de carrinhos de compra.
     * O valor retornado deverá ser arredondado com duas casas decimais, seguindo a regra:
     * 0-4 deve ser arredondado para baixo e 5-9 deve ser arredondado para cima.
     *
     * @return BigDecimal
     */
    public BigDecimal getAverageTicketAmount() {
        List<ShoppingCart> shoppingCarts = this.shoppingCartRepository.findAll();
        if (shoppingCarts.isEmpty())
            return BigDecimal.ZERO;
        BigDecimal amount = shoppingCarts
                .stream()
                .map(ShoppingCart::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return amount.divide(BigDecimal.valueOf(shoppingCarts.size())).setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * Invalida um carrinho de compras quando o cliente faz um checkout ou sua sessão expirar.
     * Deve ser efetuada a remoção do carrinho do cliente passado como parâmetro da listagem de carrinhos de compras.
     *
     * @param clientId
     * @return Retorna um boolean, tendo o valor true caso o cliente passado como parämetro tenha um carrinho de compras e
     * e false caso o cliente não possua um carrinho.
     */
    public boolean invalidate(String clientId) {
        List<ShoppingCart> shoppingCarts = this.shoppingCartRepository.findByClientCodeAndStatus(clientId, StatusShoppingCart.ACTIVE.ordinal());
        if (shoppingCarts.isEmpty())
            return false;
        ShoppingCart shoppingCart = shoppingCarts.stream().findFirst().get();
        if (!shoppingCart.invalidate())
            return false;
        this.shoppingCartRepository.saveAndFlush(shoppingCart);
        return !shoppingCart.isActive();
    }

}
