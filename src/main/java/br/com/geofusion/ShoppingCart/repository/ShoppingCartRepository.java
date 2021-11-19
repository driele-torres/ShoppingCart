package br.com.geofusion.ShoppingCart.repository;

import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    @Query( value = "SELECT * FROM shopping_cart u WHERE u.client_id = :client_id", nativeQuery = true)
    List<ShoppingCart> findByIdClient(@Param("client_id") String client_id);

    @Query( value = "SELECT * FROM shopping_cart u WHERE u.client_id = :client_id and u.status = :status", nativeQuery = true)
    List<ShoppingCart> findByIdClientAndStatus(@Param("client_id") String client_id, @Param("status") int status);
}