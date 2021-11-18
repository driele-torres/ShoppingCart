package br.com.geofusion.ShoppingCart.repository;

import br.com.geofusion.ShoppingCart.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    @Query( value = "SELECT * FROM shopping_cart u WHERE u.client_code = :clientCode",
            nativeQuery = true)
    List<ShoppingCart> findByClientCode(@Param("clientCode") Long clientCode);

    @Query( value = "SELECT * FROM shopping_cart u WHERE u.client_code = :clientCode and u.status = :status",
            nativeQuery = true)
    List<ShoppingCart> findByClientCodeAndStatus(@Param("clientCode") Long clientCode, @Param("status") int status);
}