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
            log.info("Preloading " + productRepository.save( new Product( "4238","Cobertura Chocolate", 456, new BigDecimal("4.56")) ));
            log.info("Preloading " + productRepository.save( new Product( "3238","Forminha Lisa", 209, new BigDecimal("6.31")) ));
            log.info("Preloading " + productRepository.save( new Product( "1488","Prato Descartável", 158, new BigDecimal("2.10")) ));
            log.info("Preloading " + productRepository.save( new Product( "2338","Wafer Hersheys", 256, new BigDecimal("9.23")) ));

            log.info("Preloading " + clientRepository.save( new Client( "2338","Bella Thorne")));
            log.info("Preloading " + clientRepository.save( new Client( "2237","Jhon Letter")));
        };
    }
}