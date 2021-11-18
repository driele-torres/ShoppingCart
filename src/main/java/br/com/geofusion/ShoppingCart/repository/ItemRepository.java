package br.com.geofusion.ShoppingCart.repository;

import br.com.geofusion.ShoppingCart.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {}