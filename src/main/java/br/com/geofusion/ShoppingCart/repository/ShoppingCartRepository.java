package br.com.geofusion.ShoppingCart.repository;

import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {}


