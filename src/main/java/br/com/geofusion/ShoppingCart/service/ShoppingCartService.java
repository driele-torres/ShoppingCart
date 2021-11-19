package br.com.geofusion.ShoppingCart.service;

import br.com.geofusion.ShoppingCart.exception.ClientNotFoundException;
import br.com.geofusion.ShoppingCart.factory.ShoppingCartFactory;
import br.com.geofusion.ShoppingCart.model.Item;
import br.com.geofusion.ShoppingCart.repository.ProductRepository;
import br.com.geofusion.ShoppingCart.repository.ClientRepository;
import br.com.geofusion.ShoppingCart.repository.ItemRepository;

import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import br.com.geofusion.ShoppingCart.repository.ShoppingCartRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService {
    private final ClientRepository clientRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final ItemRepository itemRepository;

    private final ShoppingCartFactory shoppingCartFactory;

    ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, ClientRepository clientRepository, ItemRepository itemRepository) {
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

    public List<ShoppingCart> findAllByIdClientAndStatus(String clientId, int status) {
        return new ArrayList<ShoppingCart>();
    }
    public List<ShoppingCart> findAll(String clientId) {
        return new ArrayList<ShoppingCart>();
    }

    public BigDecimal getAverageTicketAmount() {
        return null;
    }

    public BigDecimal getAverageTicketAmount(String clientId) {
        return null;
    }

    public ShoppingCart addItem(String clientId , Item item ) {
//        if (){
//        }
        return null;
    }

    public ShoppingCart removeItem(String clientId , Long code ) {
        return null;
    }

    public boolean invalidate(String clientId) {
        return false;
    }
    public boolean invalidateByClient(Long clientId) {
        return false;
    }


}

