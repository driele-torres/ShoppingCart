package br.com.geofusion.ShoppingCart.service;

import br.com.geofusion.ShoppingCart.exception.ClientNotFoundException;
import br.com.geofusion.ShoppingCart.model.Client;
import br.com.geofusion.ShoppingCart.model.Item;
import br.com.geofusion.ShoppingCart.repository.ProductRepository;
import br.com.geofusion.ShoppingCart.repository.ClientRepository;
import br.com.geofusion.ShoppingCart.repository.ShoppingCartRepository;
import br.com.geofusion.ShoppingCart.repository.ItemRepository;

import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;
    private final ItemRepository itemRepository;

    ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductRepository productRepository, ClientRepository clientRepository, ItemRepository itemRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
        this.itemRepository = itemRepository;
    }

    public ShoppingCart create(Long clientCode) throws ClientNotFoundException {
        if (this.clientRepository.existsById(clientCode)){
            List<ShoppingCart> shoppingCarts=this.shoppingCartRepository.findByClientCode(clientCode);
            if (shoppingCarts.isEmpty()){

            } else {
            }
        } else {
            throw new ClientNotFoundException(clientCode);
        }
//        shoppingCartRepository.find
        return new ShoppingCart();
    }

    public List<ShoppingCart> findAllByIdClientAndStatus(Long idClient, int status) {
        return new ArrayList<ShoppingCart>();
    }
    public List<ShoppingCart> findAll(Long idClient) {
        return new ArrayList<ShoppingCart>();
    }

    public BigDecimal getAverageTicketAmount() {
        return null;
    }

    public BigDecimal getAverageTicketAmount(Long idClient ) {
        return null;
    }

    public ShoppingCart addItem(Long idClient , Item item ) {

//        if (){
//
//        }

        return null;
    }

    public ShoppingCart removeItem(Long idClient , Long code ) {
        return null;
    }

    public boolean invalidate(Long clientId) {
        return false;
    }
    public boolean invalidateByClient(Long clientId) {
        return false;
    }


}

