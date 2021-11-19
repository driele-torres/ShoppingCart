package br.com.geofusion.ShoppingCart.service;

import br.com.geofusion.ShoppingCart.exception.ClientNotFoundException;
import br.com.geofusion.ShoppingCart.model.Item;
import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import br.com.geofusion.ShoppingCart.factory.ShoppingCartFactory;
import br.com.geofusion.ShoppingCart.repository.ShoppingCartRepository;
import br.com.geofusion.ShoppingCart.repository.ProductRepository;
import br.com.geofusion.ShoppingCart.repository.ClientRepository;
import br.com.geofusion.ShoppingCart.repository.ItemRepository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartService {
    private final ShoppingCartFactory shoppingCartFactory;
    private final ClientRepository clientRepository;
    private final ItemRepository itemRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    ShoppingCartService(ShoppingCartRepository shoppingCartRepository,ProductRepository productRepository, ClientRepository clientRepository, ItemRepository itemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.itemRepository = itemRepository;
        this.shoppingCartFactory = new ShoppingCartFactory(shoppingCartRepository);
    }

    public ShoppingCart create(String clientId) throws ClientNotFoundException {
        if (!this.clientRepository.existsById(clientId))
            throw new ClientNotFoundException(clientId);
        return shoppingCartFactory.create(clientId);
    }

    public List<ShoppingCart> findByIdClientAndStatus(String clientId, int status) {
        return shoppingCartRepository.findByIdClientAndStatus( clientId, status);
    }

    public List<ShoppingCart> findByIdClient(String clientId) {
        return shoppingCartRepository.findByIdClient( clientId );
    }

    public BigDecimal getAverageTicketAmount() {
        return shoppingCartFactory.getAverageTicketAmount();
    }

    public ShoppingCart addItem(String clientId, Item item) {
        return null;
    }

    public ShoppingCart removeItem(String clientId, Long code) {
        return null;
    }

    public boolean invalidate(String clientId) {
        return false;
    }

}
