package br.com.geofusion.ShoppingCart.repository;

import br.com.geofusion.ShoppingCart.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {}
