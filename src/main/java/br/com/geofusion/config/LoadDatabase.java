package br.com.geofusion.config;

import br.com.geofusion.ShoppingCart.repository.ClientRepository;
import br.com.geofusion.ShoppingCart.repository.ProductRepository;

import br.com.geofusion.ShoppingCart.model.Client;
import br.com.geofusion.ShoppingCart.model.Product;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.boot.CommandLineRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

@Configuration
class LoadDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, ClientRepository clientRepository) {
        return args -> {
            log.info("Preloading " + productRepository.save( new Product( 4238L,"Cobertura Chocolate", new BigDecimal(456), new BigDecimal("4.56")) ));
            log.info("Preloading " + productRepository.save( new Product( 3238L,"Forminha Lisa", new BigDecimal(200), new BigDecimal("6.31")) ));
            log.info("Preloading " + productRepository.save( new Product( 1488L,"Prato Descartável", new BigDecimal(154), new BigDecimal("2.10")) ));
            log.info("Preloading " + productRepository.save( new Product( 2338L,"Wafer Hersheys", new BigDecimal(789), new BigDecimal("9.23")) ));

            log.info("Preloading " + clientRepository.save( new Client( 2338L,"Bella Thorne")));
            log.info("Preloading " + clientRepository.save( new Client( 2237L,"Jhon Letter")));
        };
    }
}