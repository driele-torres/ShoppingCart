package br.com.geofusion.ShoppingCart.repository;

import br.com.geofusion.ShoppingCart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {}