package br.com.geofusion.ShoppingCart.repository;

import br.com.geofusion.ShoppingCart.model.Item;
import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByShoppingCartOrderByPosition(ShoppingCart shoppingCart);
}