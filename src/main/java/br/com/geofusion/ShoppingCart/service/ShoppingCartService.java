package br.com.geofusion.ShoppingCart.service;

import br.com.geofusion.ShoppingCart.exception.ClientNotFoundException;
import br.com.geofusion.ShoppingCart.exception.ShoppingCartNotFoundException;
import br.com.geofusion.ShoppingCart.model.Item;
import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import br.com.geofusion.ShoppingCart.factory.ShoppingCartFactory;
import br.com.geofusion.ShoppingCart.repository.ShoppingCartRepository;
import br.com.geofusion.ShoppingCart.repository.ProductRepository;
import br.com.geofusion.ShoppingCart.repository.ClientRepository;

import br.com.geofusion.ShoppingCart.model.Product;

import java.util.ArrayList;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ShoppingCartService {
    private final ShoppingCartFactory shoppingCartFactory;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    ShoppingCartService(ShoppingCartRepository shoppingCartRepository,ProductRepository productRepository, ClientRepository clientRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.shoppingCartFactory = new ShoppingCartFactory(shoppingCartRepository);
    }

    public ShoppingCart create(String clientId) throws ClientNotFoundException {
        if (!this.clientRepository.existsById(clientId))
            throw new ClientNotFoundException(clientId);
        return shoppingCartFactory.create(clientId);
    }

    public ShoppingCart findShoppingCartActiveByByIdClient(String clientId) throws ClientNotFoundException, ShoppingCartNotFoundException {
        if (!this.clientRepository.existsById(clientId))
            throw new ClientNotFoundException(clientId);
        return shoppingCartFactory.findShoppingCartActiveByIdClient(clientId);
    }

    public List<Item> findShoppingCartActiveByByIdClientItems(String clientId) throws ClientNotFoundException, ShoppingCartNotFoundException {
        ShoppingCart shoppingCart = this.findShoppingCartActiveByByIdClient(clientId);
        return new ArrayList<>(shoppingCart.getItems());
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

    public boolean invalidate(String clientId) {
        try {
            return this.shoppingCartFactory.invalidate(clientId);
        } catch (ShoppingCartNotFoundException e){
            return false;
        }
    }

    public boolean addItem(String clientId, Item item) {
        try {
            var productId = item.getProduct().getId();
            Product product = this.productRepository.getById(productId);
            ShoppingCart shoppingCart = this.shoppingCartFactory.findShoppingCartActiveByIdClient(clientId);
            shoppingCart.addItem(product, item.getUnitPrice(), item.getQuantity());
            this.shoppingCartRepository.saveAndFlush(shoppingCart);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeItem(String clientId, String productId) {
        try {
            Product product = this.productRepository.getById(productId);
            ShoppingCart shoppingCart = this.shoppingCartFactory.findShoppingCartActiveByIdClient(clientId);
            if (shoppingCart.removeItem(product)){
                Item item = shoppingCart.getItem(product);
                shoppingCart.getItems().remove(item);
                this.shoppingCartRepository.saveAndFlush(shoppingCart);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}


